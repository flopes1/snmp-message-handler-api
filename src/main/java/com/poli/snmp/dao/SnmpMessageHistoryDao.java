package com.poli.snmp.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poli.snmp.dao.util.GenericDao;
import com.poli.snmp.model.SnmpMessageHistory;

/**
 * 
 * @author Filipe Mendes
 *
 */

@Repository
public class SnmpMessageHistoryDao implements ISnmpMessageHistoryDao
{

    private static final Logger log = LoggerFactory.getLogger(SnmpMessageHistoryDao.class);

    @Autowired
    private GenericDao<SnmpMessageHistory> snmpMessageDao;

    @PostConstruct
    public void initEmployeeDao()
    {
        log.info("Initializing generic repository access");
        this.snmpMessageDao.setClassType(SnmpMessageHistory.class);
        log.info("Generic repository initialized with entity :" + SnmpMessageHistory.class.getName());
    }

    @Override
    public void addSnmpMessageHistory(SnmpMessageHistory snmpMessage)
    {
        this.snmpMessageDao.addEntity(snmpMessage);
    }

    @Override
    public SnmpMessageHistory getLastMessageHistory()
    {
        List<SnmpMessageHistory> allMessages = this.getAllMessagesHistoryDesc();

        SnmpMessageHistory lastMessage = null;

        if (allMessages != null && allMessages.size() > 0)
        {
            lastMessage = allMessages.get(0);
        }

        return lastMessage;
    }

    @Override
    public List<SnmpMessageHistory> getAllMessagesHistoryDesc()
    {
        List<SnmpMessageHistory> result = this.snmpMessageDao.getAllEntitiesOrdered("", false);

        if (result != null)
        {
            Collections.reverse(result);
        }
        return result;
    }

}
