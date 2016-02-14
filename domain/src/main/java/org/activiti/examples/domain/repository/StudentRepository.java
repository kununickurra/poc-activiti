package org.activiti.examples.domain.repository;

import org.activiti.examples.domain.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class StudentRepository extends AbstractRepositoryImpl<Student, String> {
}
