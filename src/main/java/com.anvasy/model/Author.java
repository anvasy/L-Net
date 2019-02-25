package com.anvasy.model;

import java.util.Date;

public class Author {
    private int id;
    private String fio;
    private Date date;

    public Author()  {}

    public Author(int id) {
        this.id = id;
    }

    public Author(String fio, Date date) {
        this.date = date;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
