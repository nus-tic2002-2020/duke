package duke;

import duke.commands.CommandException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;

/**
 * Controller for {@code Main}. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private static final Image userImage = new Image(MainWindow.class.getResourceAsStream("/images/DPUser.PNG"));
    private static final Image dukeImage = new Image(MainWindow.class.getResourceAsStream("/images/DPDuke.PNG"));

    @FXML
    public void initialize() throws ParseException, CommandException, IOException {
        this.scrollPane.vvalueProperty().bind(this.dialogueContainer.heightProperty());
        handleDukeReply(Duke.startUp());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, CommandException {

        if(!this.userInput.getText().isEmpty()) {
            String userText = this.userInput.getText();
            this.userInput.clear();
            this.dialogueContainer.getChildren().addAll(
                    DukeDialogueBox.getUserDialogue(userText, userImage)
            );
            handleDukeReply(duke.getResponse(userText));
        }
    }

    @FXML
    private void handleDukeReply(String dukeText) throws IOException {

        this.dialogueContainer.getChildren().addAll(
            DukeDialogueBox.getDukeDialogue(dukeText, dukeImage)
        );
        if(Duke.getIsConfirmedExit()) {
            Stage stage = (Stage) this.userInput.getScene().getWindow();
            stage.close();
        }
    }
}
