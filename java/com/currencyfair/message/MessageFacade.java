/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.currencyfair.message;


//import static aems.session.ejbs.AbstractFacade.createRolesAllowed;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 * @author paulo
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> implements MessageFacadeLocal {
    
    @PersistenceContext(unitName = "CurrencyFairPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);

    }

    @Override
    public List<String> getTopXOriginatingCountries(int limit) {
        Query query = em.createNamedQuery("Message.findByOriginatingCountryLimit");
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<Message> findAllOrdered(int limit) {
        Query query = em.createNamedQuery("Message.findAllOrdered");
        query.setMaxResults(limit);
        return query.getResultList();
    }

    

  

}
