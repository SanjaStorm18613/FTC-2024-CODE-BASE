package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class ClawMechanism {
    //mecanismo simples de garra

    //TODO
    // discutir entre areas qual caminho seguir e adaptar conforme necessario

    private Servo clawServo;
    private DigitalChannel limitSwitchClaw;

    public ClawMechanism(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "claw_servo");
        limitSwitchClaw = hardwareMap.get(DigitalChannel.class, "limit_switch_claw");
    }

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
