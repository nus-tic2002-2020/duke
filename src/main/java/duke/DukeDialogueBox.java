package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
public class DukeDialogueBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DukeDialogueBox(Label l, ImageView iv) {

        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public DukeDialogueBox(){}

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DukeDialogueBox getDukeDialogue(Label l, ImageView iv) {
        return new DukeDialogueBox(l, iv);
    }

    public static DukeDialogueBox getUserDialogue(Label l, ImageView iv) {
        var db = new DukeDialogueBox(l, iv);
        db.flip();
        return db;
    }
}
