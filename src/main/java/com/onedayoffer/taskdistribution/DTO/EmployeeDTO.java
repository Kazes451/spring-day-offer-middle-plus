package com.onedayoffer.taskdistribution.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private String fio;
    private String jobTitle;
    private Set<TaskType> preferredTaskTypes = new HashSet<>();
    private List<TaskDTO> tasks = new ArrayList<>();

    public EmployeeDTO(String fio, String jobTitle, Set<TaskType> preferredTaskTypes) {
        this.fio = fio;
        this.jobTitle = jobTitle;
        this.tasks = new ArrayList<>();
        this.preferredTaskTypes = preferredTaskTypes;
    }

    public Integer getTotalLeadTime() {
        if (tasks.isEmpty()) return 0;
        else return tasks.stream().mapToInt(TaskDTO::getLeadTime).sum();
    }
}
