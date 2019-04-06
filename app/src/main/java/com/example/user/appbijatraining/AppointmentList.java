package com.example.user.appbijatraining;

public class AppointmentList {

    String prg_id;
    String title;
    String trainer;
    String addedBy;


    public AppointmentList(String prg_id, String title, String trainer, String addedBy) {
        this.prg_id = prg_id;
        this.title = title;
        this.trainer = trainer;
        this.addedBy = addedBy;

    }

    public String getPrg_id() {
        return prg_id;
    }

    public String getTitle() {
        return title;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getAddedBy() {
        return addedBy;
    }
}
