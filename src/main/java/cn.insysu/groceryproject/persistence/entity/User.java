package cn.insysu.groceryproject.persistence.entity;

/**
 * Created by Souler on 2016/11/30.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;

    @Column(name = "UNAME" , nullable = false)
    private String username;

    @Column(name = "UPASS" , nullable = false)
    private String password;

    @Column(name = "ISVIP" , nullable = false)
    private boolean isVIP;


    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Deal> deals;

    public User() {
        super();
        deals = null;
    }

    public User(final String name , final String pass , final boolean vipStatus) {
        super();

        this.username = name;
        this.password = pass;
        this.isVIP = vipStatus;
    }

    public User(final String name , final String pass) {
        super();
        this.username = name;
        this.password = pass;
        this.isVIP = false;
    }


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : ((password == null) ? username.hashCode() : (username + password).hashCode()));
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
        final User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (username.equals(other.username) && (password.equals(other.password)))
            return true;
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User Entity [username=").append(username).append(" , isVIP=").append(isVIP).append(" , uid=").append(uid).append("]");
        return builder.toString();
    }

}
