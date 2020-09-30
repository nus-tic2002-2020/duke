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
 * A GUI for {@code Duke} to ask for confirmation to delete {@code Note} objects in {@code Duke} using FXML.
 */
public class DeleteConfirm extends Application {

    final String notes;
    FXMLLoader fxmlLoader;

    public DeleteConfirm(String notes){
        this.notes = notes;
    }

    @Override
    public void start(Stage stage) throws IOException {

        fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/DeleteConfirmWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        fxmlLoader.<DeleteConfirmWindow>getController().setNotes(notes);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete Confirmation");
        stage.getIcons().add(new Image("/icons/Duke.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public boolean getConfirmation(){
        return fxmlLoader.<DeleteConfirmWindow>getController().getConfirmation();
    }

}
