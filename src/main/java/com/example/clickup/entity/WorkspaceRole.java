package com.example.clickup.entity;

import com.example.clickup.entity.enums.WorkspaceRoleName;
import com.example.clickup.entity.templates.AbsUUIDentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceRole extends AbsUUIDentity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;


    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendsRole;
}
