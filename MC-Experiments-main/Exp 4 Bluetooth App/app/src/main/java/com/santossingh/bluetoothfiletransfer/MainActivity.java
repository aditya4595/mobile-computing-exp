package com.santossingh.bluetoothfiletransfer;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button openFileBtn, sendBtn;
    private EditText dataPath;

    private Uri selectedFileUri = null;
    private BluetoothAdapter btAdapter;

    private static final int PERMISSION_CODE = 101;

    // -------- FILE PICKER ----------
    private final ActivityResultLauncher<String[]> filePicker =
            registerForActivityResult(new ActivityResultContracts.OpenDocument(), uri -> {
                if (uri != null) {
                    selectedFileUri = uri;
                    dataPath.setText("File selected ✓");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFileBtn = findViewById(R.id.opendailog);
        sendBtn = findViewById(R.id.sendBtooth);
        dataPath = findViewById(R.id.FilePath);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        requestBluetoothPermissions();

        openFileBtn.setOnClickListener(v -> openFilePicker());

        sendBtn.setOnClickListener(v -> sendViaBluetooth());
    }

    // -------- OPEN FILE PICKER ----------
    private void openFilePicker() {
        filePicker.launch(new String[]{"*/*"});
    }

    // -------- PERMISSIONS ----------
    private void requestBluetoothPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                            != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.BLUETOOTH_CONNECT,
                        Manifest.permission.BLUETOOTH_SCAN
                }, PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Bluetooth permission required", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    // -------- SEND FILE ----------
    private void sendViaBluetooth() {

        if (selectedFileUri == null) {
            Toast.makeText(this, "Select a file first", Toast.LENGTH_SHORT).show();
            return;
        }

        if (btAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_LONG).show();
            return;
        }

        if (!btAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, selectedFileUri);
        intent.setPackage("com.android.bluetooth");

        try {
            startActivity(Intent.createChooser(intent, "Send File via Bluetooth"));
        } catch (Exception e) {
            Toast.makeText(this, "Bluetooth app not found", Toast.LENGTH_LONG).show();
        }
    }
}
