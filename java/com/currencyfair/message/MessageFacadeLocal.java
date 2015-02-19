/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author paulo
 */
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public interface MessageFacadeLocal {
    
    public List<Message> findAll();
    public void create(Message msg);
    public void remove(Message msg);
    public void removeAll(List<Message> msg);
    public List<String> getTopXOriginatingCountries(int limit);
    public List<Message> findAllOrdered(int limit);
    public Message find(Object id);
}
