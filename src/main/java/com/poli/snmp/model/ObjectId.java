package com.poli.snmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "obj_object_id")
public class ObjectId implements Serializable
{
    private static final long serialVersionUID = 248353976324095844L;

    private int id;
    private String objectId;
    private String description;

    public ObjectId()
    {
    }

    public ObjectId(int id, String objectId, String description)
    {
        this.id = id;
        this.objectId = objectId;
        this.description = description;
    }

    @Id
    @Column(name = "obj_id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Id
    @Column(name = "obj_obj_object_id")
    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    @Id
    @Column(name = "obj_dec_description")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Id:" + this.id + "\nObjectId: " + this.objectId + "\nDescription: " + this.description;
    }

}
