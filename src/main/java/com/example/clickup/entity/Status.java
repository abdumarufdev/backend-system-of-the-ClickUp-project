package com.example.clickup.entity;

import com.example.clickup.entity.enums.StatusType;
import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Status extends AbsUUIDentity {
    private String name;
    @OneToOne
    private Space space;
    @OneToOne
    private Project project;
    @OneToOne
    private Category category;
    private String color;
    @Enumerated(value = EnumType.STRING)
    private StatusType statusType;
}
