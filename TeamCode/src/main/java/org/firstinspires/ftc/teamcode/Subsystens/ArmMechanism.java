package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class ArmMechanism {
    //exemplo de braço. acho que deveriamos focar no level 1 e 0
    private DcMotor armMotor;
    private DigitalChannel limitSwitch;
    int state = 0;
    boolean btup = true;
    boolean btdown = true;

    public ArmMechanism(LinearOpMode Opmode) {
        armMotor = Opmode.hardwareMap.get(DcMotor.class, "arm_motor");
        limitSwitch = Opmode.hardwareMap.get(DigitalChannel.class, "limit_switch_arm");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void StateUp(boolean buttonStateUp) {
        if (state < 3 && buttonStateUp && btup){
            state = state + 1;
        }
        btup = !buttonStateUp;
    }

    public void StateDown(boolean buttonStateDown) {
        if (state > 0 && buttonStateDown && btdown){
            state = state - 1;
        }
        btdown = !buttonStateDown;
    }

    public int getState(){
        return state;
    }

    public void setStateArm(int armstate) {
        state = armstate;
    }


    public void setStatebt(int g2leftstickY) {
        switch (state){
            case 0:
                armMotor.setTargetPosition(0 + (int) (g2leftstickY));
                break;
            case 1:
                armMotor.setTargetPosition(10 + (int) (g2leftstickY));
                break;
            case 2:
                armMotor.setTargetPosition(20 + (int) (g2leftstickY));
                break;
            case 3:
                armMotor.setTargetPosition(30 + (int) (g2leftstickY));
                break;

        }
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(1);
    }

    public void stopArm() {
        armMotor.setPower(0);
    }
}
