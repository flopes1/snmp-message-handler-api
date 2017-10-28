package com.poli.snmp.service;

import java.util.List;

import com.poli.snmp.model.SnmpMessageHistory;

public interface ISnmpMessageHistoryService
{

    void addSnmpMessageHistory(SnmpMessageHistory history);

    SnmpMessageHistory getLastMessageHistory();

    List<SnmpMessageHistory> getAllMessagesHistoryDesc();

}
