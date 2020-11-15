package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class robot {
    DcMotor rightFront, rightBack, leftFront, leftBack, spin;
    BNO055IMU imu;
    Orientation angle;
    LinearOpMode linearOpMode;
    public void init (HardwareMap hardwareMap, LinearOpMode linearOpMode){
        spin = hardwareMap.get(DcMotor.class,"转");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        this.linearOpMode = linearOpMode;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.getAngularOrientation();
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
        angle = imu.getAngularOrientation();
    }
    public void parking() {
        while (leftFront.getCurrentPosition() < 3450 && linearOpMode.opModeIsActive()) {
            if (leftFront.getPower() >= 0 && leftFront.getCurrentPosition() > 1000) {
                leftFront.setPower(leftFront.getPower() - 0.005);
                leftBack.setPower(leftBack.getPower() - 0.005);
                rightFront.setPower(rightFront.getPower() - 0.005);
                rightBack.setPower(rightBack.getPower() - 0.005);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            } else if (leftFront.getPower() < 0.8) {
                leftFront.setPower(leftFront.getPower() + 0.001);
                leftBack.setPower(leftBack.getPower() + 0.001);
                rightFront.setPower(rightFront.getPower() + 0.001);
                rightBack.setPower(rightBack.getPower() + 0.001);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    }
