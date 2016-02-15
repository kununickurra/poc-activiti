package org.activiti.examples.domain.repository;

import org.activiti.examples.domain.PhdEngagement;
import org.activiti.examples.domain.Student;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class PhdEngagementRepository extends AbstractRepositoryImpl<PhdEngagement, String> {

    public PhdEngagement findByProcessId(String processId) {
        Query query = getCurrentSession().createQuery("FROM PhdEngagement WHERE processId = :processId");
        query.setParameter("processId", processId);
        return (PhdEngagement) query.uniqueResult();
    }
}
