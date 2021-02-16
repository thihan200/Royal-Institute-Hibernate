package lk.ijse.royalInstitute.bo.custom.impl;

import lk.ijse.royalInstitute.bo.custom.StudentBO;
import lk.ijse.royalInstitute.dao.DAOFactory;
import lk.ijse.royalInstitute.dao.custom.StudentDAO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.delete(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender()));
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

    @Override
    public String getLastStudentId() throws Exception {
        return studentDAO.getLastStudentId();
    }
}
