package org.activiti.example.bpmn.engine;

import org.activiti.bpmn.model.Task;
import org.activiti.examples.domain.Person;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksService {

    @Resource
    private TasksService taskService;

    public List<Task> getMyPendingTasks(Person person) {

        List<Task> tasks= taskService.createTaskQuery().processDefinitionKey("myProcess").taskDefinitionKey("usertask1").list();
        for(Task task:tasks){
            taskService.claim(task.getId(), "DJ");
            TaskFormData taskFormData = processEngine.getFormService().getTaskFormData(task.getId());
            Map<String, Object> variableMap = new HashMap<String, Object>();
            variableMap.put("HumanTaskCompletedBy", "DJ");
            variableMap.put("name", "super nico");
            taskService.complete(task.getId(),variableMap);
        }
    }

    public void getMyPendingTasks(Person person) {

    }


}
