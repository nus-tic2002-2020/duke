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

    @SuppressWarnings("unused")
    @FXML
    private Label text;
    @SuppressWarnings("unused")
    @FXML
    private ImageView displayPicture;

    private DukeDialogueBox(String text, Image img) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        this.text.setText(text);
        this.displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DukeDialogueBox getDukeDialogue(String text, Image img) throws IOException {
        var dukeReply = new DukeDialogueBox(text, img);
        if(Duke.getIsErrorReturn()) {
            dukeReply.setLabelBorderColour("#c14953");
        } else {
            dukeReply.setLabelBorderColour("#e4b363");
        }
        return dukeReply;
    }

    public static DukeDialogueBox getUserDialogue(String text, Image img) throws IOException {
        var userInput = new DukeDialogueBox(text, img);
        userInput.setLabelBorderColour("#058ed9");
        userInput.flip();
        return userInput;
    }

    public void setLabelBorderColour(String colour)  {
        String style = this.text.getStyle();
        this.text.setStyle(style + " -fx-border-color: " + colour + ";");
    }
}
