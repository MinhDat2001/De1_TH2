package com.example.sqlite_th2_de1.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.adapter.StatusAdapter;
import com.example.sqlite_th2_de1.database.WorkDAO;
import com.example.sqlite_th2_de1.model.Work;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WorkAddActivity extends AppCompatActivity {

    private Spinner mStatusSpinner;
    private EditText mEditTextWorkName;
    private EditText mEditTextWorkDescription;
    private EditText mEditTextDate;
    private RadioGroup collaborationRadioGroup;
    private Button buttonDate;
    private Button buttonSave;
    private Button buttonBack;
    private WorkDAO mWorkDAO;
    private Boolean colab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_add);

        mStatusSpinner = findViewById(R.id.spn_work);
        mEditTextWorkName = findViewById(R.id.editTextWorkName);
        mEditTextWorkDescription = findViewById(R.id.editTextWorkDescription);
        mEditTextDate = findViewById(R.id.editTextDate);
        collaborationRadioGroup = findViewById(R.id.collaboration_radio_group);
        collaborationRadioGroup.check(R.id.alone_radio_button);

        buttonDate = findViewById(R.id.buttonDate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);

        mWorkDAO = new WorkDAO(this);

        List<String> status = Arrays.asList(getResources().getStringArray(R.array.status_array));
        StatusAdapter adapter = new StatusAdapter(this, android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(adapter);

        colab = false;

        collaborationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.alone_radio_button) {
                    colab = false;
                } else if (checkedId == R.id.together_radio_button) {
                    colab = true;
                }
            }
        });

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog datePickerDialog = new DatePickerDialog(WorkAddActivity.this, (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                    mEditTextDate.setText(selectedDate);
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditTextWorkName.getText().toString().trim();
                String des = mEditTextWorkDescription.getText().toString().trim();
                String date = mEditTextDate.getText().toString().trim();
                String status = (String) mStatusSpinner.getSelectedItem();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(des) || TextUtils.isEmpty(date) || TextUtils.isEmpty(status))
                    Toast.makeText(WorkAddActivity.this, "Hãy điền đầy đủ các mục", Toast.LENGTH_SHORT).show();
                else {
                    Work work = new Work(name, des, date, status, colab);
                    Log.d("Tuan", "onClick: " + name + "\n" + des + "\n" + date + "\n" + colab);
                    mWorkDAO.addWork(work);
                    Toast.makeText(WorkAddActivity.this, "Thêm công việc thành công " + name, Toast.LENGTH_SHORT).show();
                    mEditTextWorkName.setText(null);
                    mEditTextWorkDescription.setText(null);
                    mEditTextDate.setText(null);
                    collaborationRadioGroup.check(R.id.alone_radio_button);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
