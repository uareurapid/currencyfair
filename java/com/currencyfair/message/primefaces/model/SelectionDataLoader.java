/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.currencyfair.message.primefaces.model;


import com.currencyfair.message.exception.RemoveEntitiyException;
import java.util.Collection;
import java.util.List;

/**
 * @author paulo
 * Interface to be implemented by the datamodel implementations
 */
public interface SelectionDataLoader {

    public void loadData(List<? extends Object> objc, Object facade);

    public void clearData() throws RemoveEntitiyException;

    public void removeObject(Object toRemove) throws RemoveEntitiyException;

    public void removeCollection(Collection toRemove) throws RemoveEntitiyException;
}
