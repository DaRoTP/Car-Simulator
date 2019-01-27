package sample;

public class Car
{
    //VARIABLES
//----------------------------------------->
    private boolean running;
    private int rpm;
    private double speed;
    private String gear;

    //GETTERS & SETTERS
//----------------------------------------->
    //running
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    //RPM
    public int getrpm() {
        return rpm;
    }
    public void setRPM(int rpm) {
        this.rpm = rpm;
    }
    //seed
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    //gear
    public String getGear() {
        return gear;
    }
    public void setGear(String gear) {
        this.gear = gear;
    }

    //CONSTRUCTOR
//----------------------------------------->
    public Car()
    {
        super();
        this.running = false;
        this.rpm = 0;
        this.speed = 0;
        this.gear = "N";
    }

    //METHODS
//----------------------------------------->

    //SPEED UP
    public void speed_up()
    {
        /* jezeli wlaczony dowolny bieg poza 'R' i 'N' to maksymalna predkosc jest 180 km/h i 3010 obrotow */
        if(getGear() != "R" && getGear() != "N")
        {
            //speed
            if(this.speed < 24)
                if(this.gear == "1")
                    this.speed += 0.5;
            if(this.speed < 43)
                if(this.gear == "2")
                    this.speed += 0.5;
            if(this.speed < 92)
                if(this.gear == "3")
                    this.speed += 0.5;
            if(this.speed < 121)
                if(this.gear == "4")
                    this.speed += 0.5;
            if(this.speed < 180)
                if(this.gear == "5")
                    this.speed += 0.5;
            //RPM
            if(this.rpm < 3010)
                this.rpm += (5000 - this.rpm)*0.01;
        }
        /* jezeli wlaczony bieg "R" to mozemy osiagnac maksymalna predkosc 20 km/h i 2100 obrotow */
        else if(getGear() == "R")
        {
            if(this.speed < 20)
                this.speed += 0.5;
            if(this.rpm < 2100)
                this.rpm += (5000 - this.rpm)*0.01;
        }
        /* jezeli wlaczony bieg "N" to mozemy osiagnac maksymalna predkosc 0 km/h i 3100 obrotow */
        else if(getGear() == "N")
        {
            if(this.rpm < 3100)
                this.rpm += (5000 - this.rpm)*0.01;
        }
    }

    //SLOW DOWN
    public void slow_down()
    {
        //speed
        if(this.speed > 0)
            this.speed -= 0.5;
        //rpm
        if(this.gear == "1" || this.gear == "N" || this.gear == "R")
        {
            if(this.rpm > 1100)
                this.rpm -= this.rpm*0.03;
        }
        else
        {
            if(this.rpm > 0)
                this.rpm -= this.rpm*0.03;
        }

    }

    //CHANGE GEARS
    public void change_gears(int n)
    {
        switch (n)
        {
            case -1:
                setGear("R");
                break;
            case 0:
                setGear("N");
                break;
            case 1:
                setGear("1");
                break;
            case 2:
                setGear("2");
                break;
            case 3:
                setGear("3");
                break;
            case 4:
                setGear("4");
                break;
            case 5:
                setGear("5");
                break;
        }
    }


}