package com.poli.snmp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poli.snmp.dao.IObjectIdDao;
import com.poli.snmp.model.ObjectId;

/**
 * 
 * @author Filipe Mendes
 *
 */

@Service
@Transactional
public class ObjectIdService implements IObjectIdService
{
    
    @Autowired
    private IObjectIdDao employeeDao;

    @Override
    public List<ObjectId> getAllObjectIds()
    {
        return this.employeeDao.getAllObjectsId();
    }
    
}
