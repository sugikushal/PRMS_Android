package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

/**
 * Created by SuganthiSugumar on 2018/9/21.
 */

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ReviewSelectScheduleProgramScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrieveScheduleDelegate;

public class ReviewSelectScheduledProgramController {
    // Tag for logging.
    private static final String TAG = ReviewSelectScheduledProgramController.class.getName();
    private ReviewSelectScheduleProgramScreen reviewSelectScheduleProgramScreen;
    private ProgramSlot psSelected = null;

    public void startUseCase() {
        psSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleProgramScreen.class);
       // intent.putExtra("action",action);
        MainController.displayScreen(intent);
    }
    public void startUseCase(String action) {
        psSelected = null;
        //this.action=action;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleProgramScreen.class);
        intent.putExtra("action",action);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectScheduleProgramScreen reviewSelectScheduleProgramScreen) {
        this.reviewSelectScheduleProgramScreen = reviewSelectScheduleProgramScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }

    /*public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleProgramScreen.showPrograms(programSlots);
    }*/

    public void selectProgramSlot(ProgramSlot programSlot,String action) {
        psSelected = programSlot;
        Log.v(TAG, "Selected radio program: " + programSlot.getName() + ".");
        // To call the base use case controller with the selected program Slot.
        // At present, call the MainController instead.
        if (action!=null && action.equals("copy"))
        {
            ControlFactory.getMaintainScheduleController().selectCopyScheduleProgram(psSelected);
        }
        else {
            ControlFactory.getMaintainScheduleController().selectEditScheduleProgram(psSelected);
        }
    }

    public void selectCancel() {
        psSelected = null;
        Log.v(TAG, "Cancelled the seleciton of radio program.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedSchedule(psSelected);
    }
    public void scheduleRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleProgramScreen.showSchedulePrograms(programSlots);
    }
    /**
     *onDisplayProgramSlotList-method.This method is to show the list of the Program Slot.
     * @param scheduleListScreen
     */
    public void onDisplayProgramSlotList(ReviewSelectScheduleProgramScreen scheduleListScreen) {
        this.reviewSelectScheduleProgramScreen = scheduleListScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }

}
