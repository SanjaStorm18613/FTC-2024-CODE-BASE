package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class FTCRobotAutonomous extends LinearOpMode {
//TODO
    //ADAPTDAR E EVOLUIR A IDEIA
    //TEM ERROS
    private Drivetrain drivetrain;
    private GamePieceDetector gamePieceDetector;
    private AprilTagDetector aprilTagDetector;
    private NavigationSystem navigationSystem;
    private ArmMechanism armMechanism;
    private ClawMechanism clawMechanism;

    @Override
    public void runOpMode() {
        initializeRobot();  // Inicializa o robô

        telemetry.addData("Status", "Aguardando início...");
        telemetry.update();
        waitForStart();  // Espera o start

        if (opModeIsActive()) {  // Checa se o opmode tá rodando
            if (gamePieceDetector.detectInitialSample()) {
                drivetrain.moveToSample();  // Vai pro SAMPLE
                aprilTagDetector.detectAprilTags();  // Ajusta com as tags
                armMechanism.raiseArm();  // Sobe o braço
                clawMechanism.releaseSample();  // Solta o SAMPLE
                drivetrain.moveToDropZone();  // Vai pra zona de depósito
            }

            if (gamePieceDetector.detectNewSample()) {
                drivetrain.moveToSample();  // Mesma coisa pra um novo SAMPLE
                aprilTagDetector.detectAprilTags();
                armMechanism.lowerArm();  // Desce o braço
                clawMechanism.grabSample();  // Pega o SAMPLE
                drivetrain.moveToDropZone();
            }
        }
    }

    // Inicializa o hardware
    private void initializeRobot() {
        drivetrain = new Drivetrain(hardwareMap);
        gamePieceDetector = new GamePieceDetector(hardwareMap);  // Passa o hardwareMap pro detector
        aprilTagDetector = new AprilTagDetector(hardwareMap);
        navigationSystem = new NavigationSystem(hardwareMap);
        armMechanism = new ArmMechanism(hardwareMap);
        clawMechanism = new ClawMechanism(hardwareMap);
    }
}
}