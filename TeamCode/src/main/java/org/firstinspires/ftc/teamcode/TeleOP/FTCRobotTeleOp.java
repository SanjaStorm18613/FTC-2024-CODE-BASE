package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystens.ArmMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.ClawMechanism;
import org.firstinspires.ftc.teamcode.Subsystens.Climbing;
import org.firstinspires.ftc.teamcode.Subsystens.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystens.Linear;
//import org.firstinspires.ftc.teamcode.Vision.CameraPermissionManager;

@TeleOp(name="FTCRobotTeleOp", group="FTC")
public class FTCRobotTeleOp extends LinearOpMode {

    //apenas para CI
    //TODO
    // ajudar a implentar o opencv nessa bagaça. ci ta delisgado
   // private CameraPermissionManager permissionManager;

    private Drivetrain drivetrain;
    private ArmMechanism armMechanism;
    private ClawMechanism clawMechanism;
    private Linear linear;
    private MultiSystems mult;
    private Climbing climbing;
    boolean btup = true;
    boolean btdown = true;


    @Override
    public void runOpMode() {
        // Inicializa o robô e gerencia as permissões
        initializeRobot();
        //checkAndRequestPermissions();

        telemetry.addData("Status", "Aguardando início...");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Controle do movimento do robô com traçao simples
            //TODO
            //AJUSTAR CONFORME ESPECIFICAÇAO


            //drive variaveis
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double side = gamepad1.left_stick_x;
            double stop = gamepad1.right_trigger;

            //drivetrain
            drivetrain.driveTele(drive, side, turn, stop);
            //drivetrain.autonomusdriveFB(1000);
            drivetrain.autonomusdriveLR(1000);

            //claw
            if (gamepad1.x && !clawMechanism.isClawClosed()) {
                clawMechanism.grabSample(gamepad1.x);
            } else if (gamepad1.x && clawMechanism.isClawClosed()) {
                clawMechanism.releaseSample();
            }

            //armstate
            armMechanism.StateUp(gamepad2.dpad_up);
            armMechanism.StateDown(gamepad2.dpad_down);
            //arm up/down
            armMechanism.setStatebt((int) (-gamepad2.left_stick_y));
            linear.setStatebt((int) (-gamepad2.right_stick_y));

            //arm linear
            mult.ArmLinear();
            linear.upstate(gamepad2.dpad_up);
            linear.downstate(gamepad2.dpad_down);

            //macro pegar peça
            if (gamepad2.y) {
                mult.TakePiece();
            }

            //climbing
            if (gamepad1.dpad_up) {
                climbing.raiseClimbing();
            } else if (gamepad1.dpad_down) {
                climbing.lowerClimbing();
            } else {
                climbing.stopClimbing();
            }


            /*telemetry.addData("State", armMechanism.getState());
            telemetry.addData("posi", armMechanism.getPosi());
            telemetry.update();*/

        }
    }

    private void initializeRobot() {
        climbing = new Climbing(this);
        drivetrain = new Drivetrain(this);
        armMechanism = new ArmMechanism(this);
        clawMechanism = new ClawMechanism(this);
        //permissionManager = new CameraPermissionManager();
        linear = new Linear(this);

        mult = new MultiSystems(this, armMechanism, linear, clawMechanism, climbing);
    }

    /*private void checkAndRequestPermissions() {

       permissionManager.simulatePermissionForCI();
    }*/
}