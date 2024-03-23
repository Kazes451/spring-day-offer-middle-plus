package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * распределение тасок для максимальной нагрузки рабочих, с учетом их обязанностей
 */
@Component
public class PreferredTaskDistributor implements TaskDistributor{
    @Override
    public void distribute(List<EmployeeDTO> employees, List<TaskDTO> tasks) {
        tasks.sort(Comparator.comparingInt(TaskDTO::getPriority).thenComparingInt(TaskDTO::getLeadTime));

        for (TaskDTO task : tasks) {
            for (EmployeeDTO employeeDTO : employees) {

                int currentEmployeeLoad = employeeDTO.getTotalLeadTime();

                if (!employeeDTO.getPreferredTaskTypes().contains(task.getTaskType()) && currentEmployeeLoad >= 6 * 60) {
                    continue;
                }

                if (currentEmployeeLoad >= 7 * 60) {
                    continue;
                }
                if (currentEmployeeLoad + task.getLeadTime() > 7 * 60) {
                    continue;
                }
                employeeDTO.getTasks().add(task);
                break;
            }
        }
    }
}
