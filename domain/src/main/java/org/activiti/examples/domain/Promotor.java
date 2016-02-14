package org.activiti.examples.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Promotor implements Serializable {

    @Id
    @Column
    private String userName;

    @OneToMany(mappedBy = "promotor")
    private List<Student> students;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
