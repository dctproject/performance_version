package cn.insysu.groceryproject.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Souler on 2016/12/1.
 */
@Entity
public class DealContent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "OCID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ocid;

    @Column(name = "QTY" , nullable = false)
    private int quantity;

    @ManyToOne(targetEntity = Deal.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "OID")
    private Deal order;

    @ManyToOne(targetEntity = Cuisine.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "CID")
    private Cuisine cuisine;

    public DealContent(int quantity, Deal deal, Cuisine cuisine) {
        this.quantity = quantity;
        this.order = deal;
        this.cuisine = cuisine;
    }

    public DealContent(int quantity, Cuisine cuisine) {
        this.quantity = quantity;
        this.cuisine = cuisine;
    }

    public DealContent() {
        super();
    }

    public long getOcid() {
        return ocid;
    }

    public void setOcid(long ocid) {
        this.ocid = ocid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Deal getOrder() {
        return order;
    }

    public void setOrder(Deal order) {
        this.order = order;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : ((cuisine == null) ? order.hashCode() : (order.hashCode() + cuisine.hashCode())));
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
        final DealContent other = (DealContent) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (order.equals(other.order) && (cuisine == other.cuisine))
            return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DealContent Entity [order=").append(order.toString()).append(" , cuisine=").append(cuisine.toString()).append(" , quantity=").append(quantity)
                .append(" , ocid=").append(ocid).append("]");
        return builder.toString();
    }
}
