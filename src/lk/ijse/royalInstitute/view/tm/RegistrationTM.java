package lk.ijse.royalInstitute.view.tm;

import javafx.scene.control.Button;
import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Student;

public class RegistrationTM {
    private String regNo;
    private String regDate;
    private Double regFee;
    private String studentId;
    private String courseId;
    private Button btn;

    public RegistrationTM(String regNo, String regDate, Double regFee, String studentId, String courseId, Button btn) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentId = studentId;
        this.courseId = courseId;
        this.btn = btn;
    }

    public RegistrationTM() {
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Double getRegFee() {
        return regFee;
    }

    public void setRegFee(Double regFee) {
        this.regFee = regFee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
