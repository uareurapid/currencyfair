/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.currencyfair.message;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "message", catalog = "currencyfair", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findAllOrdered", query = "SELECT m FROM Message m ORDER BY m.timePlaced"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByUserid", query = "SELECT m FROM Message m WHERE m.userid = :userid"),
    @NamedQuery(name = "Message.findByCurrencyFrom", query = "SELECT m FROM Message m WHERE m.currencyFrom = :currencyFrom"),
    @NamedQuery(name = "Message.findByCurrencyTo", query = "SELECT m FROM Message m WHERE m.currencyTo = :currencyTo"),
    @NamedQuery(name = "Message.findByAmountSell", query = "SELECT m FROM Message m WHERE m.amountSell = :amountSell"),
    @NamedQuery(name = "Message.findByAmountBuy", query = "SELECT m FROM Message m WHERE m.amountBuy = :amountBuy"),
    @NamedQuery(name = "Message.findByRate", query = "SELECT m FROM Message m WHERE m.rate = :rate"),
    @NamedQuery(name = "Message.findByTimePlaced", query = "SELECT m FROM Message m WHERE m.timePlaced = :timePlaced"),
    @NamedQuery(name = "Message.findByTimePlacedAndOrdered", query = "SELECT m FROM Message m WHERE m.timePlaced = :timePlaced"),
    @NamedQuery(name = "Message.findByOriginatingCountryLimit", query = "SELECT m.originatingCountry FROM Message m ORDER BY m.timePlaced"),
    @NamedQuery(name = "Message.findByOriginatingCountry", query = "SELECT m FROM Message m WHERE m.originatingCountry = :originatingCountry")})

public class Message implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "originating_country", nullable = false, length = 5)
    private String originatingCountry;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "userid", nullable = false, length = 45)
    private String userid;
    @Basic(optional = false)
    @Column(name = "currency_from", nullable = false, length = 5)
    private String currencyFrom;
    @Basic(optional = false)
    @Column(name = "currency_to", nullable = false, length = 5)
    private String currencyTo;
    @Basic(optional = false)
    @Column(name = "amount_sell", nullable = false)
    private double amountSell;
    @Basic(optional = false)
    @Column(name = "amount_buy", nullable = false)
    private double amountBuy;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    @Basic(optional = false)
    @Column(name = "time_placed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePlaced;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, String userid, String currencyFrom, String currencyTo, double amountSell, double amountBuy, float rate, Date timePlaced) {
        this.id = id;
        this.userid = userid;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
        this.timePlaced = timePlaced;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(double amountSell) {
        this.amountSell = amountSell;
    }

    public double getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(double amountBuy) {
        this.amountBuy = amountBuy;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.currencyfair.message.Message[ id=" + id + " ]";
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
    
}
