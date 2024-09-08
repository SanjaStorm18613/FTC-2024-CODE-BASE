package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="FTCRobotTeleOp", group="FTC")
public class FTCRobotTeleOp extends LinearOpMode {


    private CameraPermissionManager permissionManager;
    private Drivetrain drivetrain;
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
        }
    }

    private void initializeRobot() {
        drivetrain = new Drivetrain(hardwareMap);
        permissionManager = new CameraPermissionManager();
    }

    private void checkAndRequestPermissions() {

        permissionManager.simulatePermissionForCI();
    }
}
