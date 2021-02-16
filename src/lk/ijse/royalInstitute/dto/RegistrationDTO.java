package lk.ijse.royalInstitute.dto;

import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Student;

public class RegistrationDTO implements SuperDTO{
    private String regNo;
    private String regDate;
    private Double regFee;
    private String studentId;
    private String courseId;

    public RegistrationDTO(String regNo, String regDate, Double regFee, String studentId, String courseId) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public RegistrationDTO() {
    }

    public RegistrationDTO(String regNo, String regDate, Double regFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentId = String.valueOf(student);
        this.courseId = String.valueOf(course);
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
}
