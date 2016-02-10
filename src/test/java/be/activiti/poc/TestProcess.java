package be.activiti.poc;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;

import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/persistence-context.xml", "/spring/activiti-context.xml"})
public class TestProcess {
 
	private String filename = "/home/nico/poc/activiti/workspace/test-activiti/src/main/resources/diagrams/test-activity.bpmn";
	/**
	 * Inject repository service
	 */
	@Resource
	private RepositoryService repositoryService;
	/**
	 * Inject runtime service
	 */	
	@Resource
	private RuntimeService runtimeService;

	@Resource
	private ProcessEngine processEngine;

	/**
	 * Inject task service
	 */	
	@Resource
	private TaskService taskService;
 
	@Test
	public void startProcess() throws Exception {
 
		/*
		 * Deploy the process
		 */
		repositoryService.createDeployment().enableDuplicateFiltering().addInputStream("test-activity.bpmn20.xml",
				new FileInputStream(filename)).deploy();
 
		Map<String, Object> variableMap = new HashMap();
		variableMap.put("processStartedBy", "Nico");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
		Assert.assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		
		claimAndCompleteHumanTask();
	}
 
	public void claimAndCompleteHumanTask() throws Exception {
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
}