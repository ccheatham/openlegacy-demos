package com.oracle_customer_sdk.openlegacy.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CUSTOMER_ID_GENERATOR", sequenceName="CUSTOMER_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GENERATOR")
    private long id;
    
    @Column(name="bankcode")
    private BigDecimal bankcode;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    public Customer() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBankcode() {
        return this.bankcode;
    }

    public void setBankcode(BigDecimal bankcode) {
        this.bankcode = bankcode;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}