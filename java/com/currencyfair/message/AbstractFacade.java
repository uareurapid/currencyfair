/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.currencyfair.message;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;


/**
 * @author paulo
 */

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;


    //protected static final Logger LOG = Logger.getLogger(AbstractFacade.class);

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();


    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(T entity) throws SecurityException {

        getEntityManager().persist(entity);

    }


    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(T entity) throws SecurityException {

        getEntityManager().remove(getEntityManager().merge(entity));
     
    }

    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeAll(List<T> entity) throws SecurityException {

       for (T ent : entity) {
          getEntityManager().remove(getEntityManager().merge(entity));
       }

    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


}
