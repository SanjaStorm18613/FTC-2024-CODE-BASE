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
    double vel = 1;
    double posi;

    public ArmMechanism(LinearOpMode Opmode) {
        armMotor = Opmode.hardwareMap.get(DcMotor.class, "arm_motor");
        limitSwitch = Opmode.hardwareMap.get(DigitalChannel.class, "limit_switch_arm");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void StateUp(boolean buttonStateUp) {
        if (state < 3 && buttonStateUp && btup){
            state = state + 1;
            posi = 0;
        }
        btup = !buttonStateUp;
    }

    public void StateDown(boolean buttonStateDown) {
        if (state > 0 && buttonStateDown && btdown){
            state = state - 1;
            posi = 0;
        }
        btdown = !buttonStateDown;
    }

    public int getPosi(){
        return (int) posi;
    }

    public int getState(){
        return state;
    }

    public void setStateArm(int armstate) {
        state = armstate;
    }


    public void setStatebt(double g2leftstickY) {
        posi += (vel * g2leftstickY);


        switch (state){

            case 0:
                armMotor.setTargetPosition(0 + (int) (posi));
                break;
            case 1:
                armMotor.setTargetPosition(50 + (int) (posi));
                break;
            case 2:
                armMotor.setTargetPosition(100 + (int) (posi));
                break;
            case 3:
                armMotor.setTargetPosition(150 + (int) (posi));
                break;

        }
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(1);
    }

    public void stopArm() {
        armMotor.setPower(0);
    }
}
