package duke.budget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A GUI for {@code Duke}  to ask for price using FXML.
 */
public class AskPrice extends Application {

    final String itemName;
    FXMLLoader fxmlLoader;

    public AskPrice(String itemName){
        this.itemName = itemName;
    }

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/PriceWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<PriceWindow>getController().setItemName(itemName);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Confirm Item Price");
            stage.getIcons().add(new Image("/icons/Duke.png"));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String returnPrice(){
        return fxmlLoader.<PriceWindow>getController().getInputPrice();
    }

}
