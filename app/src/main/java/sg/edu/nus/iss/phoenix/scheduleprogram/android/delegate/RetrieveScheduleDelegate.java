package sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate;

import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectScheduledProgramController;

public class RetrieveScheduleDelegate {
    private static final String TAG = RetrieveScheduleDelegate.class.getName();

    private MaintainScheduleController maintainScheduleController = null;
    private ReviewSelectScheduledProgramController reviewSelectScheduledProgramController = null;

    public RetrieveScheduleDelegate(MaintainScheduleController maintainScheduleController) {
        this.reviewSelectScheduledProgramController = null;
        this.maintainScheduleController = maintainScheduleController;
    }

    public RetrieveScheduleDelegate(ReviewSelectScheduledProgramController reviewSelectScheduledProgramController) {
        this.maintainScheduleController = null;
        this.reviewSelectScheduledProgramController = reviewSelectScheduledProgramController;
    }
}
