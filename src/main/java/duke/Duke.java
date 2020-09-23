package duke;

import duke.commands.CommandException;
import duke.commands.DateException;
import duke.commands.DukeCommand;
import duke.parser.DukeParser;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

/**
 * {@code Duke} is a note-keeper, task manager, budget assistant, and more...!
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Duke extends Application implements DukeParser, DukeUI {

    //DUKE VARIABLES------------------------------------
    private static DukeStorage dukeStorage;
    private static DukeList dukeNotes;
    boolean isLoadedFromFile;


    //JAVAFX VARIABLES----------------------------------
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DPUser.PNG"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DPDuke.PNG"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Label label;


    //JAVA FX CODES-------------------------------------
    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();


        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(1000.0);
        stage.setMinWidth(800.0);

        mainLayout.setPrefSize(800.0, 1000.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        scrollPane.setPrefSize(785, 935);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        sendButton.setPrefWidth(105.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        userInput.setPrefWidth(675.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);

        sendButton.setOnMouseClicked((event) -> {
            if(!userInput.getText().equals("")) {
                handleUserInput();
            }
        });

        userInput.setOnAction((event) -> {
            if(!userInput.getText().equals("")) {
                handleUserInput();
            }
        });

    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DukeDialogueBox.getUserDialogue(userText, new ImageView(user)),
                DukeDialogueBox.getDukeDialogue(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }



    //RUN DUKE------------------------------------------
    /**
     * This method constructs a {@code Duke} object.
     *
     * @param path The path to the saved files in the hard drive..
     */
    public Duke(String path){

        dukeStorage = new DukeStorage(path);

        try {
            dukeNotes = new DukeList(dukeStorage.readFromFile());
            isLoadedFromFile = true;

        } catch (IOException | ParseException e) {
            dukeNotes = new DukeList();
            isLoadedFromFile = false;
        }
    }

    /**
     * This method is used to initialise a {@code Duke} object.
     *
     */
    public Duke(){}

    /**
     * This method run a {@code Duke} object.
     *
     * @exception ParseException If there is an error in reading and understanding inputs.
     * @exception IOException If (@code Note} or {@code File} objects specified could not be found.
     * @exception CommandException If there are errors in the command input.
     */
    public void run() throws ParseException, IOException, CommandException {

        boolean confirmExit = false;

        //Get Date & Time on startup
        Date now = new Date();

        //Run startup sequence
        DukeUI.printOnStartup(now, isLoadedFromFile);

        while(!confirmExit) {
            String input = DukeUI.receiveCommand();
            try {
                DukeCommand dukeCommand = DukeParser.readCommand(input);
                assert dukeCommand != null;
                dukeCommand.execute(dukeNotes, dukeStorage);
                confirmExit = dukeCommand.getConfirmExit();

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                DukeUI.printDivider();
                System.out.println("\tI don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                e.printStackTrace();
                System.out.println("\tThe task(s) you mentioned cannot be found.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.suggestListNotes();
                DukeUI.printDivider();

            } catch (NumberFormatException | ParseException e) {
                DukeUI.printDivider();
                System.out.println("\tI don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tThe attribute(s) you mentioned cannot be understood.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.printDivider();

            } catch (CommandException e) {
                e.printExplanation(input);

            } catch (DateException e) {
                e.printExplanation();
            }
        }
    }


    //DUKE MAIN-----------------------------------------
    public static void main(String[] args) throws Exception {
        String path = "data/notes.txt";
        new Duke(path).run();

    }
}
