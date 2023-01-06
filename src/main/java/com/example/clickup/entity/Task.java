package com.example.clickup.entity;

import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task extends AbsUUIDentity {
    private String name;
    private String description;
    @OneToOne
    private Status status;
    @OneToOne
    private Category category;
    @OneToOne
    private Priority priority;
    private String subtask;
    @OneToOne
    Attachment attachment;
    private Date startData;
    private Time startTime;
    private Date dueData;
    private Time dueTime;
    private Timestamp estimateTime;
    private Timestamp activedTime;
}
