package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{


    //VARIABLES
//-------------------------------------------->
    private Car auto = new Car(); //object of a class Car
    private int gr = 0; //changing gears variable
    private boolean maximizebool = false;
    private boolean change_img = false;
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean check = true;


    //IMAGES
//-------------------------------------------->
    private Image car_on = new Image("sample/resources/car_on1.png");
    private Image car_off = new Image("sample/resources/car_off.png");
    private Image help = new Image("sample/resources/help.png");

    //PANES
//-------------------------------------------->
    @FXML
    private GridPane grid = new GridPane();
    @FXML
    private StackPane stack = new StackPane();
    @FXML
    private AnchorPane anchor = new AnchorPane();
    @FXML
    private Pane dragable = new Pane();

    //IMAGES
//-------------------------------------------->
    @FXML
    private ImageView car_hud;
    @FXML
    private ImageView Info_Image;

    //LABELS
//-------------------------------------------->
    @FXML
    private Label speedometer_label;
    @FXML
    private Label tachometer_label;
    @FXML
    private Label gear_label;
    @FXML
    private Label Start_Stop;

    //BUTTONS
//-------------------------------------------->
    @FXML
    private Button Engin_ON_OFF;
    @FXML
    private Button Exit;
    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private Button help_btn;

    //PROGRESS INDICATOR
//-------------------------------------------->
    @FXML
    private ProgressIndicator speed_guage;
    @FXML
    private ProgressIndicator rpm_guage;


// MAXIMIZE MINIMIZE EXIT STAGE

    /*exit */
    @FXML
    public void exit_x(){
        Platform.exit();
    }
    /*minimize */
    @FXML
    public void minimize_(ActionEvent event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    /* maximize */
    @FXML
    public void maximize_(ActionEvent event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        maximizebool = !maximizebool;

            stage.setMaximized(maximizebool);
    }

    /* HELP */
    public void Info(){
        change_img = !change_img;
        if(change_img)
            Info_Image.setImage(help);
        else
            Info_Image.setImage(null);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        makeDrgable();
        update_lable();


    }

    //DRAGABLE STAGE
//-------------------------------------------->
    private void makeDrgable(){
        dragable.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        dragable.setOnMouseDragged((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getSceneX() - xOffset);
            stage.setY(event.getSceneY() - yOffset);
        });
    }

    //TURN ENGINE ON OR OF
//-------------------------------------------->
    public void turn_ON_OF()
    {
        auto.setRunning(!auto.isRunning());

        if(auto.isRunning())
        {
            car_hud.setImage(car_on);
            auto.setRPM(1120);
            Start_Stop.setText("STOP");
            Start_Stop.setStyle(" -fx-text-fill: #DB37B7;  ");
            tachometer_label.setText(Integer.toString(auto.getrpm()));
            auto.change_gears(gr);
            gear_label.setText(auto.getGear());

        }
        else
        {
            car_hud.setImage(car_off);
            auto.setRPM(0);
            auto.setSpeed(0);
            speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
            Start_Stop.setText("START");
            Start_Stop.setStyle(" -fx-text-fill: #000430;  ");
            tachometer_label.setText(Integer.toString(auto.getrpm()));
        }
    }

    //ON KEYPRESSED
//-------------------------------------------->
    @FXML
    public void keyPressed(KeyEvent ev)
    {
        /*wszystkie dzialania mozna wykonywac tylko kiedy samochod jest wlaczony*/
        if(auto.isRunning())
        {
            switch(ev.getCode())
            {
                /*przyspieszenie - uzuwamy funkcji classy car "speed_up" i aktualizujemy etykiety*/
                case W:
                {
                    auto.speed_up();
                    speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                    tachometer_label.setText(Integer.toString(auto.getrpm()));
                    check = false;
                }
                break;
                /*hamowanie - uzuwamy funkcji classy car "slow_down" i aktualizujemy etykiety*/
                case S:
                {
                    auto.slow_down();
                    speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                    tachometer_label.setText(Integer.toString(auto.getrpm()));
                }
                break;
                /*zmieniamy bieg w dol - bieg mozemy zmieniac tylko po kolei,
                 * nie zmieniamy biegu np. z 5 na 2
                 * kiedy przechodzimy do biegu nizszego np. z 4 na 3 i obroty sa > 2500 to dodajemy +1800 obrotow
                 * a jezeli obroty  < 1600 dodajemy +1600 */
                case A:
                {
                    gr--;
                    if(gr == -2)
                        gr = -1;
                    auto.change_gears(gr);
                    gear_label.setText(auto.getGear());
                    if(gr != -1 && gr != 0 && gr != 1)
                    {
                        if(auto.getrpm() > 2500 && auto.getrpm() < 6100)
                        {
                            auto.setRPM(auto.getrpm()+1800);
                            tachometer_label.setText(Integer.toString(auto.getrpm()));
                        }
                        else if(auto.getrpm() < 1600)
                        {
                            auto.setRPM(auto.getrpm()+1100);
                            tachometer_label.setText(Integer.toString(auto.getrpm()));
                        }
                    }
                }
                break;
                /*zmieniamy bieg w gore - */
                case D:
                {
                    gr++;
                    if(gr == 7)
                        gr = 6;
                    auto.change_gears(gr);
                    gear_label.setText(auto.getGear());
                }
                break;
                default:
                    break;
            }
        }
    }
    @FXML
    public void keyReleased(KeyEvent event){
        if(auto.isRunning())
            if(event.getCode() == KeyCode.W){
                check = true;
            }

    }

    private void update_lable(){
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(100),
                        even -> {
                            if(check)
                                auto.slow_down();
                            speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                            tachometer_label.setText(Integer.toString(auto.getrpm()));
                            speed_guage.setProgress(auto.getSpeed()*0.0028);
                            rpm_guage.setProgress(auto.getrpm()*0.000085);

                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




}