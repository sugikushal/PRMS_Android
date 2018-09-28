package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

/**
 * Created by Meow on 2018/9/26.
 */


import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrievePresenterDelegate;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrieveProducerDelegate;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ReviewSelectPresenterScreen;

import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ReviewSelectProducerScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Producer;

public class ReviewSelectProducerController {
    //  Tag for logging.
    private static final String TAG = ReviewSelectProducerController.class.getName();
    private ReviewSelectProducerScreen reviewSelectProducerScreen;
    private Producer proSelected = null;

    public void startUseCase() {
        proSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectProducerScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectProducerScreen reviewSelectProducerScreen) {
        this.reviewSelectProducerScreen = reviewSelectProducerScreen;
        new RetrieveProducerDelegate(this).execute("all");

    }

    public void producerRetrieved(List<Producer> producers) {
        reviewSelectProducerScreen.showProducers(producers);
    }

    public void selectProducer(Producer producer) {
        proSelected =  producer;
        Log.v(TAG, "Selected producer: " +  producer.getProducerId() + ".");
        // To call the base use case controller with the selected radio program.
        // At present, call the MainController instead.
        //ControlFactory.getMainController().selectedProgram(rpSelected);
        ControlFactory.getMaintainScheduleController().selectedProducer(proSelected);
    }

    public void selectCancel() {
        proSelected = null;
        Log.v(TAG, "Cancelled the selection of producer.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedProducer(proSelected);
    }


}
