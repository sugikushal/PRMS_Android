package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

/**
 * Created by GengHui on 2018/9/26.
 */


import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrievePresenterDelegate;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ReviewSelectPresenterScreen;

import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;

public class ReviewSelectPresenterController {
    //  Tag for logging.
    private static final String TAG = ReviewSelectPresenterController.class.getName();
    private ReviewSelectPresenterScreen reviewSelectPresenterScreen;
    private Presenter prSelected = null;

    public void startUseCase() {
        prSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectPresenterScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectPresenterScreen reviewSelectPresenterScreen) {
        this.reviewSelectPresenterScreen = reviewSelectPresenterScreen;
        new RetrievePresenterDelegate(this).execute("all");

    }

    public void presenterRetrieved(List<Presenter> presenters) {
        reviewSelectPresenterScreen.showPresenters(presenters);
    }

    public void selectPresenter(Presenter presenter) {
        prSelected = presenter;
        Log.v(TAG, "Selected presenter: " + presenter.getPresenterId() + ".");
        // To call the base use case controller with the selected radio program.
        // At present, call the MainController instead.
        //ControlFactory.getMainController().selectedProgram(rpSelected);
        ControlFactory.getMaintainScheduleController().selectedPresenter(prSelected);
    }

    public void selectCancel() {
        prSelected = null;
        Log.v(TAG, "Cancelled the seleciton of presenter.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedPresenter(prSelected);
    }


}
