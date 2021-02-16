package lk.ijse.royalInstitute.dao.custom.impl;

import lk.ijse.royalInstitute.dao.custom.StudentDAO;
import lk.ijse.royalInstitute.entity.Student;
import lk.ijse.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean delete(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Query from_student = session.createQuery("from Student");

        List<Student> list = from_student.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Student search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();

        session.close();

        return student;
    }

    @Override
    public String getLastStudentId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("select sid From Student order BY sid desc LIMIT 1");
        String id = (String) sqlQuery.uniqueResult();

        transaction.commit();

        session.close();
        return id;
    }
}
