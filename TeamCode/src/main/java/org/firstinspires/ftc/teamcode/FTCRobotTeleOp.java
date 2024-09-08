package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="FTCRobotTeleOp", group="FTC")
public class FTCRobotTeleOp extends LinearOpMode {


    private CameraPermissionManager permissionManager;

    @Override
    public void runOpMode() {
        // Inicializa o robô e gerencia as permissões
        initializeRobot();
        checkAndRequestPermissions();

        telemetry.addData("Status", "Aguardando início...");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Controle do movimento do robô

        }
    }

    private void initializeRobot() {

        permissionManager = new CameraPermissionManager();
    }

    private void checkAndRequestPermissions() {

        permissionManager.simulatePermissionForCI();
    }
}
