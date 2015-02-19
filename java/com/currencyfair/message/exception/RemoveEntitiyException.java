/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message.exception;

/**
 * @author paulo
 */
public class RemoveEntitiyException extends Exception {

    public RemoveEntitiyException() {
        super("Error removing Entity!");
    }

    public RemoveEntitiyException(Throwable e) {
        super(e);
    }

    public RemoveEntitiyException(String msg) {
        super(msg);
    }
}
