package lk.ijse.royalInstitute.dao;

import lk.ijse.royalInstitute.dao.custom.impl.CourseDAOImpl;
import lk.ijse.royalInstitute.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.royalInstitute.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){ return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory; }

    public enum DAOType{
        STUDENT, COURSE, REGISTRATION
    }

    public SuperDAO getDAO(DAOType types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
