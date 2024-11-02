package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class ClawMechanism {
    //mecanismo simples de garra

    //TODO
    // discutir entre areas qual caminho seguir e adaptar conforme necessario

    private Servo clawServo;
    private DigitalChannel limitSwitchClaw;

    public ClawMechanism(LinearOpMode Opmode) {
        clawServo = Opmode.hardwareMap.get(Servo.class, "claw_servo");
        limitSwitchClaw = Opmode.hardwareMap.get(DigitalChannel.class, "limit_switch_claw");

    }
    //Todo
    //Macro para agarrar gamepiece
    public void grabSample() {
        if (!isClawClosed()) {
            clawServo.setPosition(1);

        }

    }

    public void releaseSample() {
        clawServo.setPosition(0);
    }

    public boolean isClawClosed() {
        return !limitSwitchClaw.getState();
    }
}
