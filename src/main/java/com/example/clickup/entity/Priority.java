package com.example.clickup.entity;

import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Priority extends AbsUUIDentity {
    private String name;
    @OneToOne
    Icon icon;
}
