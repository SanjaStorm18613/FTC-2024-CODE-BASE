package org.firstinspires.ftc.teamcode.Subsystens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Linear {

    public DcMotor LinearMotor;
    int state = 0;
    boolean btup = true;
    boolean btdown = true;



    public Linear(LinearOpMode Opmode) {
        LinearMotor = Opmode.hardwareMap.get(DcMotor.class, "Linear_Amr");
        LinearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void upstate (boolean ButtonStateUp){
        if( state < 3 && ButtonStateUp && btup) {
            state = state + 1;
        }
            btup = !ButtonStateUp;
    }

    public void downstate (boolean ButtonStateDown) {
        if (state > 0 && ButtonStateDown && btdown) {
            state = state - 1;
        }
            btdown = !ButtonStateDown;
    }

    public void setStateLinearArm(int armstate) {
        state = armstate;
    }

    public void setStatebt(boolean ButtonStateDown, int g2rightstickY){
        switch (state){
            case 0:
                LinearMotor.setTargetPosition(0 + g2rightstickY);
                break;
            case 1:
                LinearMotor.setTargetPosition(10 + g2rightstickY);
                break;
            case 2:
                LinearMotor.setTargetPosition(20 + g2rightstickY);
                break;
            case 3:
                LinearMotor.setTargetPosition(30 + g2rightstickY);
                break;
        }
        LinearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LinearMotor.setPower(1);
    }

    public void linearup (){

        LinearMotor.setTargetPosition( LinearMotor.getTargetPosition() + 1);
    }

    public void lineardown (){
        LinearMotor.setTargetPosition( LinearMotor.getTargetPosition() - 1);
    }





}
