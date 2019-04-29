# Car Simulator

My very first JavaFX project that I took seriously and a very first GUI project in general. Inspiration for this project came from an assigment I had to do at my university as a last project of our OOP (objective oriented programing) classes, we had to create a GUI responsive 
Car simulator that had such capabilities as: *speed up*, *slow down*, *brakes*, *change gears*, *turn car on and off*, *show RPM*, *show Speed*.

## Authors

* **Darek**  - [DaRoTP](https://github.com/DaRoTP)

## What can it do ?
* **Turn car On and Off** - you can not do any other actions like speed up change gears ect. if you haven't turned the car *ON*, to do so
you have to click the button on the bottom right of the window that says **START** then Label will change to **STOP**, to turn off the car click that same button again.
* **Change gears** - you have 8 gears to chose from (R, N, 1, 2, 3, 4, 5, 6) to change gears use your keyboard buttons **A** - gear down **D** - gera up. When turnong on the car you strat with your gear on *N* to start accelerating change gears to any other gear than *N*. Each gear has it's own speed limit it can reach.
* **Accelerate** - to accelterate press **W**, max speed you can reach is 220 km/h at 6th gear, each gear has it's speed limit althought you can still reach 220 m/h mark on 1st gear but it will take you a longer time than if you accelerate with appropriate gears. 
* **Brake** - to brake press **S**, very simple, when braking speed and rpms go down.
* **Turn signlas** - press **Q** for a lefr turn signal and **E** for a right turn signal, to turn them off click the same button again, both signals can't be active at the same time.
* **Help** - on top left corner there is a button, when clicked displays an image with an instruction on car contorls.
* **Custom window controlls** - I used an undecorated stage option and added and stylized controls later, added minimize, maximize, exit and draggable window options. 
## Bugs
* TBD (to be discovered)

## Tools used to build this 

* JavaFX Scene Builder 8.5.0
* Java Version 8 (build 1.8.0_201-b09)
