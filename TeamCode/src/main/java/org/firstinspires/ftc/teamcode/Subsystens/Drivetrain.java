package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TeleOP.MultiSystems;

public class Drivetrain {

    private DcMotor BackLeftDrive;
    private DcMotor BackRightDrive;
    private DcMotor FrontLeftDrive;
    private DcMotor FrontRightDrive;

    // EXEMPLO TRAÇAO SIMPLES
    public Drivetrain(LinearOpMode Opmode) {
        BackLeftDrive = Opmode.hardwareMap.get(DcMotor.class, "back_left_drive");
        BackRightDrive = Opmode.hardwareMap.get(DcMotor.class, "back_right_drive");
        FrontLeftDrive = Opmode.hardwareMap.get(DcMotor.class, "front_left_drive");
        FrontRightDrive = Opmode.hardwareMap.get(DcMotor.class, "front_right_drive");

        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveToDropZone() {
        BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BackLeftDrive.setTargetPosition(1000);
        BackRightDrive.setTargetPosition(1000);

        BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        BackLeftDrive.setPower(0.5);
        BackRightDrive.setPower(0.5);

        while (BackLeftDrive.isBusy() && BackRightDrive.isBusy()) {
           //TODO
           // AJUSTAR REFERENCIAS CONFORME NECESSARIO
        }
    }

    public void driveTele(double frontPower, double sidePower, double turnPower, double stopPower) {
        BackLeftDrive.setPower((frontPower + sidePower - turnPower) * -(stopPower - 1));
        BackRightDrive.setPower((frontPower - sidePower + turnPower) * -(stopPower - 1));
        FrontLeftDrive.setPower((frontPower + sidePower + turnPower) * -(stopPower - 1));
        FrontRightDrive.setPower((frontPower - sidePower - turnPower) * -(stopPower - 1));
    }

    public void autoFront(double frontPower) {
        BackLeftDrive.setPower(frontPower);
        BackRightDrive.setPower(frontPower);
        FrontLeftDrive.setPower(frontPower);
        FrontRightDrive.setPower(frontPower);
    }

    public void autoSide(double sidePower) {
        BackLeftDrive.setPower(-sidePower);
        BackRightDrive.setPower(sidePower);
        FrontLeftDrive.setPower(sidePower);
        FrontRightDrive.setPower(-sidePower);
    }

    public void autoTurn(double turnPower) {
        BackLeftDrive.setPower(-turnPower);
        BackRightDrive.setPower(turnPower);
        FrontLeftDrive.setPower(-turnPower);
        FrontRightDrive.setPower(turnPower);
    }

    public void stop() {
        BackLeftDrive.setPower(0);
        BackRightDrive.setPower(0);
        FrontLeftDrive.setPower(0);
        FrontRightDrive.setPower(0);
    }
}