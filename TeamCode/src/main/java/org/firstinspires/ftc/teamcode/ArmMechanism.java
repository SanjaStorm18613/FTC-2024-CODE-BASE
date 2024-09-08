package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class ArmMechanism {
    //exemplo de braço. acho que deveriamos focar no level 1 e 0
    //TODO
    // discutir com mecanica e ajustar conforme necessario
    private DcMotor armMotor;
    private DigitalChannel limitSwitch;

    public ArmMechanism(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotor.class, "arm_motor");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "limit_switch_arm");
    }

    //TODO
    // Macro erguer braço
    public void raiseArm() {
        if (!limitSwitch.getState()) {
            armMotor.setPower(0.5);
        } else {
            stopArm();
        }
    }
    //TODO
    //Macro abaixar braço
    public void lowerArm() {
        armMotor.setPower(-0.5);
    }

    public void stopArm() {
        armMotor.setPower(0);
    }
}
