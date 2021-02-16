package lk.ijse.royalInstitute.view.tm;

import javafx.scene.control.Button;

public class CourseTM {
    private String code;
    private String courseName;
    private String courseType;
    private String duration;
    private Button btn;

    public CourseTM(String code, String courseName, String courseType, String duration, Button btn) {
        this.code = code;
        this.courseName = courseName;
        this.courseType = courseType;
        this.duration = duration;
        this.btn = btn;
    }

    public CourseTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
