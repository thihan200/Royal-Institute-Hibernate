package lk.ijse.royalInstitute.dao;

import lk.ijse.royalInstitute.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO <Entity extends SuperEntity,ID extends Serializable> {
    public boolean add(Entity entity)throws Exception;

    public boolean delete(Entity entity)throws Exception;

    public boolean update(Entity entity)throws Exception;

    public List<Entity> getAll()throws Exception;

    public Entity search(ID id)throws Exception;
}
