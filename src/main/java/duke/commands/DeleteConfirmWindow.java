package duke.commands;

import duke.ui.DukeListBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for {@code DeleteConfirm}. Provides the layout for the other controls.
 */
public class DeleteConfirmWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox noteContainer;
    @FXML
    private Button confirmButton;
    @FXML
    private Button abortButton;

    private boolean isConfirmed;

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.noteContainer.heightProperty());
    }

    @FXML
    private void exitConfirm() {
        this.isConfirmed = true;
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitAbort() {
        this.isConfirmed = false;
        Stage stage = (Stage) this.abortButton.getScene().getWindow();
        stage.close();
    }

    public void setNotes(String notes) {
        this.noteContainer.getChildren().addAll(
                DukeListBox.getDukeList(notes)
        );
    }

    public boolean getConfirmation(){
        return this.isConfirmed;
    }

}

