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
public class Todo extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoId;
    private String text;
    private boolean isDone;

}
