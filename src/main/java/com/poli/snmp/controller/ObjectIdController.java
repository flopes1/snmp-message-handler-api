package com.poli.snmp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poli.snmp.model.ObjectId;
import com.poli.snmp.service.IObjectIdService;

/**
 * 
 * @author Filipe Mendes
 *
 */

@RestController
@RequestMapping("/objectId")
public class ObjectIdController
{

    private static final Logger log = LoggerFactory.getLogger(ObjectIdController.class);

    @Autowired
    private IObjectIdService objectIdService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ObjectId> getAllEmployees()
    {
        log.info("GET /objectId called");
        return this.objectIdService.getAllObjectIds();
    }

}
