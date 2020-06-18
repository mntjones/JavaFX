package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {

    @FXML
    private Label label;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;

    public void initialize() {
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick() {
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Save Application File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("All", "*.*")
        );

        List<File> file = fileChooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        //File file = fileChooser.showOpenDialog(gridPane.getScene().getWindow());
        // DirectoryChooser dirChooser = new DirectoryChooser();
        //File file = dirChooser.showDialog(gridPane.getScene().getWindow());

        if(file != null) {
            for(int i=0; i<file.size(); i++) {
                System.out.println(file.get(i));
            }
            //System.out.println(file.getPath());
        }
        else {
            System.out.println("Chooser was cancelled");
        }
    }
}
