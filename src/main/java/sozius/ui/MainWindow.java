package sozius.ui;

import java.time.DateTimeException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sozius.Sozius;

/** Controller for the main GUI. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Sozius sozius;
    private Stage stage;

    /** Initializes the conversation and input controls. */
    @FXML
    public void initialize() {
        // Follow new messages and wrapped content after JavaFX lays them out.
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) ->
                scrollPane.setVvalue(1.0));
        sendButton.disableProperty().bind(userInput.textProperty().isEmpty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                "Hello! I'm Sozius. What do you need?\n"
                        + "Try: todo read a book\n"
                        + "Use list to see your tasks.", null));
        Platform.runLater(() -> userInput.requestFocus());
    }

    /** Injects the Sozius instance. */
    public void setSozius(Sozius sozius) {
        assert sozius != null;
        this.sozius = sozius;
    }

    /** Injects the Stage instance. */
    public void setStage(Stage stage) {
        assert stage != null;
        this.stage = stage;
    }

    /** Sends a command and keeps unsuccessful input available for correction. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            userInput.clear();
            userInput.requestFocus();
            return;
        }
        if (input.equals("bye")) {
            sozius.saveTasks();
            stage.close();
            return;
        }

        String response;
        boolean isError;
        try {
            response = sozius.getResponse(input);
            // These are the two error prefixes returned by the current Parser.
            isError = response.startsWith("Error:") || response.startsWith("Invalid command:");
        } catch (DateTimeException | IllegalArgumentException e) {
            response = "Check the command's values and date format, then try again.";
            isError = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, null),
                isError ? DialogBox.getErrorDialog(response) : DialogBox.getDukeDialog(response, null));
        if (isError) {
            userInput.selectAll();
        } else {
            userInput.clear();
        }
        userInput.requestFocus();
    }
}
