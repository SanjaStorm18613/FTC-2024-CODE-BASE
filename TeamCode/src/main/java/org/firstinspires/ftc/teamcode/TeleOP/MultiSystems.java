package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystens.ArmMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.ClawMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystens.Linear;
import org.firstinspires.ftc.teamcode.Vision.CameraPermissionManager;

public class MultiSystems {
    private CameraPermissionManager permissionManager;

    public Drivetrain drivetrain;
    public ArmMechanism armMechanism;
    public ClawMechanism clawMechanism;
    public Linear linear;

    public MultiSystems(LinearOpMode Opmode ,ArmMechanism armMechanism, Linear linear, ClawMechanism claw) {
        this.armMechanism = armMechanism;
        this.linear = linear;
        this.clawMechanism = claw;
    }

    public void ArmLinear() {
        linear.setStateLinearArm(armMechanism.getState());
    }

    public void TakePiece() {
        armMechanism.setStateArm(0);
        ArmLinear();
        clawMechanism.releaseSample();
    }

    public void DeliveryPieceHighChamber() {
        armMechanism.setStateArm(3);
        ArmLinear();
    }

    public void DeliveryPieceLowBasket() {
        armMechanism.setStateArm(2);
        ArmLinear();
    }

    public void DeliveryPieceLowChamber() {
        armMechanism.setStateArm(1);
        ArmLinear();
    }
}
