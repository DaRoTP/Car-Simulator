package Model;

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
    public int getRPM() { return rpm; }
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
        /* if max speed of a specific gear is reached, acceleration is reduced to 0.05 */
        if(getGear() != "N")
        {
            //SPEED
            if(this.speed < 220)
            {
                switch (gear)
                {
                    case "R": {
                        if (this.speed < 20)
                            this.speed += 0.5;
                    }
                    break;
                    case "1": {
                        if (this.speed < 30)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                    case "2": {
                        if (this.speed < 50)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                    case "3": {
                        if (this.speed < 70)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                    case "4": {
                        if (this.speed < 100)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                    case "5": {
                        if (this.speed < 150)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                    case "6": {
                        if (this.speed < 220)
                            this.speed += 0.5;
                        else
                            this.speed += 0.05;
                    }
                    break;
                }
            }

            //RPM
            /* when tpm reaches 6010 and higher it only in increments by 1 until 8100*/
            if(this.rpm < 6010)
                this.rpm += 11;

            if(this.rpm > 6010 && this.rpm < 8100)
                this.rpm += 1;

        }

        /* when gear is on "N" max speed is 0 km/h (can't accelerate) and rpm can go up to 6100 */
        else if(getGear() == "N")
        {
            if(this.rpm < 6100)
                this.rpm += 11;
        }
    }

    //SLOW DOWN
    public void slow_down()
    {
        //speed
        if(this.speed > 0)
            this.speed -= 0.5;
        //rpm
        /* when gear is on "N" rpm can not go below 1100 */
        if(this.rpm > 0)
            if(this.gear == "N")
            {
                if (this.rpm > 1100)
                    this.rpm -= 11;
            }
            else
                this.rpm -= 11;

         if(this.rpm < 0)
             this.rpm = 0;
    }

    //CHANGE GEARS
    /*each Gear ( R, N, 1, 2...) is assigned to an int
      * functions takes in an int value and changes gear variable to a specified gear character */
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
            case 6:
                setGear("6");
                break;
        }
    }


}