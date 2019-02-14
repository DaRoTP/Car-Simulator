package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //BorderPane root = new BorderPane();

            Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
            Scene scene = new Scene(root,1372,707);
            scene.getStylesheets().add(getClass().getResource("/sample/application.css").toExternalForm());
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Car simulator");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}