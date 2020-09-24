package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.*;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private ByteArrayOutputStream outputGUI = new ByteArrayOutputStream();
    private final PrintStream psConsole = System.out;
    private PrintStream streamOutputGUI = new PrintStream(outputGUI);

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private String dukeText = streamOutputGUI.toString();

    private Duke duke;

    private static final Image userImage = new Image(MainWindow.class.getResourceAsStream("/images/DPUser.PNG"));
    private static final Image dukeImage = new Image(MainWindow.class.getResourceAsStream("/images/DPDuke.PNG"));

    @FXML
    public void initialize() {
        System.setOut(streamOutputGUI);
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void runDuke() throws Exception {
        duke.run();
        handleDukeReply(dukeText);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        if(!userInput.getText().equals("")) {
            String userText = userInput.getText();
            dialogueContainer.getChildren().addAll(
                    DukeDialogueBox.getUserDialogue(userText, userImage)
            );
            InputStream inputGUI = new ByteArrayInputStream(userInput.getText().getBytes());
            System.setIn(inputGUI);
        }
        userInput.clear();
        handleDukeReply(outputGUI.toString());
    }

    @FXML
    private void handleDukeReply(String dukeText) {

        dialogueContainer.getChildren().addAll(
            DukeDialogueBox.getDukeDialogue(dukeText, dukeImage)
        );
    }
}
