package sg.edu.nus.iss.phoenix.scheduleprogram.entity;

/**
 * Created by GengHui on 2018/9/27.
 */

public class Presenter {

    private String presenterId;


    public Presenter(String presenterId) {
        this.presenterId= presenterId;
    }

    public String getPresenterId() {
        return presenterId;
    }


    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }


}
