package lk.ijse.royalInstitute.bo.custom;

import lk.ijse.royalInstitute.bo.SuperBO;
import lk.ijse.royalInstitute.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public boolean addCourse(CourseDTO courseDTO)throws Exception;

    public boolean deleteCourse(CourseDTO courseDTO)throws Exception;

    public boolean updateCourse(CourseDTO courseDTO)throws Exception;

    public CourseDTO searchCourse(String id)throws Exception;

    public List<CourseDTO> getAllCourses()throws Exception;
}
