package org.activiti.example.bpmn.engine;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TasksService {

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private TaskService taskService;

    public List<ProcessTask> getMyPendingTasks(String userName) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userName).list();
        return tasks.stream().map(t -> buildProcessTask(t)).collect(Collectors.toList());
    }

    public List<ProcessTask> getGroupPendingTasks(String groupName) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(groupName).list();
        return tasks.stream().map(t -> buildProcessTask(t)).collect(Collectors.toList());
    }

    public void claimTask(String taskId, String userName) {
        taskService.claim(taskId, userName);
    }

    public void completeTask(String taskId, String userName, Map<String, Object> formData) {
        taskService.complete(taskId, formData);
    }

    private ProcessTask buildProcessTask(Task task) {
        TaskFormData taskFormData = processEngine.getFormService().getTaskFormData(task.getId());
        return new ProcessTask(task, taskFormData);
    }


}
