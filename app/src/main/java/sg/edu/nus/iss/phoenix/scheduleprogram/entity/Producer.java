package sg.edu.nus.iss.phoenix.scheduleprogram.entity;

/**
 * Created by Meow on 2018/9/27.
 */

public class Producer {

    private String producerId;


    public Producer(String producerId) {
        this.producerId= producerId;
    }

    public String getProducerId() {
        return producerId;
    }


    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }


}
