package duke.commands;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A GUI for {@code Duke} to ask for confirmation to wipe {@code Duke} using FXML.
 */
public class WipeConfirm extends Application {

    FXMLLoader fxmlLoader;

    public WipeConfirm(){}

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/WipeConfirmWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Wipe Confirmation");
            stage.getIcons().add(new Image("/images/DPDuke.png"));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getConfirmation(){
        return fxmlLoader.<ExitConfirmWindow>getController().getConfirmation();
    }

}
