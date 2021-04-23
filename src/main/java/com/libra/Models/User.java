package com.libra.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;

    @OneToMany(targetEntity=Todo.class)
    @JoinColumn(name="todoId")
    private List<Todo> todosList;

    @OneToMany(targetEntity=Notes.class)
    @JoinColumn(name="notesId")
    private List<Notes> notesList;
}
