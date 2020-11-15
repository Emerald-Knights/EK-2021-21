package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
@TeleOp
public class spin extends LinearOpMode{
    robot boWei = new robot();
    @Override
    public void runOpMode(){
        boWei.init(hardwareMap, this);

        waitForStart();
        while(boWei.linearOpMode.opModeIsActive()){
            boolean x1= gamepad1.x;
            boolean y1= gamepad1.y;

            double lx=gamepad1.left_stick_x;
            double ly=-gamepad1.left_stick_y;
            double rx=gamepad1.right_stick_x;

            if (Math.abs(lx) <= 0.15) {
                lx = 0;
            }

            if (Math.abs(ly) <= 0.15) {
                ly = 0;
            }

            if (Math.abs(rx) <= 0.15) {
                rx = 0;
            }

            double lf = lx + ly + rx;
            double lb = ly - lx + rx;
            double rf = ly - lx - rx;
            double rb = lx + ly - rx;
            double max = Math.max(Math.max(Math.abs(lb), Math.abs(lf)), Math.max(Math.abs(rb), Math.abs(rf)));
            double magnitude = Math.sqrt((lx * lx) + (ly * ly) + (rx * rx));
            double ratio = magnitude / max;
            if (max == 0) {
                ratio=0;
            }
            boWei.leftFront.setPower(lf * ratio);
            boWei.leftBack.setPower(lb * ratio);
            boWei.rightFront.setPower(rf * ratio);
            boWei.rightBack.setPower(rb * ratio);


            if(gamepad1.x){
                boWei.spin.setPower(-0.8);
            }
            else{
                boWei.spin.setPower(0);
            }
        }

    }
}
