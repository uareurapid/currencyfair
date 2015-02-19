/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import java.util.List;
import java.util.Hashtable;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author paulo
 */
@ManagedBean(name="processorController")
@ViewScoped
public class PreProcessorController implements Serializable{
    
    public static final int TOP_LIMIT_NUMBER = 50;

    @EJB
    private MessageFacadeLocal facade;
    private List <Message> topMessages; 
    /**
     * Creates a new instance of PreProcessorController
     */
    public PreProcessorController() {
    }
    
    private BarChartModel countryModel;
    private HorizontalBarChartModel amountBuySellModel;
 
    @PostConstruct
    public void init() {
        topMessages = facade.findAllOrdered(TOP_LIMIT_NUMBER);
        createBarModels();
    }
 
    public BarChartModel getCountryModel() {
        return countryModel;
    }
     
    public HorizontalBarChartModel getAmountBuySellModel() {
        return amountBuySellModel;
    }
 
    private BarChartModel initCountryBarModel(Hashtable<String, Integer> data) {
        BarChartModel model = new BarChartModel();
 
        ChartSeries countries = new ChartSeries();
        countries.setLabel("Countries");
        
        for(String msg: data.keySet()) {
            countries.set(msg, data.get(msg));
        }
 
        model.addSeries(countries);
        return model;
    }
     
    private void createBarModels() {
        createCountryModel();
        createAmountSellBuyModel();
    }
     
    private void createCountryModel() {
        
        Hashtable<String, Integer> data = new Hashtable<String, Integer>();
        
        for(Message msg: topMessages) {
  
            if(!data.containsKey(msg.getOriginatingCountry())) {
                data.put(msg.getOriginatingCountry(), 1);
            }
            else {
                Integer value = data.get(msg.getOriginatingCountry());
                value+=1;
                data.put(msg.getOriginatingCountry(), value);
            }
        }
        
        countryModel = initCountryBarModel(data);
         
        countryModel.setTitle("Top 50 Originating countries");
        countryModel.setLegendPosition("ne");
        
        
         
        Axis xAxis = countryModel.getAxis(AxisType.X);
        xAxis.setLabel("Country");
         
        Axis yAxis = countryModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of transactions");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private void createAmountSellBuyModel() {
        
        amountBuySellModel = new HorizontalBarChartModel();
        
        ChartSeries sellSeries = new ChartSeries();
        sellSeries.setLabel("Amount Sell");
        
        ChartSeries buySeries = new ChartSeries();
        buySeries.setLabel("Amount Buy");
        
        Hashtable<Integer, Double> dataSell = new Hashtable<Integer, Double>();
        Hashtable<Integer, Double> dataBuy = new Hashtable<Integer, Double>();
        
        for(Message msg: topMessages) {
            
            Calendar cal = new GregorianCalendar();
            cal.setTime(msg.getTimePlaced());
            int year = cal.get(Calendar.YEAR);
            
            if(!dataSell.containsKey(year)) {
                dataSell.put(year, msg.getAmountSell());
            }
            else {
                Double value = dataSell.get(year);
                value+=msg.getAmountSell();
                dataSell.put(year,value);
            }
            //--------------- BUY ------------
            if(!dataBuy.containsKey(year)) {
                dataBuy.put(year, msg.getAmountBuy());
            }
            else {
                Double value = dataBuy.get(year);
                value+=msg.getAmountBuy();
                dataBuy.put(year,value);
            }
        }
        
        
        for(Integer year: dataSell.keySet()) {
            sellSeries.set(year, dataSell.get(year));
        }
        
        for(Integer year: dataBuy.keySet()) {
            buySeries.set(year, dataBuy.get(year));
        }

 
        amountBuySellModel.addSeries(sellSeries);
        amountBuySellModel.addSeries(buySeries);
         
        amountBuySellModel.setTitle("Buy/Sell Ratio (Per Year)");
        amountBuySellModel.setLegendPosition("e");
        amountBuySellModel.setStacked(true);
         
        Axis xAxis = amountBuySellModel.getAxis(AxisType.X);
        xAxis.setLabel("Total Buy/Sell");
        xAxis.setMin(100);
        xAxis.setMax(20000);
         
        Axis yAxis = amountBuySellModel.getAxis(AxisType.Y);
        yAxis.setLabel("Year");        
    }

    /**
     * @return the topMessages
     */
    public List <Message> getTopMessages() {
        return topMessages;
    }

    /**
     * @param topMessages the topMessages to set
     */
    public void setTopMessages(List <Message> topMessages) {
        this.topMessages = topMessages;
    }
 
    
    

}
