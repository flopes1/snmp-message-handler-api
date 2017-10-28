package com.poli.snmp.model;

import java.io.Serializable;

public class SnmpMessage implements Serializable
{

    private static final long serialVersionUID = 3414799318453491796L;

    private String community;
    private String objectId;
    private String targetIp;
    private String targetPort;

    public SnmpMessage()
    {
    }

    public SnmpMessage(String commynity, String objectId, String targetIp, String targetPort)
    {
        this.community = commynity;
        this.objectId = objectId;
        this.targetIp = targetIp;
        this.targetPort = targetPort;
    }

    public String getCommunity()
    {
        return community;
    }

    public void setCommunity(String community)
    {
        this.community = community;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public String getTargetIp()
    {
        return targetIp;
    }

    public void setTargetIp(String targetIp)
    {
        this.targetIp = targetIp;
    }

    public String getTargetPort()
    {
        return targetPort;
    }

    public void setTargetPort(String targetPort)
    {
        this.targetPort = targetPort;
    }

}
