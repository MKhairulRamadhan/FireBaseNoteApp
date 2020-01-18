package com.example.firebasenoteapp;

public class MyNotes {
    String titlenotes, datenotes, descnotes, keynotes;

    public MyNotes() {
    }

    public MyNotes(String titlenotes, String datenotes, String descnotes, String keynotes) {
        this.titlenotes = titlenotes;
        this.datenotes = datenotes;
        this.descnotes = descnotes;
        this.keynotes = keynotes;
    }

    public String getKeynotes() {
        return keynotes;
    }

    public void setKeynotes(String keynotes) {
        this.keynotes = keynotes;
    }

    public String getTitlenotes() {
        return titlenotes;
    }

    public void setTitlenotes(String titlenotes) {
        this.titlenotes = titlenotes;
    }

    public String getDatenotes() {
        return datenotes;
    }

    public void setDatenotes(String datenotes) {
        this.datenotes = datenotes;
    }

    public String getDescnotes() {
        return descnotes;
    }

    public void setDescnotes(String descnotes) {
        this.descnotes = descnotes;
    }
}
