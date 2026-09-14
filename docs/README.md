# Sozius User Guide

Sozius is a task assistant that lets you manage todos, deadlines, and events by typing commands into a chat window. You can view your tasks, mark them as done, search their descriptions, and delete tasks you no longer need.

## Using Sozius

Type a command into the input field and press **Enter** or click **Send**.

Use lowercase command words. In the command formats below, text inside angle brackets, such as `<description>`, represents a value you must supply. Do not type the angle brackets.

## Command summary

| Action | Command format |
| --- | --- |
| Add a todo | `todo <description>` |
| Add a deadline | `deadline <description> /by <date>` |
| Add an event | `event <description> /from <start> /to <end>` |
| List tasks | `list` |
| Mark a task as done | `mark <index>` |
| Mark a task as not done | `unmark <index>` |
| Delete a task | `delete <index>` |
| Search descriptions | `find <keyword>` |
| Save and exit | `bye` |

## Adding a todo

A todo is a task without a date or time.

**Format:** `todo <description>`

**Example:**

```text
todo read a book
```

Sozius adds the task, displays its details, and reports the total number of tasks in your list.

## Adding a deadline

A deadline is a task with a due date.

**Format:** `deadline <description> /by <date>`

Supply both a description and a date, separated by `/by`.

For example, to add a task named “add user guide” due on 2026-09-19 2359, use the following command pattern:

```text
deadline submit report /by 2026-09-19 2359
```

Sozius adds the deadline and displays the updated task count.

## Adding an event

An event is a task with a start and an end.

**Format:** `event <description> /from <start> /to <end>`

Supply the description, followed by `/from` and the start, then `/to` and the end.

For example, to add an event named “team meeting”, replace `<start>` and `<end>` below with the appropriate values:

```text
event team meeting /from <start> /to <end>
```

Sozius adds the event and displays the updated task count.

## Listing tasks

**Command:** `list`

Displays all tasks in a numbered list. If the list is empty, Sozius replies:

```text
No tasks found
```

Use the numbers from this list when marking, unmarking, or deleting tasks. Numbering starts at **1**. After deleting a task, run `list` again to check the updated numbers.

## Marking a task as done

**Format:** `mark <index>`

**Example:**

```text
mark 1
```

Marks the first task as done and displays the updated task.

## Marking a task as not done

**Format:** `unmark <index>`

**Example:**

```text
unmark 1
```

Marks the first task as not done and displays the updated task.

## Deleting a task

**Format:** `delete <index>`

**Example:**

```text
delete 2
```

Removes the second task and displays the task that was deleted.

## Finding tasks

**Format:** `find <keyword>`

**Example:**

```text
find book
```

Searches task descriptions for text containing `book` and displays the matches and their count. The search is case-insensitive, so it also matches descriptions containing `Book` or `BOOK`.

To mark or delete a matching task, use `list` to find its task number.

## Saving and exiting

**Command:** `bye`

Sozius attempts to save your tasks and then closes the window. Closing the window using its close button also attempts to save your tasks.

Tasks are stored in `tasks.txt` in the application's working directory and loaded when Sozius starts. Use the same working directory between launches to load the same task file. Keep a backup of this file if you want to preserve a separate copy of your tasks.

## Correcting command errors

If a command is rejected, check the following:

- Use a command from the summary table, with its command word in lowercase.
- Include a description when adding a task.
- Include `/by` and a date for deadlines.
- Include `/from` and `/to`, with both values, for events.
- Use an existing task number for `mark`, `unmark`, and `delete`.
- Use a whole-number index starting at 1, not 0 or a negative number.

For example, an unknown command produces:

```text
Error: unknown command
```

An invalid task number produces:

```text
Invalid command: Invalid index
```
