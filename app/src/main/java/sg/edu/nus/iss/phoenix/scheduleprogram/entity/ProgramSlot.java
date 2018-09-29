package sg.edu.nus.iss.phoenix.scheduleprogram.entity;

/**
 * Created by SuganthiSugumar on 2018/9/21.
 */

import java.sql.Date;
import java.sql.Timestamp;

public class ProgramSlot {

    private String name;
    private String duration;
    private String date;
    private String  startTime;
    private String presenter;
    private String producer;

    public ProgramSlot(String name, String typicalDuration, String date, String  startTime, String presenter, String producer) {
        this.name = name;
        this.duration = typicalDuration;
        this.date = date;
        this.startTime = startTime;
        this.presenter = presenter;
        this.producer = producer;
    }



    public ProgramSlot() {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProgramSlot(String scheduleProgramName) {
        this.name = scheduleProgramName;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String  getStartTime() {
        return startTime;
    }

    public void setStartTime(String  startTime) {
        this.startTime = startTime;
    }

}