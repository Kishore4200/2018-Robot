# Power Up!
Code for Team 670's Steamworks Robot
--------------------------
**Robot Code**

- [ ] DriveBase
  - [ ] Drive With Joystick
    - [ ] Field Centric
    - [ ] Driver Centric
  - [ ] Drive With Joystick
- [ ] Elevator
  - [ ] Up/Down movement
  - [ ]	Sensor to record position
  - [ ] Reset position to 0
  - [ ] Position Control
- [ ] Intake/Shooter
  - [ ] T.B.D
- [ ] Camera
  - [ ] Program subsystem from robot (On roborio)

**Sensors/Inputs**
NOTE: Remember your try catches, in case the robot sensor is unplugged

- [ ] Elevator
  - [ ] Choose sensor (Laser/Ultrasonic/BNO055)
  - [ ] Once chosen, program in Elevator subsystem
- [ ] navXMicro
  - [ ] DriveStraight
  - [ ] Pivoting procedures
- [ ] Vision
    - If offboard processor:
        - [ ] Detect box location, and pickup
        - [ ] Do 3d modeling
        - [ ] Send command to network tables to the robot/serial connection
    - If Onboard Roborio (as command)
        - [ ] Detect box location, and pickup

**Autonomous Code**

- [x] Do nothing (0pt.)
- [x] Move to line (5pts.)
- [x] Gear Auto (60 pts.)
- [x] |---All gear sides w/ vision (x3)
- [x]	|---All sides gear w/o vision (x1)

--------------------------
Guides

**Steps to flash a new RoboRIO**

[HERE](http://wpilib.screenstepslive.com/s/4485/m/24193/l/273817-updating-your-roborio-firmware)

**Steps to flash a new Router**

[HERE](https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986-programming-your-radio-for-home-use)

**Steps to install the Motor Controller, CTRE interface**

[HERE](https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot)
--------------------------
Updates

**(2/12/17):** 
Libraries needed for building/compiling the robot code: (install this software prior to opening the robot software, or you risk breaking everything):
- http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources
- https://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/
