package com.example.clickup.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDTO {
    private String name;
    private UUID projectid;
    private String accesType;
    private String archived;
    private String color;
}
