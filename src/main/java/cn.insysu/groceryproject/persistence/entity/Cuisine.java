package cn.insysu.groceryproject.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Souler on 2016/11/30.
 */
@Entity
public class Cuisine implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;

    @OneToOne(targetEntity = Dish.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "DID")
    private Dish dishDescription;


    @Column(name = "AVAIL" , nullable = false)
    private boolean isAvailable;

    @Column(name = "REMAIN")
    private int remainQuantity;

    @Column(name = "ISVIP" , nullable = false)
    private boolean isVIPOffer;

    public Cuisine(Dish dishDescription, boolean isAvailable, int remainQuantity, boolean isVIPOffer) {
        this.dishDescription = dishDescription;
        this.isAvailable = isAvailable;
        this.remainQuantity = remainQuantity;
        this.isVIPOffer = isVIPOffer;
    }

    public Cuisine(boolean isAvailable, int remainQuantity, boolean isVIPOffer) {
        this.isAvailable = isAvailable;
        this.remainQuantity = remainQuantity;
        this.isVIPOffer = isVIPOffer;
    }

    public Cuisine(boolean isVIPOffer) {
        this.isAvailable = false;
        this.remainQuantity = 0;
        this.isVIPOffer = isVIPOffer;
    }

    public Dish getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(Dish dishDescription) {
        this.dishDescription = dishDescription;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public boolean isVIPOffer() {
        return isVIPOffer;
    }

    public void setVIPOffer(boolean VIPOffer) {
        isVIPOffer = VIPOffer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dishDescription == null) ? 0 : ((remainQuantity == 0) ?
                            dishDescription.hashCode() : dishDescription.hashCode() + remainQuantity));
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
        final Cuisine other = (Cuisine) obj;
        if (dishDescription == null) {
            if (other.dishDescription != null)
                return false;
        } else if (dishDescription.equals(other.dishDescription) && (remainQuantity == other.remainQuantity))
            return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Cuisine Entity [dish=").append(dishDescription.toString()).append(" , isAvailable=").append(isAvailable)
                .append(" , remainQuantity=").append(remainQuantity).append(" , isVIPOffer=").append(isVIPOffer)
                .append(" , cid=").append(cid).append("]");
        return builder.toString();
    }
}
