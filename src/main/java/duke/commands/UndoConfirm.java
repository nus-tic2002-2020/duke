package duke.commands;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for {@code Duke} to ask for confirmation to undo {@code Duke} using FXML.
 */
public class UndoConfirm extends Application {

    FXMLLoader fxmlLoader;

    public UndoConfirm(){}

    @Override
    public void start(Stage stage) throws IOException {

        fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/UndoConfirmWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Undo Confirmation");
        stage.getIcons().add(new Image("/icons/Duke.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public boolean getConfirmation(){
        return fxmlLoader.<UndoConfirmWindow>getController().getConfirmation();
    }

}
