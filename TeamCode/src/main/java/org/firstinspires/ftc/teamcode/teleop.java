package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Teleop", group="oof")
public class teleop extends OpMode {

    //robotP robert= new robotP();
    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotor spinnyThing;
    @Override
    public void init(){
        leftFront= hardwareMap.get(DcMotor.class, "leftFront");
        leftBack= hardwareMap.get(DcMotor.class, "leftBack");
        rightFront= hardwareMap.get(DcMotor.class, "rightFront");
        rightBack= hardwareMap.get(DcMotor.class, "rightBack");

        spinnyThing = hardwareMap.get(DcMotor.class, "转");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    @Override
    public void start(){

    }
    @Override
    public void loop() {

        boolean x1 = gamepad1.x;
        boolean y1 = gamepad1.y;

        double lx = gamepad1.left_stick_x;
        double ly = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        double lf = ly + lx + rx;
        double lb = ly - lx + rx;
        double rf = ly - lx - rx;
        double rb = ly + lx - rx;

        if (x1 == true) {
            spinnyThing.setPower(0.8);
        } else if (y1 == true) {
            spinnyThing.setPower(-0.8);
        } else {
            spinnyThing.setPower(0);
        }


        if (Math.abs(lx) <= 0.15) {
            lx = 0;
        }

        if (Math.abs(ly) <= 0.15) {
            ly = 0;
        }

        if (Math.abs(rx) <= 0.15) {
            rx = 0;
        }

        double max = Math.max(Math.max(Math.abs(lb), Math.abs(lf)), Math.max(Math.abs(rb), Math.abs(rf)));
        double magnitude = Math.sqrt((lx * lx) + (ly * ly) + (rx * rx));
        double ratio = magnitude * .8 / max;

        if (max == 0) {
            ratio = 0;
        }

        leftFront.setPower(lf * ratio);
        leftBack.setPower(lb * ratio);
        rightFront.setPower(rf * ratio);
        rightBack.setPower(rb * ratio);

        telemetry.addData("left joystick", lx);
        telemetry.update();
    }
}