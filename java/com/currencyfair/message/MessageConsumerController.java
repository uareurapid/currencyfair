/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author paulo
 */
@ManagedBean(name = "messageConsumerController")
@ViewScoped
public class MessageConsumerController implements java.io.Serializable{

    
    @EJB
    private MessageFacadeLocal facade;
    
    private Message selectedMessage;
    
    /**
     * Creates a new instance of MessageConsumerController
     * http://gokhan.ozar.net/jsf-primefaces-json-tutorial/
     */
    public MessageConsumerController() {
        System.out.println("here");
        
    }
    @PostConstruct
    public void init() {
        //HttpServletRequest requestObject = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //parseRequestBody(requestObject);
    }

    /**
     * @return the facade
     */
    public MessageFacadeLocal getFacade() {
        return facade;
    }

    /**
     * @param facade the facade to set
     */
    public void setFacade(MessageFacadeLocal facade) {
        this.facade = facade;
    }
    
    public List <Message> getAllMessages() {
        return facade.findAll();
    }

    /**
     * @return the selectedMessage
     */
    public Message getSelectedMessage() {
        return selectedMessage;
    }

    /**
     * @param selectedMessage the selectedMessage to set
     */
    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

  
}
