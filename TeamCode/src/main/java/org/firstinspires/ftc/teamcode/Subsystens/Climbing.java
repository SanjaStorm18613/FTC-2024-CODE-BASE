package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Climbing {

   private DcMotor MotorClimbing;

   public Climbing(LinearOpMode Opmode) {
      MotorClimbing = Opmode.hardwareMap.get(DcMotor.class,  "Motor_climbing");

   }

   public void raiseClimbing() {
     MotorClimbing.setPower(1);

   }

   public void lowerClimbing(){
      MotorClimbing.setPower(-1);

   }

   public void stopClimbing(){
      MotorClimbing.setPower(0);

   }


}
