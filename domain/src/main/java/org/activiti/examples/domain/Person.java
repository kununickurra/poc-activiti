package org.activiti.examples.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public abstract class Person implements Serializable {

    @Id
    @Column
    private String userName;

    @Column
    private String name;

    @Column
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
