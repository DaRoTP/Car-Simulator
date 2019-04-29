package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Model.Car;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{

    //VARIABLES
//-------------------------------------------->
    private Car auto = new Car(); //object of a class Car
    private int gear_int = 0; //changing gears variable
    private boolean check = true; //checking if accelerating

    private boolean maximizable = false; //check if window is maximized or not (false = not, true = yes)
    private boolean change_img = false; //check if image is displayed (false = not, true = yes)

    private double xOffset = 0; //draggable stage x offset
    private double yOffset = 0; //draggable stage y offset

    private double blinker = 0; // opacity control off blinker lights
    private int turn_signal = 0; // activate blinkers 0 - both turned off 1 - left blinker 2 - right blinker

    private Date date = new Date(); //get date
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //display date in this format


    //IMAGES
//-------------------------------------------->
    private Image car_on = new Image("View/images/car_on.png");
    private Image car_off = new Image("View/images/car_off.png");
    private Image help = new Image("View/images/help.png");

    //PANES
//-------------------------------------------->
    @FXML
    private Pane draggable = new Pane();

    //IMAGES
//-------------------------------------------->
    @FXML
    private ImageView car_hud;
    @FXML
    private ImageView Info_Image;
    @FXML
    private ImageView arrow_left;
    @FXML
    private ImageView arrow_right;

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
    @FXML
    private Label Date_label;


    //PROGRESS INDICATOR
//-------------------------------------------->
    @FXML
    private ProgressIndicator speed_gauge;
    @FXML
    private ProgressIndicator rpm_gauge;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        makeDraggable();
        update_label();
        arrow_left.setOpacity(0);
        arrow_right.setOpacity(0);
    }

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
        maximizable = !maximizable;

        stage.setMaximized(maximizable);
    }

    /* HELP */
    public void Info(){
        change_img = !change_img;
        if(change_img)
            Info_Image.setImage(help);
        else
            Info_Image.setImage(null);
    }

    //DRAGGABLE STAGE
//-------------------------------------------->
    private void makeDraggable(){

        draggable.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        draggable.setOnMouseDragged(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    //TURN LIGHTS
//-------------------------------------------->
    public void turn_lights()
    {
        blinker = (blinker + 0.1) % 1;

        if(blinker == 0)
            blinker = 1;
        else if(blinker == 0.99)
            blinker = 0;

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
            auto.change_gears(gear_int);
            Start_Stop.setText("STOP");
            Start_Stop.setStyle(" -fx-text-fill: #DB37B7;  ");
            tachometer_label.setText(Integer.toString(auto.getRPM()));
            gear_label.setText(auto.getGear());
            Date_label.setText(sdf.format(date));

        }
        else
        {
            car_hud.setImage(car_off);
            auto.setRPM(0);
            auto.setSpeed(0);
            Start_Stop.setText("START");
            Start_Stop.setStyle(" -fx-text-fill: #000430;  ");
            tachometer_label.setText(Integer.toString(auto.getRPM()));
            speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
            Date_label.setText("");
            turn_signal = 0;
        }
    }

    //ON KEYPRESSED
//-------------------------------------------->
    @FXML
    public void keyPressed(KeyEvent ev)
    {
        /* all actions can be done only when the car is "ON" (isRunning = true)*/
        if(auto.isRunning())
        {
            switch(ev.getCode())
            {
                /* LEFT turn signal */
                case Q:
                {
                    if(turn_signal == 0)
                        turn_signal = 1;
                    else if(turn_signal == 1)
                        turn_signal = 0;
                }
                    break;
                /* RIGHT turn signal */
                case E:
                {
                    if(turn_signal == 0)
                        turn_signal = 2;
                    else if(turn_signal == 2)
                        turn_signal = 0;
                }
                    break;
                /* ACCELERATE */
                case W:
                {
                    auto.speed_up();
                    check = false;
                }
                    break;
                /* BRAKE */
                case S:
                {
                    auto.slow_down();
                }
                    break;
                /* GEAR DOWN */
                case A:
                {
                    gear_int--;
                    if(gear_int == -2)
                        gear_int = -1;

                    auto.change_gears(gear_int);
                    gear_label.setText(auto.getGear());

                    if(gear_int != -1 && gear_int != 0 && gear_int != 1)
                    {
                        if(auto.getRPM() > 2500 && auto.getRPM() < 8100) /* when gearing down from 6,5,4,3,2 gears and RPM > 2500 then RPM += 1800 */
                        {
                            auto.setRPM(auto.getRPM()+1800);
                            tachometer_label.setText(Integer.toString(auto.getRPM()));
                        }
                        else if(auto.getRPM() < 1600) /* when gearing down from 6,5,4,3,2 gears and RPM < 1600 then RPM += 1100 */
                        {
                            auto.setRPM(auto.getRPM()+1100);
                            tachometer_label.setText(Integer.toString(auto.getRPM()));
                        }
                    }
                }
                break;
                /* GEAR UP */
                case D:
                {
                    gear_int++;
                    if(gear_int == 7)
                        gear_int = 6;

                    auto.change_gears(gear_int);
                    gear_label.setText(auto.getGear());
                }
                break;

                default:
                    break;
            }
        }
    }
    /* when acceleration key is released activate function "slow down" */
    @FXML
    public void keyReleased(KeyEvent event){
        if(auto.isRunning())
            if(event.getCode() == KeyCode.W){
                check = true;
            }

    }
    /* UPDATE EVERY 100 milliseconds
     * updating most of the GUI animated objects like Labels, progress indicators, Opacity of images etc...*/
    private void update_label()
    {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(100),
                        even -> {
                            if(check)
                                auto.slow_down();

                            speedometer_label.setText(Integer.toString((int)auto.getSpeed()));
                            tachometer_label.setText(Integer.toString(auto.getRPM()));

                            speed_gauge.setProgress(auto.getSpeed()*0.0028);
                            rpm_gauge.setProgress(auto.getRPM()*0.000085);

                            if(turn_signal == 1)// LEFT turn blinker
                            {
                                turn_lights();
                                arrow_left.setOpacity(blinker);
                            }
                            else if(turn_signal == 2) // RIGHT turn blinker
                            {
                                turn_lights();
                                arrow_right.setOpacity(blinker);
                            }
                            else if(turn_signal == 0) // No blinkers
                            {
                                arrow_right.setOpacity(0);
                                arrow_left.setOpacity(0);
                            }

                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




}