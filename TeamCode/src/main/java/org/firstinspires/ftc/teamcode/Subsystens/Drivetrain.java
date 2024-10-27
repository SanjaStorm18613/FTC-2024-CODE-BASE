package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Drivetrain {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    // EXEMPLO TRAÇAO SIMPLES
    public Drivetrain(LinearOpMode Opmode) {
        leftDrive = Opmode.hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = Opmode.hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveToDropZone() {
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setTargetPosition(1000);
        rightDrive.setTargetPosition(1000);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);

        while (leftDrive.isBusy() && rightDrive.isBusy()) {
           //TODO
           // AJUSTAR REFERENCIAS CONFORME NECESSARIO
        }
    }

    public void drive(double drivePower, double turnPower) {
        leftDrive.setPower(drivePower + turnPower);
        rightDrive.setPower(drivePower - turnPower);
    }

    public void stop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}