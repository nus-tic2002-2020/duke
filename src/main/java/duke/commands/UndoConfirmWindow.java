package duke.commands;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for {@code UndoConfirm}. Provides the layout for the other controls.
 */
public class UndoConfirmWindow extends AnchorPane {

    @FXML
    private Button confirmButton;
    @FXML
    private Button abortButton;

    private boolean isConfirmed;

    @FXML
    public void initialize() {

    }

    @FXML
    private void undoConfirm() {
        this.isConfirmed = true;
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void undoAbort() {
        this.isConfirmed = false;
        Stage stage = (Stage) this.abortButton.getScene().getWindow();
        stage.close();
    }

    public boolean getConfirmation(){
        return this.isConfirmed;
    }

}

