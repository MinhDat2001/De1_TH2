package com.example.sqlite_th2_de1.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.adapter.StatusAdapter;
import com.example.sqlite_th2_de1.model.Work;
import com.example.sqlite_th2_de1.database.WorkDAO;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WorkDetailActivity extends AppCompatActivity {
    int id;
    String name, des, date, status;
    Boolean colab;
    private TextView mId;
    private Spinner mStatusSpinner;
    private EditText mEditTextWorkName;
    private EditText mEditTextWorkDescription;
    private EditText mEditTextDate;
    private RadioGroup collaborationRadioGroup;
    private Button buttonDate;
    private Button buttonChange;
    private Button buttonDelete;
    private Button buttonBack;
    private WorkDAO mWorkDAO;

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);
        Intent intent = getIntent();
        Work work = (Work) intent.getSerializableExtra("work");
        id = work.getId();
        name = work.getName();
        des = work.getDescription();
        date = work.getDate();
        status = work.getStatus();
        colab = work.getCollaborate();


        mStatusSpinner = findViewById(R.id.spn_work);
        mId = findViewById(R.id.tvID);
        mEditTextWorkName = findViewById(R.id.editTextWorkName);
        mEditTextWorkDescription = findViewById(R.id.editTextWorkDescription);
        mEditTextDate = findViewById(R.id.editTextDate);
        collaborationRadioGroup = findViewById(R.id.collaboration_radio_group);

        buttonDate = findViewById(R.id.buttonDate);
        buttonChange = findViewById(R.id.buttonChange);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.buttonBack);
        mWorkDAO = new WorkDAO(this);

        mId.setText(Integer.toString(id));
        mEditTextWorkName.setText(name);
        mEditTextWorkDescription.setText(des);
        mEditTextDate.setText(date);

        List<String> publishers = Arrays.asList(getResources().getStringArray(R.array.status_array));
        StatusAdapter adapter = new StatusAdapter(this, android.R.layout.simple_spinner_item, publishers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(adapter);
        selectSpinnerItemByValue(mStatusSpinner, status);

        collaborationRadioGroup.check(colab ? R.id.together_radio_button : R.id.alone_radio_button);
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
                final DatePickerDialog datePickerDialog = new DatePickerDialog(WorkDetailActivity.this, (view, year1, monthOfYear, dayOfMonth) -> {
                    // Đặt ngày được chọn vào EditText
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                    mEditTextDate.setText(selectedDate);
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextWorkName.getText().toString();
                des = mEditTextWorkDescription.getText().toString();
                date = mEditTextDate.getText().toString();
                status = mStatusSpinner.getSelectedItem().toString();
                Work work = new Work(id, name, des, date, status, colab);
                mWorkDAO.updateWork(work);
                Toast.makeText(WorkDetailActivity.this, "Công việc số " + id + " đã được cập nhật", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkDAO.deleteBook(work);
                Toast.makeText(WorkDetailActivity.this, "Công việc số " + work.getId()+ " đã bị xoá", Toast.LENGTH_SHORT).show();
                onBackPressed();
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