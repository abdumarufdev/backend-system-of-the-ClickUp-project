package com.example.clickup.entity;

import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends AbsUUIDentity {

    private String originalName;

    private Long size;

    private String contentType;

    private String path;

}
