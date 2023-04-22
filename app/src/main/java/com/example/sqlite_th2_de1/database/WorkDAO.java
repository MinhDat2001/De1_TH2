package com.example.sqlite_th2_de1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite_th2_de1.model.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkDAO {
    private final WorkDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public WorkDAO(Context context) {
        dbHelper = new WorkDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public int getMaxId() {
        open();
        int maxId = 0;
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM " + WorkDatabaseHelper.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        cursor.close();
        close();
        return maxId;
    }


    public void addWork(Work work) {
        open();
        ContentValues values = new ContentValues();
        values.put(WorkDatabaseHelper.COLUMN_ID, work.getId());
        values.put(WorkDatabaseHelper.COLUMN_NAME, work.getName());
        values.put(WorkDatabaseHelper.COLUMN_DES, work.getDescription());
        values.put(WorkDatabaseHelper.COLUMN_DATE, work.getDate());
        values.put(WorkDatabaseHelper.COLUMN_STATUS, work.getStatus());
        values.put(WorkDatabaseHelper.COLUMN_COLAB, work.getCollaborate());

        db.insert(WorkDatabaseHelper.TABLE_NAME, null, values);
        close();
    }

    public void updateWork(Work work) {
        open();
        ContentValues values = new ContentValues();
        values.put(WorkDatabaseHelper.COLUMN_NAME, work.getName());
        values.put(WorkDatabaseHelper.COLUMN_DES, work.getDescription());
        values.put(WorkDatabaseHelper.COLUMN_DATE, work.getDate());
        values.put(WorkDatabaseHelper.COLUMN_STATUS, work.getStatus());
        values.put(WorkDatabaseHelper.COLUMN_COLAB, work.getCollaborate());

        db.update(WorkDatabaseHelper.TABLE_NAME, values, WorkDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(work.getId())});
        close();
    }

    public void deleteBook(Work work) {
        open();
        db.delete(WorkDatabaseHelper.TABLE_NAME, WorkDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(work.getId())});
        close();
    }

    public List<Work> getAllWorks() {
        open();
        List<Work> workList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + WorkDatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String des = cursor.getString(2);
                String date = cursor.getString(3);
                String status = cursor.getString(4);
                Integer t = cursor.getInt(5);
                Boolean colab = (t != 0);
                Work work = new Work(id, name, des, date, status, colab);
                workList.add(work);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return workList;
    }

    public List<Work> searchByDes(String des) {
        open();
        List<Work> works = new ArrayList<>();

        String sql = "SELECT * FROM " + WorkDatabaseHelper.TABLE_NAME + " WHERE " + WorkDatabaseHelper.COLUMN_DES + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + des + "%"};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dest = cursor.getString(2);
                String date = cursor.getString(3);
                String status = cursor.getString(4);
                Integer t = cursor.getInt(5);
                Boolean colab = (t != 0);
                Work work = new Work(id, name, dest, date, status, colab);
                works.add(work);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return works;
    }

    public List<Work> searchByWork(String work) {
        open();
        List<Work> works = new ArrayList<>();

        String sql = "SELECT * FROM " + WorkDatabaseHelper.TABLE_NAME + " WHERE " + WorkDatabaseHelper.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + work + "%"};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dest = cursor.getString(2);
                String date = cursor.getString(3);
                String status = cursor.getString(4);
                Integer t = cursor.getInt(5);
                Boolean colab = (t != 0);
                Work workk = new Work(id, name, dest, date, status, colab);
                works.add(workk);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return works;
    }

    public List<Work> searchByWorkAndDes(String work, String des) {
        open();
        List<Work> works = new ArrayList<>();

        String sql = "SELECT * FROM " + WorkDatabaseHelper.TABLE_NAME + " WHERE " + WorkDatabaseHelper.COLUMN_DES + " LIKE ? AND " + WorkDatabaseHelper.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + des + "%", "%" + work + "%"};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dest = cursor.getString(2);
                String date = cursor.getString(3);
                String status = cursor.getString(4);
                Integer t = cursor.getInt(5);
                Boolean colab = (t != 0);
                Work workk = new Work(id, name, dest, date, status, colab);
                works.add(workk);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return works;
    }


    public List<Work> searchByStatus(String status) {
        open();
        List<Work> works = new ArrayList<>();

        String sql = "SELECT * FROM " + WorkDatabaseHelper.TABLE_NAME + " WHERE " + WorkDatabaseHelper.COLUMN_STATUS + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + status + "%"};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dest = cursor.getString(2);
                String date = cursor.getString(3);
                String statu = cursor.getString(4);
                Integer t = cursor.getInt(5);
                Boolean colab = (t != 0);
                Work workk = new Work(id, name, dest, date, statu, colab);
                works.add(workk);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return works;
    }

}
