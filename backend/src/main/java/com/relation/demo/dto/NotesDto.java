package com.relation.demo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDto {
    private int id;
    private String title;
    private String content;
    private Date date;
    private boolean completed;
    private int userId;
}
