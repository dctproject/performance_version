package cn.insysu.groceryproject.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
@Entity
public class Deal implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int ERROR = 0;
    public static final int UNPAID = 1000;
    public static final int PAID = 1001;
    public static final int DELIVERING = 1010;
    public static final int DELIVERED = 1011;
    public static final int CLOSED = 1100;
    public static final int REFUNDING = 1101;
    public static final int ASSESSING = 1110;
    public static final int SUCCEED = 1111;

    private String getStateStatus(final int state) {
        switch (state) {
            case UNPAID:
                return "UNPAID";
            case PAID:
                return "PAID";
            case DELIVERING:
                return "DELIVERING";
            case DELIVERED:
                return "DELIVERED";
            case CLOSED:
                return "CLOSED";
            case REFUNDING:
                return "REFUNDING";
            case ASSESSING:
                return "ASSESSING";
            case SUCCEED:
                return "SUCCEED";
            default:
                return "ERROR";
        }
    }
//    private enum States {
//        UNPAID , PAID , DELIVERING , DELIVERED , CLOSED , REFUNDING , ASSESSING , SUCCEED
//    }
//    public enum States {
//        UNPAID , PAID , DELIVERING , DELIVERED , CLOSED , REFUNDING , ASSESSING , SUCCEED
//    }

    @Id
    @Column(name = "OID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long oid;

    @Column(name = "TIME")
    private long timestamp;

    @Column(name = "STATE")
    private int state;

    @ManyToOne(targetEntity = User.class , fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "order" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<DealContent> ordercontents;

    public List<DealContent> getOrdercontents() {
        return ordercontents;
    }

    public void setOrdercontents(List<DealContent> ordercontents) {
        this.ordercontents = ordercontents;
    }



    public Deal(long timestamp, int state, User user) {
        this.timestamp = timestamp;
        this.state = state;
        this.user = user;
    }

    public Deal(long timestamp, User user) {
        this.timestamp = timestamp;
        this.user = user;
        this.state = UNPAID;
    }

    public Deal(long timestamp) {
        this.timestamp = timestamp;
        this.user = null;
        this.state = CLOSED;
    }

    public Deal() {
        super();
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : ((timestamp == 0) ? user.hashCode() : (user.hashCode() + Long.toString(timestamp)).hashCode()));
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
        final Deal other = (Deal) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (user.equals(other.user) && (timestamp == other.timestamp))
            return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Deal Entity [user=").append(user.toString()).append(" , timestamp=").append(timestamp).append(" , state=").append(getStateStatus(state))
                .append(" , oid=").append(oid).append("]");
        return builder.toString();
    }
}
