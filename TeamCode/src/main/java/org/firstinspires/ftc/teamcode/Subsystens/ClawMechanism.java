package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class ClawMechanism {
    //mecanismo simples de garra

    //TODO
    // discutir entre areas qual caminho seguir e adaptar conforme necessario

    private Servo clawServo1;
    private Servo clawServo2;
    private DigitalChannel limitSwitchClaw;
    boolean btx = true;

    public ClawMechanism(LinearOpMode Opmode) {
        clawServo1 = Opmode.hardwareMap.get(Servo.class, "claw_servo1");
        clawServo2 = Opmode.hardwareMap.get(Servo.class, "claw_servo2");
        clawServo2.setDirection(Servo.Direction.REVERSE);
        releaseSample();
        limitSwitchClaw = Opmode.hardwareMap.get(DigitalChannel.class, "limit_switch_claw");

    }
    //Todo
    //Macro para agarrar gamepiece
    public void grabSample(boolean btxx) {
        if (!isClawClosed() && btxx && btx) {
            clawServo1.setPosition(0.4);
            clawServo2.setPosition(0.4);

        }
        btx = !btxx;

    }

    public void releaseSample() {
        clawServo1.setPosition(0.1);
        clawServo2.setPosition(0.1);
    }

    public boolean isClawClosed() {
        if (clawServo1.getPosition() == 1) {
        return true;
        }
        else {
            return false;
        }
    }
}
