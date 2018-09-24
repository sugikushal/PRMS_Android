package sg.edu.nus.iss.phoenix.scheduleprogram.entity;

public class ProgramSlot {
    private String programName;
    private String programSlotDuration;
    private String programSlotDate;
    private String programSlotStartTime;

    public ProgramSlot(String programSlotName,String programSlotDuration,
                       String programSlotDate,String programSlotStartTime){
        this.programSlotDate = programSlotDate;
        this.programSlotDuration = programSlotDuration;
        this.programName = programName;
        this.programSlotStartTime = programSlotStartTime;
    }

    public String getProgramSlotDate() {
        return programSlotDate;
    }

    public String getProgramSlotName() {
        return programName;
    }

    public String getProgramSlotStartTime() {
        return programSlotStartTime;
    }

    public String getProgramSlotDuration() {
        return programSlotDuration;
    }

    public void setProgramSlotDate(String programSlotDate) {
        this.programSlotDate = programSlotDate;
    }

    public void setProgramSlotDuration(String programSlotDuration) {
        this.programSlotDuration = programSlotDuration;
    }

    public void setProgramSlotName(String programName) {
        this.programName = programName;
    }

    public void setProgramSlotStartTime(String programSlotStartTime) {
        this.programSlotStartTime = programSlotStartTime;
    }
}
