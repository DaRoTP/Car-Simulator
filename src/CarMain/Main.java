package CarMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //BorderPane root = new BorderPane();

            Parent root = FXMLLoader.load(getClass().getResource("/View/CarHud.fxml"));
            Scene scene = new Scene(root,1372,707);
            scene.getStylesheets().add(getClass().getResource("/View/CarHudStyle.css").toExternalForm());
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Car simulator");
            Image logo_icon = new Image("View/images/logo.png");
            primaryStage.getIcons().add(logo_icon);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}