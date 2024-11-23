package org.firstinspires.ftc.teamcode.Subsystens;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Linear {

    public DcMotor LinearMotor;
    int state = 0;
    boolean btup = true;
    boolean btdown = true;
    double vel = 10;
    double posi;

    LinearOpMode Opmode;



    public Linear(LinearOpMode Opmode) {
        this.Opmode = Opmode;
        LinearMotor = Opmode.hardwareMap.get(DcMotor.class, "Linear_Amr");
        LinearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void upstate (boolean ButtonStateUp){
        if( state < 3 && ButtonStateUp && btup) {
            state = state + 1;
            posi = 0;
        }
            btup = !ButtonStateUp;
    }

    public void downstate (boolean ButtonStateDown) {
        if (state > 0 && ButtonStateDown && btdown) {
            state = state - 1;
            posi = 0;
        }
            btdown = !ButtonStateDown;
    }

    public void setStateLinearArm(int armstate) {
        state = armstate;
        posi = 0;
    }

    public int getPosi(){
        return (int) posi;
    }

    public int getState(){
        return state;
    }

    public void setStatebt(double g2rightstickY){

        posi += (vel * g2rightstickY);

        switch (state){
            case 0:
                LinearMotor.setTargetPosition(0 + (int) -posi);
                break;
            case 1:
                LinearMotor.setTargetPosition(-300 + (int) -posi);
                break;
            case 2:
                LinearMotor.setTargetPosition(-450 + (int) -posi);
                break;
            case 3:
                LinearMotor.setTargetPosition(-630 + (int) -posi);
                break;

        }
        LinearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LinearMotor.setPower(1);
        Opmode.telemetry.addData("linear_state", state);
        Opmode.telemetry.addData("linear_target", LinearMotor.getTargetPosition());

    }

    public void linearup (){

        LinearMotor.setTargetPosition( LinearMotor.getTargetPosition() + 1);
    }

    public void lineardown (){
        LinearMotor.setTargetPosition( LinearMotor.getTargetPosition() - 1);
    }





}
