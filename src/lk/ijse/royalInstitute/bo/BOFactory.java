package lk.ijse.royalInstitute.bo;

import lk.ijse.royalInstitute.bo.custom.impl.CourseBOImpl;
import lk.ijse.royalInstitute.bo.custom.impl.RegistrationBOImpl;
import lk.ijse.royalInstitute.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getInstance(){ return (boFactory == null) ? boFactory = new BOFactory() : boFactory; }

    public enum BOType{
        STUDENT, COURSE, REGISTRATION
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case STUDENT:
                return new StudentBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            default:
                return null;

        }
    }

}
