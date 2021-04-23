package com.libra.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    private int todoId;
    private String text;
    private Date creationTime;

    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="id")
    private User user;
}
