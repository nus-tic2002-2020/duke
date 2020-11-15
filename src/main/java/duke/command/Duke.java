package duke.command;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.io.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.*;

/**
 * Duke application main logic.
 * Supports javafx GUI using {@link Application} class.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaAhDuke.png"));

    private static final String FILE_PATH = "data/tasks.ssv";
    private static final String DATA_SEPARATOR = "|";

    protected Storage storageTasks;
    protected TaskManager taskManager;
    protected Ui ui;

    /**
     * Default Constructor using FILE_PATH constant.
     */
    public Duke() {
        this(FILE_PATH);
    }

    /**
     * Constructor using custom file path for tasks data.
     * @param filePath for tasks data saved on disk
     */
    public Duke(String filePath) {
        storageTasks = new Storage(filePath);
        ui = new Ui();

        try {
            List<String> entries = storageTasks.load();
            taskManager = new TaskManager();
            taskManager.setTasksFromRaw(entries, DATA_SEPARATOR);
        } catch (DukeException | IOException e) {
            ui.loadError();
            taskManager = new TaskManager();
        }
    }

    /**
     * Run Duke routing for command line.
     */
    public void run() {
        ui.welcome();

        boolean exit = false;
        boolean printEndLine = false;
        ui.greet();

        while(!exit) { // If no error, continue
            try {
                String fullCommand = ui.readCommand();

                if (fullCommand == null || fullCommand.isBlank()) { // Do not parse command
                    continue;
                } else {
                    printEndLine = true;
                }

                ui.echoLine();
                Command command = Command.parse(fullCommand);
                command.execute(taskManager, ui, storageTasks);
                exit = command.isExit();

            } catch (DukeException e) {
                e.printError();
            } finally {
                if (printEndLine) {
                    ui.echoLine();
                    printEndLine = false;
                }
            }
        }

    }

    /**
     * Main method entry point for duke application command line
     * @param args from Java options
     */
    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

    /**
     * GUI start. Initialise all GUI components and handle user inputs.
     * @param stage
     * @throws Exception
     * @see Application
     */
    @Override
    public void start(Stage stage) throws Exception {
        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.
        */

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Ah Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show welcome message
        Label dukeWelcome = getDialogLabel(ui.welcome() + "\n" + ui.greet());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeWelcome, new ImageView(duke))
        );

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }
    */

    /**
     * Iteration 3
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Process user input from GUI
     * @param input from user as raw command string
     * @return command output string
     */
    private String getResponse(String input) {
        //return "Duke heard: " + input;

        List<String> outputList = new ArrayList<String>();

        try {
            Command command = Command.parse(input);
            command.execute(taskManager, ui, storageTasks);
            outputList = command.getOutputs();
        } catch (DukeException e) {
            return e.printError();
        }

        StringBuilder builder = new StringBuilder();
        for (String s: outputList) {
            builder.append(s + "\n");
        }

        return builder.toString();
    }
}