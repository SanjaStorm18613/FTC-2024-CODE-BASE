package org.firstinspires.ftc.teamcode.Vision;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTagDetector {

    private static final boolean USE_WEBCAM = true;

    private AprilTagProcessor aprilTag;

    private VisionPortal visionPortal;

    private LinearOpMode opMode;

    public AprilTagDetector (LinearOpMode opMode) {

        // Create the AprilTag processor the easy way.
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        if (USE_WEBCAM) {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    opMode.hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
        } else {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, aprilTag);
        }

    }
    public void TeleApril(){

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        opMode.telemetry.addData("# AprilTags Detected", currentDetections.size());


        for (AprilTagDetection detection : currentDetections) {

            opMode.telemetry.addData("ID" ,detection.id);
            opMode.telemetry.addData("x" ,detection.ftcPose.x);
            opMode.telemetry.addData("y" ,detection.ftcPose.y);
            opMode.telemetry.addData("z" ,detection.ftcPose.z);

            opMode.telemetry.addLine();

            opMode.telemetry.addData("yaw" ,detection.ftcPose.yaw);
            opMode.telemetry.addData("pitch" ,detection.ftcPose.pitch);
            opMode.telemetry.addData("roll" ,detection.ftcPose.roll);

            opMode.telemetry.addLine();

            opMode.telemetry.addData("bearing" ,detection.ftcPose.bearing);
            opMode.telemetry.addData("elevation" ,detection.ftcPose.elevation);
            opMode.telemetry.addData("range" ,detection.ftcPose.range);

        }
    }
}