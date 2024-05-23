package uz.pdp.studycenter.model.resp;

public interface StudentData {
    String getFullName();

    Boolean[] getAttendances();

    Long[] getAttendanceIds();

    Long[] getStudentIds();
}
