package lk.ijse.royalInstitute.bo.custom;

import lk.ijse.royalInstitute.bo.SuperBO;
import lk.ijse.royalInstitute.dto.CourseDTO;
import lk.ijse.royalInstitute.dto.RegistrationDTO;
import lk.ijse.royalInstitute.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public interface RegistrationBO extends SuperBO {
    public boolean addRegistration(RegistrationDTO registrationDTO)throws Exception;

    public boolean deleteRegistration(RegistrationDTO registrationDTO)throws Exception;

    public boolean updateRegistration(RegistrationDTO registrationDTO)throws Exception;

    public RegistrationDTO searchRegistration(String id)throws Exception;

    public List<RegistrationDTO> getAllRegistrations()throws Exception;

    public String getLastRegID() throws Exception;

    public CourseDTO searchCourse(String id)throws Exception;

    public List<CourseDTO> getAllCourses()throws Exception;

    public StudentDTO searchStudent(String id)throws Exception;

    public List<StudentDTO> getAllStudents()throws Exception;
}
