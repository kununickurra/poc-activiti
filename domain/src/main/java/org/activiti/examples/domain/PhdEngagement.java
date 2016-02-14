package org.activiti.examples.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PhdEngagement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String motivation;

    @OneToOne
    private Student student;


    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
