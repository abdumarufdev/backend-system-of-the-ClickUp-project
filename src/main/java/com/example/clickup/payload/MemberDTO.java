package com.example.clickup.payload;

import com.example.clickup.entity.enums.AddType;
import lombok.Data;

import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;
}
