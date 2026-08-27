package com.albasit.plus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isVip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVipToggle = findViewById(R.id.btnVipToggle);
        Button btnLearner = findViewById(R.id.btnLearner);
        Button btnCv = findViewById(R.id.btnCv);
        Button btnBills = findViewById(R.id.btnBills);
        Button btnRepair = findViewById(R.id.btnRepair);
        Button btnEasyPaisa = findViewById(R.id.btnEasyPaisa);
        Button btnEasyLoad = findViewById(R.id.btnEasyLoad);

        btnVipToggle.setOnClickListener(v -> {
            isVip = !isVip;
            if (isVip) {
                btnVipToggle.setText("VIP Status: ACTIVE (Credit Limit: 5000)");
                Toast.makeText(this, "VIP Mode Activated!", Toast.LENGTH_SHORT).show();
            } else {
                btnVipToggle.setText("VIP Status: INACTIVE (Tap to Simulate VIP)");
                Toast.makeText(this, "Standard User Mode Activated", Toast.LENGTH_SHORT).show();
            }
        });

        // Standard Services
        btnLearner.setOnClickListener(v -> showRequestDialog("Driving Learner License"));
        btnCv.setOnClickListener(v -> showRequestDialog("CV / Resume Making"));
        btnBills.setOnClickListener(v -> showRequestDialog("Utility Bill / Fee Payment"));
        btnRepair.setOnClickListener(v -> showRequestDialog("Mobile Repair / Software Flashing"));

        // VIP Only Services
        btnEasyPaisa.setOnClickListener(v -> {
            if (!isVip) {
                showVipRestrictionDialog();
            } else {
                showRequestDialog("Emergency EasyPaisa Transfer (VIP Credit)");
            }
        });

        btnEasyLoad.setOnClickListener(v -> {
            if (!isVip) {
                showVipRestrictionDialog();
            } else {
                showRequestDialog("Emergency EasyLoad Balance (VIP Credit)");
            }
        });
    }

    private void showRequestDialog(String serviceName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Request: " + serviceName);
        builder.setMessage("Your request will be sent directly to Albasit Mobile Shop Admin Panel. Shop team will contact you shortly.");
        builder.setPositiveButton("Submit Request", (dialog, which) -> 
            Toast.makeText(MainActivity.this, "Request Sent to Shop Admin!", Toast.LENGTH_LONG).show()
        );
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showVipRestrictionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("VIP Access Only");
        builder.setMessage("Emergency Cash and EasyLoad credit services are strictly available for registered VIP members with security deposit.\n\nPlease contact Albasit Mobile Shop to upgrade.");
        builder.setPositiveButton("OK, Got It", null);
        builder.show();
    }
}
