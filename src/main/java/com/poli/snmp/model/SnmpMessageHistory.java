package com.poli.snmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "snmp_message_history")
public class SnmpMessageHistory implements Serializable
{

    private static final long serialVersionUID = 3555149641948212561L;

    private int id;
    private String objectId;
    private String targetIP;
    private String response;

    public SnmpMessageHistory()
    {
    }

    public SnmpMessageHistory(int id, String objectId, String targetIP, String response)
    {
        this.objectId = objectId;
        this.targetIP = targetIP;
        this.response = response;
    }

    @Id
    @Column(name = "smg_id")
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name = "smg_obj_object_id")
    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    @Column(name = "smg_tgt_target_ip")
    public String getTargetIP()
    {
        return targetIP;
    }

    public void setTargetIP(String targetIP)
    {
        this.targetIP = targetIP;
    }

    @Column(name = "smg_msg_message_response")
    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id + "\nObject Id: " + this.objectId + "\nTarget IP: " + this.targetIP + "\nResponse: "
                + this.response;
    }

}
