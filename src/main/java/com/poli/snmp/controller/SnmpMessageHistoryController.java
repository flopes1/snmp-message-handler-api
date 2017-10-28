package com.poli.snmp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poli.snmp.controller.util.ResourceNotFoundException;
import com.poli.snmp.model.SnmpMessage;
import com.poli.snmp.model.SnmpMessageHistory;
import com.poli.snmp.service.ISnmpMessageHistoryService;

/**
 * 
 * @author Filipe Mendes
 *
 */

@RestController
@RequestMapping("/snmp/message")
public class SnmpMessageHistoryController
{

    private static final Logger log = LoggerFactory.getLogger(SnmpMessageHistoryController.class);

    @Autowired
    private ISnmpMessageHistoryService snmpServiceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<SnmpMessageHistory> getAllSnmpMessagesDesc()
    {
        log.info("GET /snmp/message called");
        return this.snmpServiceService.getAllMessagesHistoryDesc();
    }

    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public SnmpMessageHistory getLastSnmpMessage()
    {
        log.info("GET /snmp/message/last called");
        SnmpMessageHistory message = this.snmpServiceService.getLastMessageHistory();

        if (message == null)
        {
            throw new ResourceNotFoundException();
        }

        return message;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addSnmpMessage(@RequestBody SnmpMessage message)
    {
        log.debug("POST /snmp/message ({}) called", message.getObjectId());
        
        SnmpMessageHistory history = new SnmpMessageHistory();
        
        // TODO criar o historico e inserir na base com o resultado do socket
        // Criar o metodo do socket na classe SnmpMessageService e chamar aqui
        
        this.snmpServiceService.addSnmpMessageHistory(history);
    }

}
