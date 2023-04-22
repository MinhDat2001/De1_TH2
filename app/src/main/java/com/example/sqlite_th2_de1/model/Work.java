package com.example.sqlite_th2_de1.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Work implements Serializable {
    public static Comparator<Work> dateComparator = new Comparator<Work>() {
        public int compare(Work w1, Work w2) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d1 = format.parse(w1.getDate());
                Date d2 = format.parse(w2.getDate());
                return d1.compareTo(d2);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }
    };

    private static int lastId = 0;
    private int id;
    private String name;
    private String description;
    private String date;
    private String status;
    private boolean collaborate;

    public Work(String name, String description, String date, String status, boolean collaborate) {
        lastId++;
        this.id = lastId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public Work(int id, String name, String description, String date, String status, boolean collaborate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
        this.collaborate = collaborate;
    }

    public Work() {
        lastId++;
        this.id = lastId;
    }

    public static void setLastId(int lastId) {
        Work.lastId = lastId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getCollaborate() {
        return collaborate;
    }

    public void setCollaborate(boolean collaborate) {
        this.collaborate = collaborate;
    }

    @Override
    public String toString() {
        return "Work{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", date='" + date + '\'' + ", status='" + status + '\'' + ", collaborate=" + collaborate + '}';
    }
}
