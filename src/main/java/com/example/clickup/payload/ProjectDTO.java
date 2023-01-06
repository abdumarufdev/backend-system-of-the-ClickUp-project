package com.example.clickup.payload;

import lombok.Data;
import java.util.UUID;

@Data
public class ProjectDTO {
    private String name;
    private UUID spaceid;
    private String accesType;
    private String archived;
    private String color;
}
