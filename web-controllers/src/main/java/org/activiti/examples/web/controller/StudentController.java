package org.activiti.examples.web.controller;

import org.activiti.example.bpmn.engine.ProcessService;
import org.activiti.examples.domain.PhdEngagement;
import org.activiti.examples.domain.Promotor;
import org.activiti.examples.domain.Student;
import org.activiti.examples.domain.repository.PhdEngagementRepository;
import org.activiti.examples.domain.repository.PromotorRepository;
import org.activiti.examples.domain.repository.StudentRepository;
import org.activiti.examples.web.controller.dto.PhdEngagementRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private ProcessService processService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhdEngagementRepository phdEngagementRepository;

    @Autowired
    private PromotorRepository promotorRepository;

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<String> getAllStudents() {
        return studentRepository.findAll().stream().map(s -> s.getUserName()).collect(Collectors.toList());
    }


    @RequestMapping(value = "/{userName}/phd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public String newPhdEngagementRequest(@PathVariable String userName, @RequestBody PhdEngagementRequest request) {
        LOG.info("newPhdEngagementRequest method called with request {}", request);
        Student student = studentRepository.findById(userName);
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
        phdEngagementRepository.saveOrUpdate(engagement);

        student.setPhdEngagement(engagement);

        Map<String, Object> formData = new HashMap<>();
        formData.put("motivation", engagement.getMotivation());

        studentRepository.saveOrUpdate(student);

        String processId = processService.startProcess("phd", student, formData);
        return "Phd Process started, thanks for submission...";
    }

    @RequestMapping(value = "/{userName}/phd", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getCurrentEngagement(@RequestBody PhdEngagementRequest request) {
        LOG.info("GetCurrentEngagement method called with request {}", request);
        return "OK";
    }


}
