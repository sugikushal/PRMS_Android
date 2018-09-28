package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

import android.content.Intent;
import android.util.Log;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrieveScheduleDelegate;

public class ReviewSelectScheduledProgramController {
    // Tag for logging.
    private static final String TAG = ReviewSelectScheduledProgramController.class.getName();
    private ReviewSelectProgramScreen reviewSelectScheduleProgramScreen;
    private ProgramSlot psSelected = null;

    public void startUseCase() {
        psSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectProgramScreen reviewSelectScheduleProgramScreen) {
        this.reviewSelectScheduleProgramScreen = reviewSelectScheduleProgramScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }

    /*public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleProgramScreen.showPrograms(programSlots);
    }*/

    public void selectProgram(ProgramSlot programSlot) {
        psSelected = programSlot;
        Log.v(TAG, "Selected radio program: " + programSlot.getName() + ".");
        // To call the base use case controller with the selected program Slot.
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedSchedule(psSelected);
    }

    public void selectCancel() {
        psSelected = null;
        Log.v(TAG, "Cancelled the seleciton of radio program.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedSchedule(psSelected);
    }
}
