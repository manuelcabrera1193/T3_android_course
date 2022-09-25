package com.example.t3_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.t3_02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        listeners();
    }

    private void init() {
        String[] data = new String[]{"PLANILLA", "CONTRATADOS"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, data);
        binding.spinner.setAdapter(adapter);
        binding.result.setText(
                getResults(true)
        );
    }

    private void listeners() {
        binding.btnShowResults.setOnClickListener(listener -> {
            Log.i("aaa", binding.name.getText().toString());
            Log.i("aaa", binding.spinner.getSelectedItem().toString());
            Log.i("aaa", getBasic());
            Log.i("aaa", getExtras());
            Log.i("aaa", getAmountExtra());
            Log.i("aaa", getTotalExtras());
            Log.i("aaa", getGrossSalary());
            Log.i("aaa", getSecure());
            Log.i("aaa", getAdicionales());
            Log.i("aaa", getImpuestos());
            Log.i("aaa", getTotal()+"");
            binding.result.setText(
                    getResults(false)
            );
        });
    }

    private String getResults(boolean first) {
        return String.format("Nombre: %s\n" +
                        "Tipo de Trabajador: %s\n" +
                        "Sueldo Básico: %s\n" +
                        "Horas Extras:%s * %s = %s\n" +
                        "Sueldo Bruto:%s\n" +
                        "%s\n" +
                        "%s\n" +
                        "Impuesto:%s\n" +
                        "SUELDO NETO: %s",
                first ? "" : binding.name.getText().toString(),
                first ? "" : binding.spinner.getSelectedItem().toString(),
                first ? "" : getBasic(),
                first ? "" : getExtras(),
                first ? "" : getAmountExtra(),
                first ? "" : getTotalExtras(),
                first ? "" : getGrossSalary(),
                first ? "" : getSecure(),
                first ? "" : getAdicionales(),
                first ? "" : getImpuestos(),
                first ? "" : getTotal()
        );
    }

    private String getBasic() {
        return binding.basic.getText().toString();
    }

    private String getAmountExtra() {
        return binding.spinner.getSelectedItem().toString().equals("PLANILLA") ? "50" : "30";
    }

    private String getExtras() {
        return binding.extra.getText().toString();
    }

    private String getTotalExtras() {
        double priceExtra = Double.parseDouble(getAmountExtra());
        double extras = Double.parseDouble(getExtras());
        return String.format("%s", priceExtra * extras);
    }

    private String getGrossSalary() {
        return String.format("%s", Double.parseDouble(getTotalExtras()) + Double.parseDouble(getBasic()));
    }

    private String getImpuestos() {
        return String.format("%s", Double.parseDouble(getGrossSalary()) * 0.08);
    }

    private String getSecure() {
        if (binding.radioAfp.isChecked()) {
            return "AFP(14%): " + (Double.parseDouble(getGrossSalary()) * 0.14);
        } else if (binding.radioOnp.isChecked()) {
            return "ONP(14%): " + (Double.parseDouble(getGrossSalary()) * 0.12);
        } else {
            return "";
        }
    }

    private double getSecureDouble() {
        if (binding.radioAfp.isChecked()) {
            return Double.parseDouble(getGrossSalary()) * 0.14;
        } else if (binding.radioOnp.isChecked()) {
            return Double.parseDouble(getGrossSalary()) * 0.12;
        } else {
            return 0.0;
        }
    }

    private String getAdicionales() {
        double adicionalesDouble = 0.0;
        String adicionales = "";
        if (binding.chk1.isChecked()) {
            adicionalesDouble += 30;
            adicionales += "EPS";
        }
        if (binding.chk2.isChecked()) {
            adicionalesDouble += 50;
            if (adicionales.isEmpty()) {
                adicionales += "Seguro Oncológico";
            } else {
                adicionales += " + Seguro Oncológico";
            }

        }
        if (binding.chk3.isChecked()) {
            adicionalesDouble += 80;
            if (adicionales.isEmpty()) {
                adicionales += "Afiliación Club";
            } else {
                adicionales += " + Afiliación Club";
            }
        }

        return adicionales + ":" + adicionalesDouble;
    }

    private double getAdicionalesDouble() {
        double adicionalesDouble = 0.0;
        if (binding.chk1.isChecked()) {
            adicionalesDouble += 30;
        }
        if (binding.chk2.isChecked()) {
            adicionalesDouble += 50;
        }
        if (binding.chk3.isChecked()) {
            adicionalesDouble += 80;
        }

        return adicionalesDouble;
    }


    private double getTotal() {
        return Double.parseDouble(getGrossSalary()) - getAdicionalesDouble() - getSecureDouble();
    }

}