package com.example.sqlite_th2_de1.Utils;

import com.example.sqlite_th2_de1.model.Work;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class WorkDateComparator implements Comparator<Work> {

    private final DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

    @Override
    public int compare(Work o1, Work o2) {
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = dateFormat.parse(o1.getDate());
            date2 = dateFormat.parse(o2.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1.compareTo(date2);
    }
}
