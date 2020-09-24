package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class DukeDialogueBox extends HBox {

    @FXML
    private Label text = new Label();
    @FXML
    private ImageView displayPicture = new ImageView();

    private DukeDialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.displayPicture.setImage(img);
    }


    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DukeDialogueBox getDukeDialogue(String text, Image img) {
        return new DukeDialogueBox(text, img);
    }

    public static DukeDialogueBox getUserDialogue(String text, Image img) {
        var db = new DukeDialogueBox(text, img);
        db.flip();
        return db;
    }
}
