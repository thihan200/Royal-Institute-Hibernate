package lk.ijse.royalInstitute.dao.custom;

import lk.ijse.royalInstitute.dao.SuperDAO;
import lk.ijse.royalInstitute.entity.Student;

public interface StudentDAO extends SuperDAO<Student, String> {

    public String getLastStudentId()throws Exception;
}
