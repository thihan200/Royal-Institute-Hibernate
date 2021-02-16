package lk.ijse.royalInstitute.bo.custom.impl;

import lk.ijse.royalInstitute.bo.custom.RegistrationBO;
import lk.ijse.royalInstitute.dao.DAOFactory;
import lk.ijse.royalInstitute.dao.custom.CourseDAO;
import lk.ijse.royalInstitute.dao.custom.RegistrationDAO;
import lk.ijse.royalInstitute.dao.custom.StudentDAO;
import lk.ijse.royalInstitute.dto.CourseDTO;
import lk.ijse.royalInstitute.dto.RegistrationDTO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Registration;
import lk.ijse.royalInstitute.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean addRegistration(RegistrationDTO registrationDTO) throws Exception {
        return registrationDAO.add(new Registration(registrationDTO.getRegNo(),
                registrationDTO.getRegDate(),
                registrationDTO.getRegFee(),
                studentDAO.search(registrationDTO.getStudentId()),
                courseDAO.search(registrationDTO.getCourseId())));
    }

    @Override
    public boolean deleteRegistration(RegistrationDTO registrationDTO) throws Exception {
        return registrationDAO.delete(new Registration(
                registrationDTO.getRegNo(),
                registrationDTO.getRegDate(),
                registrationDTO.getRegFee(),
                studentDAO.search(registrationDTO.getStudentId()),
                courseDAO.search(registrationDTO.getCourseId())));
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) throws Exception {
        return registrationDAO.update(new Registration(
                registrationDTO.getRegNo(),
                registrationDTO.getRegDate(),
                registrationDTO.getRegFee(),
                studentDAO.search(registrationDTO.getStudentId()),
                courseDAO.search(registrationDTO.getCourseId())));
    }

    @Override
    public RegistrationDTO searchRegistration(String id) throws Exception {

        return null;
    }

    @Override
    public List<RegistrationDTO> getAllRegistrations() throws Exception {
        List<Registration> registrationList = registrationDAO.getAll();

        List<RegistrationDTO> registrationDTOList = new ArrayList<>();

        for (Registration registration : registrationList) {
            registrationDTOList.add(new RegistrationDTO(
                    registration.getRegNo(),
                    registration.getRegDate(),
                    registration.getRegFee(),
                    registration.getStudent(),
                    registration.getCourse()
                    ));
        }
        return registrationDTOList;
    }

    @Override
    public String getLastRegID() throws Exception {
        return registrationDAO.getLastRegistrationId();
    }

    @Override
    public CourseDTO searchCourse(String id) throws Exception {
        Course course = courseDAO.search(id);

        return new CourseDTO(
                course.getCode(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        );
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        List<Course> coursesList = courseDAO.getAll();

        List<CourseDTO> courseDTOList = new ArrayList<>();

        for (Course course : coursesList) {
            courseDTOList.add(new CourseDTO(course.getCode(),
                    course.getCourseName(),
                    course.getCourseType(),
                    course.getDuration()));
        }
        return courseDTOList;
    }

    @Override
    public StudentDTO searchStudent(String id) throws Exception {
        Student student = studentDAO.search(id);

        return new StudentDTO(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDob(),
                student.getGender()
        );
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> studentsList = studentDAO.getAll();

        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : studentsList) {
            studentDTOList.add(new StudentDTO(student.getId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDob(),
                    student.getGender()));
        }
        return studentDTOList;
    }


}
