package org.activiti.examples.web.controller;

import org.activiti.example.bpmn.engine.ProcessService;
import org.activiti.examples.domain.PhdEngagement;
import org.activiti.examples.domain.Promotor;
import org.activiti.examples.domain.Student;
import org.activiti.examples.domain.repository.PhdEngagementRepository;
import org.activiti.examples.domain.repository.PromotorRepository;
import org.activiti.examples.domain.repository.StudentRepository;
import org.activiti.examples.web.controller.dto.PhdEngagementRequest;
import org.activiti.examples.web.controller.dto.PhdEngagementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.activiti.examples.domain.EngagementStatus.REQUESTED;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private ProcessService processService;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private PhdEngagementRepository phdEngagementRepository;

    @Resource
    private PromotorRepository promotorRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<String> getAllStudents() {
        return studentRepository.findAll().stream().map(s -> s.getUserName()).collect(Collectors.toList());
    }


    @RequestMapping(value = "/{userName}/phd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Transactional
    public PhdEngagementResponse newPhdEngagementRequest(@PathVariable String userName, @RequestBody PhdEngagementRequest request) {
        LOG.info("newPhdEngagementRequest method called with request {}", request);
        Student student = studentRepository.findById(userName);

        // For testing purpose only, create the student & linked promotor if it does not exists yet.
        if (student == null) {
            student = new Student();
            student.setUserName(userName);
            student.setEmail("myfakemail@hotmail.com");
            Promotor promotor = new Promotor();
            promotor.setUserName("promotor-" + userName);
            promotorRepository.saveOrUpdate(promotor);
            student.setPromotor(promotor);
            studentRepository.saveOrUpdate(student);
        }

        PhdEngagement engagement = new PhdEngagement();
        engagement.setStudent(student);
        engagement.setMotivation(request.getMotivation());
        engagement.setEngagementStatus(REQUESTED);
        phdEngagementRepository.saveOrUpdate(engagement);

        student.setPhdEngagement(engagement);
        studentRepository.saveOrUpdate(student);

        // Set process variables
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("motivation", engagement.getMotivation());

        String processId = processService.startProcess("phd", student.getUserName(), inputData);

        // Save the process ID into the engagement.
        engagement.setProcessId(processId);
        phdEngagementRepository.saveOrUpdate(engagement);

        PhdEngagementResponse response = new PhdEngagementResponse();
        response.setMessage("Phd Process started, thanks for submission...");
        response.setProcessId(processId);
        return response;
    }

    @RequestMapping(value = "/{userName}/phd", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getCurrentEngagement(@RequestBody PhdEngagementRequest request) {
        LOG.info("GetCurrentEngagement method called with request {}", request);
        return "OK";
    }


}
