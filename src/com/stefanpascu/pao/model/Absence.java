package com.stefanpascu.pao.model;

import java.util.Date;

public class Absence {

    private Date date;
    private Subject subject;
    private boolean motivated;

    public Absence(Date date, Subject subject) {
        this.date = date;
        this.subject = subject;
        this.motivated = false;
    }

    public void setMotivated() {
        this.motivated = true;
    }

    public Date getDate() {
        return date;
    }

    public boolean isMotivated() {
        return motivated;
    }

    public Subject getSubject() {
        return subject;
    }
}
