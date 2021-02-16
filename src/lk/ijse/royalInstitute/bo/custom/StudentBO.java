package lk.ijse.royalInstitute.bo.custom;

import lk.ijse.royalInstitute.bo.SuperBO;
import lk.ijse.royalInstitute.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean addStudent(StudentDTO studentDTO)throws Exception;

    public boolean deleteStudent(StudentDTO studentDTO)throws Exception;

    public boolean updateStudent(StudentDTO studentDTO)throws Exception;

    public StudentDTO searchStudent(String id)throws Exception;

    public List<StudentDTO> getAllStudents()throws Exception;

    public String getLastStudentId() throws Exception;


}
