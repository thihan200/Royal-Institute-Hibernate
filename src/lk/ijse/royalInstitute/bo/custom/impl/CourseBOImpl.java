package lk.ijse.royalInstitute.bo.custom.impl;

import lk.ijse.royalInstitute.bo.custom.CourseBO;
import lk.ijse.royalInstitute.dao.DAOFactory;
import lk.ijse.royalInstitute.dao.custom.CourseDAO;
import lk.ijse.royalInstitute.dao.custom.StudentDAO;
import lk.ijse.royalInstitute.dto.CourseDTO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO coursetDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean addCourse(CourseDTO courseDTO) throws Exception {
        return coursetDAO.add(new Course(courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration()));
    }

    @Override
    public boolean deleteCourse(CourseDTO courseDTO) throws Exception {
        return coursetDAO.delete(new Course(courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration()
                ));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        return coursetDAO.update(new Course(courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration()
                ));
    }

    @Override
    public CourseDTO searchCourse(String id) throws Exception {
        Course course = coursetDAO.search(id);

        return new CourseDTO(
                course.getCode(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        );
    }


    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        List<Course> coursesList = coursetDAO.getAll();

        List<CourseDTO> courseDTOList = new ArrayList<>();

        for (Course course : coursesList) {
            courseDTOList.add(new CourseDTO(course.getCode(),
                    course.getCourseName(),
                    course.getCourseType(),
                    course.getDuration()));
        }
        return courseDTOList;
    }
}
