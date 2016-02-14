package org.activiti.examples.web.controller.dto;

/**
 * Created by nitillie on 12-02-16.
 */
public class TaskResponse {

    private String taskId;
    private String taskName;
    private String description;
    private String requiredInput;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredInput() {
        return requiredInput;
    }

    public void setRequiredInput(String requiredInput) {
        this.requiredInput = requiredInput;
    }
}
