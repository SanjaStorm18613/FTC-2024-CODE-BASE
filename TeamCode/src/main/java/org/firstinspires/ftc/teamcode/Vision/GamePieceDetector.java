package org.firstinspires.ftc.teamcode.Vision;

import android.content.res.AssetFileDescriptor;

import org.tensorflow.lite.Interpreter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//TODO
// ESTUDAR CNNS
// DESCOBRIR COMO QUE FAZ ISSO HOJE EM DIA
//ACESSAR O TENSOR FLOW. GERAR UM MODELO DE TREINO

public class GamePieceDetector {

    private Interpreter interpreter;
    private HardwareMap hardwareMap;  // Definindo o hardwareMap aqui

    public GamePieceDetector(LinearOpMode Opmode) {
        Opmode.hardwareMap = hardwareMap;  // Passa o hardwareMap pro construtor
        try {
            interpreter = new Interpreter(loadModelFile());  // carrega o modelo TFLite
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método pra carregar o arquivo do modelo .tflite
    private MappedByteBuffer loadModelFile() throws Exception {
        AssetFileDescriptor fileDescriptor = hardwareMap.appContext.getAssets().openFd("gamepiece_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // Detecta se a peça de jogo inicial tá presente
    public boolean detectInitialSample() {
        float[][] input = processCameraFrame();  // pega o frame da câmera (simulado aqui)
        float[][] output = new float[1][1];  // saída do modelo
        interpreter.run(input, output);
        return output[0][0] > 0.5;  // retorna se detectou (mais de 50%)
    }

    // Detecta se uma nova peça de jogo foi vista
    public boolean detectNewSample() {
        float[][] input = processCameraFrame();  // mesmo processo do inicial
        float[][] output = new float[1][1];
        interpreter.run(input, output);
        return output[0][0] > 0.5;
    }

    // Simulação do frame da câmera
    private float[][] processCameraFrame() {
        return new float[1][1];  // aqui você faria o processamento real da imagem
    }
}