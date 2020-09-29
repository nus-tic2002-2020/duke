package duke.commands;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for {@code ExitConfirm}. Provides the layout for the other controls.
 */
public class ExitConfirmWindow extends AnchorPane {

    @FXML
    private Button confirmButton;
    @FXML
    private Button abortButton;

    private boolean isConfirmed;

    @SuppressWarnings("EmptyMethod")
    @FXML
    public void initialize() { }

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

    public boolean getConfirmation(){
        return this.isConfirmed;
    }

}

