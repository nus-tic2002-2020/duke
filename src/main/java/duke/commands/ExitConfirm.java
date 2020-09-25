package duke.commands;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A GUI for {@code Duke} to ask for confirmation to exit {@code Duke} using FXML.
 */
public class ExitConfirm extends Application {

    FXMLLoader fxmlLoader;

    public ExitConfirm(){}

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/ExitConfirmWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Exit Confirmation");
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
