package org.activiti.example.bpmn.engine;

import org.activiti.engine.form.FormData;
import org.activiti.engine.task.Task;

/**
 * Created by nitillie on 12-02-16.
 */
public class ProcessTask {

    private Task task;
    private FormData form;

    public ProcessTask(Task task, FormData form) {
        this.task = task;
        this.form = form;
    }

    public Task getTask() {
        return task;
    }

    public FormData getForm() {
        return form;
    }

}
