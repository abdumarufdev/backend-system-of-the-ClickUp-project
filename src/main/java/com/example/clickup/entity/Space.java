package com.example.clickup.entity;

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
public class Space extends AbsUUIDentity {
    private String name;
    private String color;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Workspace workspace;
    private String initialLetter;
    @OneToOne(fetch = FetchType.LAZY)
    private Icon icon;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Users owner;
    private String accessType;
    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

    public Space(String name, String color, Workspace workspace, Icon icon, Attachment avatar, Users owner, String accessType) {
        this.name = name;
        this.color = color;
        this.workspace = workspace;
        this.icon = icon;
        this.avatar = avatar;
        this.owner = owner;
        this.accessType = accessType;
    }
}
