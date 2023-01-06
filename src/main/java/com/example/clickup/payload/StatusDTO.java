package com.example.clickup.payload;

import com.example.clickup.entity.Category;
import com.example.clickup.entity.Project;
import com.example.clickup.entity.Space;
import com.example.clickup.entity.enums.StatusType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
public class StatusDTO {
    private String name;
    private UUID spaceid;
    private UUID projectid;
    private UUID categoryid;
    private String color;
    private StatusType statusType;
}
