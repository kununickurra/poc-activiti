package org.activiti.examples.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    @Id
    @Column
    private String userName;

    @Column
    private String email;

    @ManyToOne
    private Promotor promotor;

    @OneToOne
    private PhdEngagement phdEngagement;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhdEngagement getPhdEngagement() {
        return phdEngagement;
    }

    public void setPhdEngagement(PhdEngagement phdEngagement) {
        this.phdEngagement = phdEngagement;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }
}
