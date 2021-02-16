package lk.ijse.royalInstitute.dao.custom.impl;

import lk.ijse.royalInstitute.dao.custom.RegistrationDAO;
import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Registration;
import lk.ijse.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Serializable save = session.save(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean delete(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean update(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public List<Registration> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Query from_registration = session.createQuery("from Registration ");

        List<Registration> list = from_registration.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Registration search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Registration registration = session.get(Registration.class, id);

        transaction.commit();

        session.close();

        return registration;
    }

    @Override
    public String getLastRegistrationId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("select regNo From Registration order BY regNo desc LIMIT 1");
        String regNo = (String) sqlQuery.uniqueResult();

        transaction.commit();

        session.close();
        return regNo;
    }
}
