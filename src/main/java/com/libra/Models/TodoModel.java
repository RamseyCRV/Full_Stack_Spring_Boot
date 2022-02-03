package com.libra.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModel extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoId;
    private String text;
    private Boolean isDone;

}
