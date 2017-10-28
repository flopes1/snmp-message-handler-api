package com.poli.snmp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poli.snmp.dao.ISnmpMessageHistoryDao;
import com.poli.snmp.model.SnmpMessageHistory;

/**
 * 
 * @author Filipe Mendes
 *
 */

@Service
@Transactional
public class SnmpMessageHistoryService implements ISnmpMessageHistoryService
{

    @Autowired
    private ISnmpMessageHistoryDao snmpMessageDao;

    @Override
    public void addSnmpMessageHistory(SnmpMessageHistory snmpMessage)
    {
        this.snmpMessageDao.addSnmpMessageHistory(snmpMessage);
    }

    @Override
    public SnmpMessageHistory getLastMessageHistory()
    {
        return this.snmpMessageDao.getLastMessageHistory();
    }

    @Override
    public List<SnmpMessageHistory> getAllMessagesHistoryDesc()
    {
        return this.snmpMessageDao.getAllMessagesHistoryDesc();
    }

}
