package org.activiti.example.bpmn.engine;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.examples.domain.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ProcessService {

    @Resource
    private RuntimeService runtimeService;

    public String startProcess(String processName, String initiator, Map<String, Object> parameters) {
        parameters.put("studentId", initiator);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, parameters);
        return processInstance.getId();
    }
}
