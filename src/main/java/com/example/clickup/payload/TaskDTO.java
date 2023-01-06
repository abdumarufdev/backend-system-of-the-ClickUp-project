package com.example.clickup.payload;

import com.example.clickup.entity.Attachment;
import com.example.clickup.entity.Category;
import com.example.clickup.entity.Priority;
import com.example.clickup.entity.Status;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
public class TaskDTO {
    private String name;
    private String description;
    private UUID statusid;
    private UUID categoryid;
    private UUID priority;
    private String subtask;
    private MultipartFile avatar;
    private Date startData;
    private Time startTime;
    private Date dueData;
    private Time dueTime;
}
