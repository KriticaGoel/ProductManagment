package com.kritica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
    private int id;
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;
    @Column(name = "password", unique = true, nullable = false, length = 100)
    private String password;


    //to make bidirection -> we want to access profile class data usimg user class
    //and we dont want any foreign key of profile in user
    //use - Mapped by and use variable name of profile
    @OneToOne(mappedBy = "user")
    private Profiles profile;

    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

}
