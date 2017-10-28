package com.poli.snmp.dao;

import java.util.List;

import com.poli.snmp.model.SnmpMessageHistory;

public interface ISnmpMessageHistoryDao
{

    void addSnmpMessageHistory(SnmpMessageHistory snmpMessage);

    SnmpMessageHistory getLastMessageHistory();

    List<SnmpMessageHistory> getAllMessagesHistoryDesc();

}
