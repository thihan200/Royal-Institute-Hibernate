package lk.ijse.royalInstitute.dao.custom;

import lk.ijse.royalInstitute.dao.SuperDAO;
import lk.ijse.royalInstitute.entity.Registration;

public interface RegistrationDAO extends SuperDAO<Registration, String> {

    public String getLastRegistrationId()throws Exception;
}
