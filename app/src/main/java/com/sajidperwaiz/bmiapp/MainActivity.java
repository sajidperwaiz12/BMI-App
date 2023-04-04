package com.sajidperwaiz.bmiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout mainLayout, innerLayout;
    EditText edtWeight, edtFt, edtIn, edtStatus;
    Button btnBmiTable, btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        innerLayout = findViewById(R.id.innerLayout);
        edtWeight = findViewById(R.id.edtWeight);
        edtFt = findViewById(R.id.edtHeightFt);
        edtIn = findViewById(R.id.edtHeightIn);
        edtStatus = findViewById(R.id.edtStatus);
        btnBmiTable = findViewById(R.id.btnTable);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtWeight.getText().toString().isBlank()) {
                    edtStatus.setText(getResources().getText(R.string.empty_weight));
                } else if (edtFt.getText().toString().isBlank()) {
                    edtStatus.setText(getResources().getText(R.string.empty_height));
                }


                double weight = Double.parseDouble(edtWeight.getText().toString());
                int heightFt = Integer.parseInt(edtFt.getText().toString());
                int heightIn = Integer.parseInt(edtIn.getText().toString());
                int totalIn = heightFt * 12 + heightIn;
                double totalHt = totalIn * 0.0254;
                double bmi = weight / (totalHt * totalHt);

                if (bmi < 18.5) {
                    edtStatus.setText(getResources().getText(R.string.underweight));
                    edtStatus.setBackgroundColor(getResources().getColor(R.color.underweight));
                } else if (bmi < 25) {
                    edtStatus.setText(getResources().getText(R.string.healthy));
                    edtStatus.setBackgroundColor(getResources().getColor(R.color.healthy));
                } else if (bmi < 30) {
                    edtStatus.setText(getResources().getText(R.string.overweight));
                    edtStatus.setBackgroundColor(getResources().getColor(R.color.overweight));
                } else {
                    edtStatus.setText(getResources().getText(R.string.obesity));
                    edtStatus.setBackgroundColor(getResources().getColor(R.color.obesity));
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtStatus.setText("");
                edtFt.setText("");
                edtIn.setText("");
                edtWeight.setText("");
                edtStatus.setBackgroundColor(getResources().getColor(R.color.reset_color));
            }
        });

        btnBmiTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BmitTableActivity.class);
                startActivity(i);
            }
        });
    }
}