package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

import android.content.Intent;

import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrieveScheduleDelegate;

public class MaintainScheduleController {
    private static final String TAG = MaintainScheduleController.class.getName();
    private ProgramSlot psedit = null;
    private ScheduleListScreen scheduleListScreen;

    public void startUseCase() {
        psedit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleListScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateScheduleProgram() {
        psedit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    /*public void onDisplayProgramSlotList(ScheduleListScreen scheduleListScreen) {
        this.scheduleListScreen = scheduleListScreen;
        new RetrieveScheduleDelegate(this).execute("all");
    }*/
}
