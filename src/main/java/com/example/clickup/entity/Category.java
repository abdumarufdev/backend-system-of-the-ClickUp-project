package com.example.clickup.entity;

import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbsUUIDentity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Project project;
    private String accesType;
    private String archived;
    private String color;
}
