package com.poli.snmp.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poli.snmp.dao.util.GenericDao;
import com.poli.snmp.model.ObjectId;

/**
 * 
 * @author Filipe Mendes
 *
 */

@Repository
public class ObjectIdDao implements IObjectIdDao
{

    private static final Logger log = LoggerFactory.getLogger(ObjectIdDao.class);

    @Autowired
    private GenericDao<ObjectId> objectIdDao;

    @PostConstruct
    public void initEmployeeDao()
    {
        log.info("Initializing generic repository access");
        this.objectIdDao.setClassType(ObjectId.class);
        log.info("Generic repository initialized with entity :" + ObjectId.class.getName());
    }

    @Override
    public List<ObjectId> getAllObjectsId()
    {
        return this.objectIdDao.getAllEntities();
    }

}
