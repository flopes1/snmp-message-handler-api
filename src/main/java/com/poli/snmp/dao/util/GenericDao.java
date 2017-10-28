package com.poli.snmp.dao.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Filipe Mendes
 *
 */

@Repository
@Scope("prototype")
public class GenericDao<T>
{

    private static Logger log;

    private Class<T> clazz = null;

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession()
    {
        log.info("Getting current session of data base");
        Session session = null;
        try
        {
            session = this.sessionFactory.getCurrentSession();
            log.info("Getting current session successful");
        }
        catch (RuntimeException e)
        {
            log.error("Getting current session failed", e);
            throw e;
        }
        return session;
    }

    public void setClassType(Class<T> clazz)
    {
        this.clazz = clazz;
        log = LoggerFactory.getLogger(this.clazz);
    }

    public void addEntity(T entity)
    {
        log.info("Persisting " + this.clazz.getName() + ": " + entity.toString() + " instance");
        try
        {
            this.getCurrentSession().persist(entity);
            log.info("Persisting " + this.clazz.getName() + " successful ");
        }
        catch (RuntimeException e)
        {
            log.error("Persisting " + this.clazz.getName() + ": " + entity.toString() + "failed", e);
            throw e;
        }
    }

    public void removeEntity(T entity)
    {
        log.info("Removing " + this.clazz.getName() + ": " + entity.toString() + " instance");
        try
        {
            this.getCurrentSession().delete(entity);
            log.info("Removing " + this.clazz.getName() + " successful ");
        }
        catch (RuntimeException e)
        {
            log.error("Removing " + this.clazz.getName() + ": " + entity.toString() + "failed", e);
            throw e;
        }
    }

    public List<T> getAllEntitiesOrdered(String orderClause, boolean desc)
    {
        log.info("Getting all entities from " + this.clazz.getName() + " in order by: " + orderClause);

        List<T> entities = null;

        try
        {
            CriteriaBuilder cBuilder = this.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<T> criteria = cBuilder.createQuery(this.clazz);

            criteria.select(criteria.from(this.clazz));
            if (orderClause != null && !orderClause.isEmpty())
            {
                if (desc)
                {
                    criteria.orderBy(cBuilder.desc(criteria.from(this.clazz).get(orderClause)));
                }
                else
                {
                    criteria.orderBy(cBuilder.asc(criteria.from(this.clazz).get(orderClause)));
                }
            }

            entities = this.getCurrentSession().createQuery(criteria).getResultList();
            log.info("Getting all entities " + this.clazz.getName() + " successful");
        }
        catch (RuntimeException e)
        {
            log.error("Getting all entities from " + this.clazz.getName() + "failed", e);
            throw e;
        }
        return entities;

    }

    public List<T> getAllEntities()
    {
        return this.getAllEntitiesOrdered("", false);
    }

}
