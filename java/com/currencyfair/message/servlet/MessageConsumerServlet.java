/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message.servlet;

import com.currencyfair.message.Message;
import com.currencyfair.message.MessageFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.json.JSONObject;
import java.text.SimpleDateFormat;

/**
 * Servlet to consume messages
 * This would be better with websockets probably, but there is no time for it
 * @author paulo
 */
public class MessageConsumerServlet extends HttpServlet {

    
    @EJB
    private MessageFacadeLocal messageFacade;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!parseRequestBody(request)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {
            response.sendError(HttpServletResponse.SC_OK);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Currency Fair Message Consumer Servlet";
    }// </editor-fold>
    
    private boolean parseRequestBody(HttpServletRequest request) {

        StringBuilder jb = new StringBuilder();
        String line = null;
        
        try {
          java.io.BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
               
        } catch (Exception e) { 
            /*report an error*/
            return false;
        }

        try {
            
          if(jb.length()>0) {
             
              JSONObject jsonObject = new JSONObject(jb.toString());
              Message erate = new Message();
              erate.setUserid(jsonObject.getString("userId"));
              erate.setCurrencyFrom(jsonObject.getString("currencyFrom"));
              erate.setCurrencyTo(jsonObject.getString("currencyTo"));
              erate.setAmountSell(jsonObject.getDouble("amountSell"));
              erate.setAmountBuy(jsonObject.getDouble("amountBuy"));
              erate.setRate(jsonObject.getDouble("rate"));
              String timePlaced = jsonObject.getString("timePlaced");
              erate.setTimePlaced(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(timePlaced));
              erate.setOriginatingCountry(jsonObject.getString("originatingCountry"));
              
              messageFacade.create(erate);  
          }

        } catch (Exception e) {
          // crash and burn ? :-)
          return false;

        }

        return true;
    }
}
