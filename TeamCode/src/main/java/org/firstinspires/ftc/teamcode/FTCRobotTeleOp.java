package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="FTCRobotTeleOp", group="FTC")
public class FTCRobotTeleOp extends LinearOpMode {

    //apenas para CI
    //TODO
    // ajudar a implentar o opencv nessa bagaça. ci ta delisgado
    private CameraPermissionManager permissionManager;

    private Drivetrain drivetrain;
    private ArmMechanism armMechanism;
    private ClawMechanism clawMechanism;
    @Override
    public void runOpMode() {
        // Inicializa o robô e gerencia as permissões
        initializeRobot();
        checkAndRequestPermissions();

        telemetry.addData("Status", "Aguardando início...");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Controle do movimento do robô com traçao simples
            //TODO
            //AJUSTAR CONFORME ESPECIFICAÇAO
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            drivetrain.drive(drive, turn);

            if (gamepad1.dpad_up) {
                armMechanism.raiseArm();
            } else if (gamepad1.dpad_down) {
                armMechanism.lowerArm();
            }

            if (gamepad1.a && !clawMechanism.isClawClosed()) {
                clawMechanism.grabSample();
            } else if (gamepad1.b) {
                clawMechanism.releaseSample();
            }
        }
    }

    private void initializeRobot() {
        drivetrain = new Drivetrain(hardwareMap);
        armMechanism = new ArmMechanism(hardwareMap);
        clawMechanism = new ClawMechanism(hardwareMap);
        permissionManager = new CameraPermissionManager();
    }

    private void checkAndRequestPermissions() {

        permissionManager.simulatePermissionForCI();
    }
}
