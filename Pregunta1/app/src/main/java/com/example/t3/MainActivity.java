package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.t3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initEvents();
    }

    private void initEvents() {
        binding.btnCalculate.setOnClickListener(listener -> {
            try {
                String valueInput = binding.input.getText().toString();
                if (valueInput.isEmpty()) {
                    Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_LONG).show();
                } else {
                    double value = Double.parseDouble(valueInput);
                    binding.result1.setText(String.format("%s", value * 0.2));
                    binding.result2.setText(String.format("%s", value * 0.3));
                    binding.result3.setText(String.format("%s", value * 0.5));
                }
            } catch (Exception e) {
                Toast.makeText(this, "Ocurrio un error al calcular.", Toast.LENGTH_LONG).show();
            }
        });
    }
}