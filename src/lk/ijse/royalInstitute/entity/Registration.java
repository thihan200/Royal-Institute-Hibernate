package lk.ijse.royalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Registration implements SuperEntity{
    @Id
    private String regNo;
    private String regDate;
    private Double regFee;
    @ManyToOne
    @JoinColumn(name = "studentId" , referencedColumnName = "sid")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "code")
    private Course course;

    public Registration(String regNo, String regDate, Double regFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }



    public Registration() {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo='" + regNo + '\'' +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
