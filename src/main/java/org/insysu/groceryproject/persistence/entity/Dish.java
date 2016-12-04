package org.insysu.groceryproject.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Souler on 2016/11/30.
 */
@Entity
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long did;

    @Column(name = "DNAME" , nullable = false)
    private String dname;

    @Column(name = "DPRICE" , nullable = false)
    private double dprice;

    @Column(name = "DDETAIL")
    private String ddetail;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "dish" , fetch = FetchType.EAGER)
    private Cuisine cuisine;


    public Dish(String dname, double dprice, String ddetail) {
        this.dname = dname;
        this.dprice = dprice;
        this.ddetail = ddetail;
    }


    public Dish(String dname, double dprice) {
        this.dname = dname;
        this.dprice = dprice;
        this.ddetail = null;
    }

    public Dish() {
        super();
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public double getDprice() {
        return dprice;
    }

    public void setDprice(double dprice) {
        this.dprice = dprice;
    }

    public String getDdetail() {
        return ddetail;
    }

    public void setDdetail(String ddetail) {
        this.ddetail = ddetail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dname == null) ? 0 : ((dprice == 0) ? dname.hashCode() : (dname + dprice).hashCode()));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Dish other = (Dish) obj;
        if (dname == null) {
            if (other.dname != null)
                return false;
        } else if (dname.equals(other.dname) && (dprice == other.dprice))
            return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Dish Entity [dishname=").append(dname).append(" , price=").append(dprice)
                .append(" , detail=").append(ddetail).append(" , did=").append(did).append("]");
        return builder.toString();
    }
}
