package sg.edu.nus.iss.phoenix.scheduleprogram.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.DeleteScheduleDelegate;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.UpdateScheduleDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Producer;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate.RetrieveScheduleDelegate;

public class MaintainScheduleController {
    // Tag for logging.
    private static final String TAG = MaintainScheduleController.class.getName();
    private ProgramSlot psedit = null;

    private ScheduleListScreen scheduleListScreen;
    private ScheduleScreen scheduleScreen;
    private RadioProgram rpSelected = null;
    private Presenter prSelected = null;
    private Producer proSelected = null;
    /**
     * startUseCase-method.This method is starting the use case and display information on screen.
     */
    public void startUseCase() {
        psedit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleListScreen.class);
        MainController.displayScreen(intent);
    }


    /**
     *selectCreateScheduleProgram-method.This method is start create use case.
     */
    public void selectCreateScheduleProgram() {
        psedit = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }




    /**
     *selectCreateScheduleProgram-method.This method is to create Schedule delegate.
     * @param ps representation for the resource
     */
    public void selectCreateScheduleProgram(ProgramSlot ps) {
        new CreateScheduleDelegate(this).execute(ps);
    }


    /**
     *scheduleCreated-method.This method is helping to check whether schedule is created successfully.
     * @param success represents whether the method was successfully executed
     */
    public void scheduleCreated(boolean success) {
        // Go back to ScheduleList screen with refreshed program Slots.
        startUseCase();
    }





    /**
     *selectUpdateScheduleProgram-method.This method is to create a new Update Schedule Delegate.
     * @param ps representation for the resource.
     */
    public void selectUpdateScheduleProgram(ProgramSlot ps) {
        new UpdateScheduleDelegate(this).execute(ps);
    }

    /**
     *scheduleUpdated-method.This method is helping to check whether schedule is updated successfully.
     * @param success represents whether the method was successfully executed
     */
    public void scheduleUpdated(boolean success) {
        // Go back to ScheduleList screen with refreshed program Slots.
        startUseCase();
    }

    /**
     *selectCancelCreateEditSchedule-method.
     */
    public void selectCancelCreateEditSchedule() {
        // Go back to ScheduleList screen with refreshed schedules.
        startUseCase();
    }




    public void selectEditScheduleProgram(ProgramSlot programSlot) {
        psedit = programSlot;
        Log.v(TAG, "Editing Program Slot " + programSlot.getName() + "...");

        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayScheduleProgram(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        if(psedit == null)
            scheduleScreen.createScheduleProgram();
        else
            scheduleScreen.editScheduleProgram(psedit);
    }
    public void onDisplayScheduleProgram(ScheduleScreen scheduleScreen,ProgramSlot ps) {
        this.scheduleScreen = scheduleScreen;
        if(ps == null)
            scheduleScreen.createScheduleProgram();
        else
            scheduleScreen.editScheduleProgram(ps);
    }



    public void selectedProgram(RadioProgram rpSelected) {
        this.rpSelected = rpSelected;
        scheduleScreen.setRadioProgram(rpSelected);
    }

    public void selectedPresenter(Presenter prSelected) {
        this.prSelected = prSelected;
        scheduleScreen.setPresenter(prSelected);
    }
    public void selectedProducer(Producer proSelected) {
        this.proSelected = proSelected;
        scheduleScreen.setProducer(proSelected);
    }

    public void scheduleDeleted(boolean success) {
        // Go back to ScheduleList screen with refreshed program Slots.
        startUseCase();
    }

    public void selectDeleteProgram(ProgramSlot ps) {
        new DeleteScheduleDelegate(this).execute(ps);
    }
    public void maintainedSchedule() {
        ControlFactory.getMainController().maintainedSchedule();
    }

    public void reviewedSelectprogramslot()
    {
        ControlFactory.getMaintainScheduleController().startUseCase();
    }
}
