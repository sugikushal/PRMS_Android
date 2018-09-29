package sg.edu.nus.iss.phoenix.core.android.controller;

import sg.edu.nus.iss.phoenix.authenticate.android.controller.LoginController;
import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ProgramController;
import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ReviewSelectProgramController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectPresenterController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectProducerController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectScheduledProgramController;

public class ControlFactory {
    private static MainController mainController = null;
    private static LoginController loginController = null;
    private static ProgramController programController = null;
    private static ReviewSelectProgramController reviewSelectProgramController = null;
    private static ReviewSelectPresenterController reviewSelectPresenterController = null;
    private static ReviewSelectProducerController reviewSelectProducerController = null;
    private static MaintainScheduleController maintainScheduleController = null;
    private static ReviewSelectScheduledProgramController reviewSelectScheduledProgramController = null;


    public static MainController getMainController() {
        if (mainController == null) mainController = new MainController();
        return mainController;
    }

    public static LoginController getLoginController() {
        if (loginController == null) loginController = new LoginController();
        return loginController;
    }

    public static ProgramController getProgramController() {
        if (programController == null) programController = new ProgramController();
        return programController;
    }

    public static ReviewSelectProgramController getReviewSelectProgramController() {
        if (reviewSelectProgramController == null) reviewSelectProgramController = new ReviewSelectProgramController();
        return reviewSelectProgramController;
    }

    public static ReviewSelectPresenterController getReviewSelectPresenterController() {
        if (reviewSelectPresenterController == null) reviewSelectPresenterController = new ReviewSelectPresenterController();
        return reviewSelectPresenterController;
    }


    public static ReviewSelectProducerController getReviewSelectProducerController() {
        if (reviewSelectProducerController == null) reviewSelectProducerController = new ReviewSelectProducerController();
        return reviewSelectProducerController;
    }



    public static MaintainScheduleController getMaintainScheduleController(){
        if (maintainScheduleController == null) maintainScheduleController = new MaintainScheduleController();
        return maintainScheduleController;
    }

    public static ReviewSelectScheduledProgramController getReviewSelectScheduledProgramController() {
        if (reviewSelectScheduledProgramController == null) reviewSelectScheduledProgramController = new ReviewSelectScheduledProgramController();
        return reviewSelectScheduledProgramController;
    }

    /*public static ReviewSelectPresenterProducerController getReviewSelectPresenterProducerProgramController() {
        if (reviewSelectPresenterProducerProgramController == null) reviewSelectPresenterProducerProgramController = new ReviewSelectPresenterProducerController();
        return reviewSelectPresenterProducerProgramController;
    }*/


}
