package com.example.phuongnguyen.sqlinjectionjavademo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    public int id;

    public String name;

    @Column(name = "jobname") //add to query exactly database
    public String jobname;

}