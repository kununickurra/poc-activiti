package org.activiti.examples.domain.service;

import org.activiti.examples.domain.EngagementStatus;
import org.activiti.examples.domain.Student;
import org.activiti.examples.domain.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PhdRequestApprovedDelegate {

    @Resource
    private StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(PhdRequestApprovedDelegate.class);

    public void registerApproval(String studentUserName) {
        // Get the student and change engagement approval status to accepted.
        Student student = studentRepository.findById(studentUserName);
        LOG.info("Phd engagement of '{}' has been approved !!", student);
        student.getPhdEngagement().setEngagementStatus(EngagementStatus.ACCEPTED);
        studentRepository.saveOrUpdate(student);
    }
}
