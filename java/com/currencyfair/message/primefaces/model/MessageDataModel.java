/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.currencyfair.message.primefaces.model;



import com.currencyfair.message.Message;
import com.currencyfair.message.MessageFacadeLocal;
import com.currencyfair.message.exception.RemoveEntitiyException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * @author paulo
 */
public class MessageDataModel extends ListDataModel<Message> implements SelectableDataModel<Message>, SelectionDataLoader, Serializable {

    private MessageFacadeLocal ejbFacade;

    public MessageDataModel() {

    }

    @Override
    public Object getRowKey(Message t) {
        return t.getId();
    }

    @Override
    public Message getRowData(String rowKey) {

        Integer id = Integer.valueOf(rowKey);
        return ejbFacade.find(id);
    }

    @Override
    public void loadData(List<? extends Object> objc, Object facade) {
        this.ejbFacade = (MessageFacadeLocal) facade;
        setWrappedData(objc);
    }

    @Override
    public void clearData() throws RemoveEntitiyException {
        try {

            List <Message> allData = ejbFacade.findAll();
            if(allData!=null) {
                
                ejbFacade.removeAll(allData);
                List<Message> cts = (List<Message>) getWrappedData();
                cts.clear(); 
            }
            
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new RemoveEntitiyException(e.getCause());
        }
    }

    /**
     * @return the ejbFacade
     */
    public MessageFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(MessageFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    @Override
    public void removeObject(Object toRemove) throws RemoveEntitiyException, SecurityException {
        try {
            List<Message> cts = (List<Message>) getWrappedData();
            ejbFacade.remove((Message) toRemove);
            cts.remove((Message) toRemove);
        }  catch (Exception e) {
            throw new RemoveEntitiyException(e.getCause());
        }
    }

    @Override
    public void removeCollection(Collection toRemove) throws RemoveEntitiyException, SecurityException {
        try {
            List<Message> cts = (List<Message>) getWrappedData();
            List<Message> toRemoveList = (List<Message>) toRemove;
            for (Message c : toRemoveList) {
                ejbFacade.remove(c);
            }
            cts.removeAll(toRemove);

        } catch (SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new RemoveEntitiyException(e.getCause());
        }
    }

}
