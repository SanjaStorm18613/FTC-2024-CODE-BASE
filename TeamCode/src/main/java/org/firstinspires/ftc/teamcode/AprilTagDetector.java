package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectionPipeline;

import java.util.ArrayList;




// PRECISAMOS DO OPENCV PARA ISSO; eduardinho explica
//TODO
//VER MANUAL REFERENTE A APRIL TAGS
//ESTUDAR MATERIAL DO GRUPO SOBRE CNN´S

public class AprilTagDetector {

    private AprilTagDetectionPipeline aprilTagPipeline; // pipeline pra detectar tags
    private OpenCvPipeline pipeline; // pipeline OpenCV
    private HardwareMap hardwareMap;

    // configura o tamanho da tag e os IDs que a gente vai usar no campo
    double tagsize = 0.1016; // 10.16 cm
    int[] tagIds = {11, 12, 13, 14, 15, 16}; // IDs das tags

    public AprilTagDetector(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        // inicializa a pipeline pra detectar tags
        aprilTagPipeline = new AprilTagDetectionPipeline(tagsize, tagIds);
        pipeline = new AprilTagDetectionPipeline();
    }

    // detecta as AprilTags e ajusta a posicao do robo
    public void detectAprilTags() {
        ArrayList<AprilTagDetection> detections = aprilTagPipeline.getLatestDetections();

        if (detections.size() != 0) {
            for (AprilTagDetection detection : detections) {
                int tagID = detection.id;  // pega o ID da tag detectada
                telemetry.addData("Tag detectada:", tagID);
                adjustPosition(tagID, detection.pose.x, detection.pose.y, detection.pose.z);  // ajusta a posicao
            }
        } else {
            telemetry.addData("Tag", "nenhuma tag detectada");
        }
        telemetry.update();
    }

    // ajusta a posicao do robo baseado no ID e nas coordenadas x, y, z
    private void adjustPosition(int tagID, double x, double y, double z) {
        switch (tagID) {
            case 11:
                telemetry.addData("Ajustando para", "deposito A");
                moveToPosition(x, y, z);  // ajusta com base nas coordenadas
                break;
            case 12:
                telemetry.addData("Ajustando para", "deposito B");
                moveToPosition(x, y, z);
                break;
            case 13:
                telemetry.addData("Ajustando para", "deposito C");
                moveToPosition(x, y, z);
                break;
            default:
                telemetry.addData("Ajuste", "tag desconhecida");
                break;
        }
        telemetry.update();
    }

    // move o robo com base nas coordenadas x, y, z
    private void moveToPosition(double x, double y, double z) {
        telemetry.addData("Posicao", "X: " + x + ", Y: " + y + ", Z: " + z);

        // usa essas coordenadas pra mover o robo na direcao certa
        //TODO:
        //ESTUDAR
        // link pra calc de distancia: https://en.wikipedia.org/wiki/Euclidean_distance
        //calcular Angulos de direção usando seno, cosseno, etc. (https://en.wikipedia.org/wiki/Trigonometry)
    }
}