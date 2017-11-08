package com.poli.snmp.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.poli.snmp.model.SnmpMessage;
import com.poli.snmp.model.SnmpMessageHistory;

public interface ISnmpMessageHistoryService
{

    void addSnmpMessageHistory(SnmpMessageHistory history);

    SnmpMessageHistory getLastMessageHistory();

    List<SnmpMessageHistory> getAllMessagesHistoryDesc();
    
    public String processSnmpMessage(SnmpMessage snmpMessage) throws NumberFormatException, UnknownHostException, IOException;

}
