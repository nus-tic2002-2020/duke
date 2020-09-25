package duke.ui;

import duke.commands.DeleteConfirmWindow;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class DukeListBox extends HBox {

    @FXML
    private Label text;

    private DukeListBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DeleteConfirmWindow.class.getResource("/view/ListBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
    }

    public static DukeListBox getDukeList(String text) {

        return new DukeListBox(text);
    }
}
