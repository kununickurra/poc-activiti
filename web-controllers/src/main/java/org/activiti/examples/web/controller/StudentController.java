package org.activiti.examples.web.controller;

import org.activiti.examples.web.controller.dto.ActivateAccountRequest;
import org.activiti.examples.web.controller.dto.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nico on 09.02.16.
 */
@RestController
@RequestMapping("/registration")
public class StudentController {

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody RegistrationRequest request) {
        LOG.info("register method called with request {}", request);
        return "OK";
    }

    @RequestMapping(value = "/{userId}/activate", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String activateAccount(@PathVariable("userId") int registrationId, @RequestBody ActivateAccountRequest request) {
        LOG.info("activateAccount method called with request {}", request);
        return "OK";
    }


}
