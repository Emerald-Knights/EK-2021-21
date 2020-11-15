package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="frosh", group="f")
public class freshmenNoobs extends LinearOpMode {
    DcMotor leftFront, leftBack, rightFront, rightBack;

    @Override
    public void runOpMode() {
        leftFront=hardwareMap.get(DcMotor.class, "leftFront");


        BNO055IMU imu;
        imu= hardwareMap.get(BNO055IMU.class, "imu");


        waitForStart();
        
        leftFront.getCurrentPosition();


        while(leftFront.getCurrentPosition() < 800 && opModeIsActive()){
            //need opModeIsActive() for all while loops or if it stops prematurely there will be errors
            leftFront.setPower(0.75);
        }
        leftFront.setPower(0);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
