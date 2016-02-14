package org.activity.example.workfow;

import java.util.Map;

/**
 * Created by nitillie on 12-02-16.
 */
public interface ProcessService {

    int startProcess(String processName, String assignee, Map<String, Object> parameters);

}
