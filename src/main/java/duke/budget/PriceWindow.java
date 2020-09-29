package duke.budget;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for {@code AskPrice}. Provides the layout for the other controls.
 */
public class PriceWindow extends AnchorPane {

    @FXML
    private TextField userInput;
    @FXML
    private Button confirmButton;
    @FXML
    private Label itemName;
    private String inputPrice;

    @SuppressWarnings("EmptyMethod")
    @FXML
    public void initialize() { }

    @FXML
    private void getPriceInput() {

        if(!this.userInput.getText().isEmpty()) {
            Stage stage = (Stage) this.userInput.getScene().getWindow();
            setInputPrice(this.userInput.getText());
            userInput.clear();
            stage.close();
        }
    }

    public void setItemName(String itemName) {
        this.itemName.setText(itemName + "?");
    }

    public void setInputPrice(String inputPrice){
        this.inputPrice = inputPrice;
    }

    public String getInputPrice(){
        return this.inputPrice;
    }

}

