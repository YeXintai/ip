package sozius.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sozius.Sozius;

/**
 * Controller for the main GUI.
 */
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image soziusImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the dialog view
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Sozius: Hello! I'm Sozius.\n        What do you need?\n", soziusImage)
        );
    }

    /** Injects the Sozius instance */
    public void setSozius(Sozius sozius) {
        assert sozius != null;

        this.sozius = sozius;
    }

    /** Injects the Stage instance */
    public void setStage(Stage stage) {
        assert stage != null;

        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sozius' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            sozius.saveTasks();
            stage.close();
        }

        String response = sozius.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, soziusImage)
        );
        userInput.clear();
    }
}
