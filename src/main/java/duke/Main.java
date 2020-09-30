package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for {@code Duke}  using FXML.
 */
public class Main extends Application {

    final String path = "data/notes.txt";
    private final Duke duke = new Duke(path);

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
        stage.setTitle("Project Duke");
        stage.getIcons().add(new Image("/icons/Duke.png"));
        stage.setResizable(false);
    }

}