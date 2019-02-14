package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int gr = 0; //changing gears variable
    boolean maximizebool = false;
    private double xOffset = 0;
    private double yOffset = 0;

    private Image car_on = new Image("sample/resources/car_on.png");
    private Image car_off = new Image("sample/resources/car_off.png");


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

    //LABELS
//-------------------------------------------->
    @FXML
    private Label speedometer_label;
    @FXML
    private Label tachometer_label;
    @FXML
    private Label gear_label;

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


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            update_lable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //TURN ENGINE ON OR OF
//-------------------------------------------->
    @FXML
    public void turn_ON_OF(ActionEvent event)
    {
        auto.setRunning(!auto.isRunning());

        if(auto.isRunning())
        {
            car_hud.setImage(car_on);
            auto.setRPM(1120);
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
                case UP:
                {
                    auto.speed_up();
                    speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                    tachometer_label.setText(Integer.toString(auto.getrpm()));
                }
                break;
                /*hamowanie - uzuwamy funkcji classy car "slow_down" i aktualizujemy etykiety*/
                case DOWN:
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
                case LEFT:
                {
                    gr--;
                    if(gr == -2)
                        gr = -1;
                    auto.change_gears(gr);
                    gear_label.setText(auto.getGear());
                    if(gr != -1 && gr != 0 && gr != 1)
                    {
                        if(auto.getrpm() > 2500)
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
                case RIGHT:
                {
                    gr++;
                    if(gr == 6)
                        gr = 5;
                    auto.change_gears(gr);
                    gear_label.setText(auto.getGear());
                }
                break;
                default:
                    break;
            }
        }
    }
    /*funkcja ktora aktualizuje etykiety i wykonuje funkcje "slow_down()" - hamowania co 200 milisekund */
    public void update_lable() throws InterruptedException {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(200),
                        even -> {
                            auto.slow_down();
                            speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                            tachometer_label.setText(Integer.toString(auto.getrpm()));
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



}