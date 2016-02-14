package org.activiti.examples.web.controller;


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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotor")

public class PromotorController {

    @Autowired
    private TasksService tasksService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhdEngagementRepository phdEngagementRepository;

    @Autowired
    private PromotorRepository promotorRepository;

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<TaskResponse> getAllStudents(@PathVariable String userName) {
        return tasksService.getMyPendingTasks(userName).stream().map(t -> toTaskResponse(t)).collect(Collectors.toList());
    }


    private TaskResponse toTaskResponse(ProcessTask task) {
        TaskResponse response = new TaskResponse();
        response.setTaskId(task.getTask().getId());
        response.setTaskName(task.getTask().getName());
        response.setDescription(task.getTask().getDescription());
        response.setRequiredInput(task.getForm().toString());
        return response;
    }

}
