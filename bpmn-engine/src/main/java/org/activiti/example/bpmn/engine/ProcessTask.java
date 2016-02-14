package org.activiti.example.bpmn.engine;

import org.activiti.engine.form.FormData;
import org.activiti.engine.task.Task;

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
