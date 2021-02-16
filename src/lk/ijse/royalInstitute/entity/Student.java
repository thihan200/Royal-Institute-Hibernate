package lk.ijse.royalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student implements SuperEntity{
    @Id
    private String sid;
    private String studentName;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    @OneToMany(mappedBy = "student")
    private List<Registration> registrationList;

    public Student(String sid, String studentName, String address, String contact, String dob, String gender) {
        this.sid = sid;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(String sid, String studentName, String address, String contact, String dob, String gender, List<Registration> registrationList) {
        this.sid = sid;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.registrationList = registrationList;
    }

    public Student() {
    }


    public String getId() {return sid;}

    public void setId(String id) {
        this.sid = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    @Override
    public String toString() {
        return sid;
    }
}
