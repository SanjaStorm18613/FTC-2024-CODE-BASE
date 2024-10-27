package org.firstinspires.ftc.teamcode.Vision;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class CameraPermissionManager extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_CAMERA_PERM = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simulatePermissionForCI();
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void requestCameraPermission() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Permissão concedida! Acessando a câmera.", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "Precisamos acessar sua câmera para capturar imagens.",
                    RC_CAMERA_PERM, perms);
        }
    }

    // Simula a permissão no CI
    public void simulatePermissionForCI() {
        if (isRunningInCI()) {
            onPermissionsGranted(RC_CAMERA_PERM, new ArrayList<>());
        }else{
            requestCameraPermission();
        }
    }

    private boolean isRunningInCI() {
        return System.getenv("CI") != null ||
                System.getenv("GITHUB_ACTIONS") != null;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permissão concedida", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
