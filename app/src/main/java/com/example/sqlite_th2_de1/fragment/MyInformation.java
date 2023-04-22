package com.example.sqlite_th2_de1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sqlite_th2_de1.R;

public class MyInformation extends Fragment {

    private ImageView avatar;
    private TextView hoVaTen;
    private TextView soThich;
    private TextView gioiThieu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_information, container, false);

        avatar = view.findViewById(R.id.avatar);
        hoVaTen = view.findViewById(R.id.ho_va_ten);
        soThich = view.findViewById(R.id.so_thich);
        gioiThieu = view.findViewById(R.id.gioi_thieu_ban_than);

        hoVaTen.setText("Đặng Minh Đạt");
        gioiThieu.setText("Mình đến từ Hà Nội, mình hiện đang là sinh viên trường Bưu chính viễn thông. " +
                "Đây là bài thực hành của mình, " +
                "rất vui được biết và làm quen với tất cả các bạn.\n" +
                "\n");
        return view;
    }
}
