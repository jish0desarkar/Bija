package com.example.user.appbijatraining;

public class JSONGetter {
    String prg_id;
    String title;
    String staff_id;
    String company_id;
    String call_loop_id;
    String date;
    String remark;
    String trainer_id;
    String trainer_cnf;
    String trainers;

    public JSONGetter(String prg_id, String title,
                   String staff_id,
                   String company_id,
                   String call_loop_id,
                   String date,
                   String remark,
                   String trainer_id,
                   String trainer_cnf,
                   String trainers) {
        this.prg_id = prg_id;
        this.title = title;
        this.staff_id = staff_id;
        this.company_id = company_id;
        this.call_loop_id = call_loop_id;
        this.date = date;
        this.remark = remark;
        this.trainer_cnf = trainer_cnf;
        this.trainer_id = trainer_id;
        this.trainers = trainers;
    }

    public String getPrg_id()
    {
        return this.prg_id;
    }
    public String getTitle()
    {
        return  this.title;
    }
    public String getStaff_id()
    {
        return this.staff_id;
    }
    public String getCompany_id()
    {
        return  this.company_id;
    }
    public String getCall_loop_id()
    {
        return this.call_loop_id;
    }
    public String getDate()
    {
        return this.date;
    }
    public String getRemark()
    {
        return this.remark;
    }
    public String getTrainer_id()
    {
        return this.trainer_id;
    }
    public String getTrainer_cnf()
    {
        return this.trainer_cnf;
    }
    public String getTrainers() {
        return this.trainers;
    }


}
