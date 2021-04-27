package com.libra.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notesId;
    private String text;
    private String title;

}
