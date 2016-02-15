package org.activiti.examples.web.controller;


import org.activiti.bpmn.model.Task;
import org.activiti.example.bpmn.engine.ProcessTask;
import org.activiti.example.bpmn.engine.TasksService;
import org.activiti.examples.domain.repository.PhdEngagementRepository;
import org.activiti.examples.domain.repository.PromotorRepository;
import org.activiti.examples.domain.repository.StudentRepository;
import org.activiti.examples.web.controller.dto.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotor")
public class PromotorController {

    private static final Logger LOG = LoggerFactory.getLogger(PromotorController.class);

    @Resource
    private TasksService tasksService;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private PhdEngagementRepository phdEngagementRepository;

    @Resource
    private PromotorRepository promotorRepository;

    @RequestMapping(value = "/{userName}/task", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<TaskResponse> getTasksToComplete(@PathVariable String userName) {
        return tasksService.getMyPendingTasks(userName).stream()
                .map(t -> toTaskResponse(
                        t,
                        phdEngagementRepository.findByProcessId(
                                t.getTask().getProcessInstanceId()).getStudent().getUserName()))
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/{userName}/task/{taskId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void approveOrRejectTask(@PathVariable String userName, @PathVariable String taskId, @RequestParam Boolean approved) {
        tasksService.completeTask(taskId, userName, buildTaskParameters(approved));
    }

    private Map<String, Object> buildTaskParameters(Boolean approved) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("approved", approved.toString());
        return parameters;
    }

    private TaskResponse toTaskResponse(ProcessTask task, String initiator) {
        TaskResponse response = new TaskResponse();
        response.setTaskId(task.getTask().getId());
        response.setTaskName(task.getTask().getName());
        response.setDescription(task.getTask().getDescription());
        response.setRequiredInput(task.getForm().toString());
        response.setInitiator(initiator);
        return response;
    }

}
