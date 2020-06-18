package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
 //       setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
// Order or Precedence of CSS
// 1. In-line CSS
// 2. FXML style sheet
// 3. Applications theme style sheet (Main)

// more effects at javafx.styles.effects