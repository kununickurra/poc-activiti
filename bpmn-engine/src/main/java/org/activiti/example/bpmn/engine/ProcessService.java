package org.activiti.example.bpmn.engine;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.examples.domain.Person;
import org.activiti.examples.domain.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitillie on 12-02-16.
 */
@Service
public class ProcessService {

    @Resource
    private RuntimeService runtimeService;

    public String startProcess(String processName, Student initiator, Map<String, Object> parameters) {
        parameters.put("student", initiator);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, parameters);
        return processInstance.getId();
    }


}
