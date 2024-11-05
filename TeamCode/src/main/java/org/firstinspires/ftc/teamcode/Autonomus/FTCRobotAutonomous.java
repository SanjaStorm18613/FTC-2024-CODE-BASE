package org.firstinspires.ftc.teamcode.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystens.ArmMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.ClawMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.Drivetrain;
import org.firstinspires.ftc.teamcode.Vision.AprilTagDetector;
import org.firstinspires.ftc.teamcode.Vision.GamePieceDetector;

public class FTCRobotAutonomous extends LinearOpMode {
//TODO
    //ADAPTDAR E EVOLUIR A IDEIA
    //TEM ERROS
    private Drivetrain drivetrain;
    private GamePieceDetector gamePieceDetector;
    private AprilTagDetector aprilTagDetector;
    //private NavigationSystem navigationSystem;
    private ArmMechanism armMechanism;
    private ClawMechanism clawMechanism;

    @Override
    public void runOpMode() {
        initializeRobot();  // Inicializa o robô

        telemetry.addData("Status", "Aguardando início...");
        telemetry.update();
        waitForStart();  // Espera o start

        if (opModeIsActive()) {  
            if (gamePieceDetector.detectInitialSample()) {
                //drivetrain.moveToSample();  // Vai pro SAMPLE
                //aprilTagDetector.detectAprilTags();  // Ajusta com as tags
                armMechanism.raiseArm();  // Sobe o braço
                clawMechanism.releaseSample();  // Solta o SAMPLE
                drivetrain.moveToDropZone();  // Vai pra zona de depósito
            }

            if (gamePieceDetector.detectNewSample()) {
                //drivetrain.moveToSample();  // Mesma coisa pra um novo SAMPLE
                //aprilTagDetector.detectAprilTags();
                armMechanism.lowerArm();  // Desce o braço
                clawMechanism.grabSample();  // Pega o SAMPLE
                drivetrain.moveToDropZone();
            }
        }
    }

    // Inicializa o hardware
    private void initializeRobot() {
        drivetrain = new Drivetrain(this);
        gamePieceDetector = new GamePieceDetector(this);  // Passa o hardwareMap pro detector
        aprilTagDetector = new AprilTagDetector(this);
        //navigationSystem = new NavigationSystem(hardwareMap);
        armMechanism = new ArmMechanism(this);
        clawMechanism = new ClawMechanism(this);
    }
}
