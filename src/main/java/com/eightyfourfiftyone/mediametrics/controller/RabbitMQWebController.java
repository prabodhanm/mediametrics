package com.eightyfourfiftyone.mediametrics.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eightyfourfiftyone.mediametrics.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/javainuse-rabbitmq/")
public class RabbitMQWebController {
    @Autowired
    RabbitMQSender rabbitMQSender;

    @RequestMapping(value = "/producer", method= RequestMethod.POST)
    //, @RequestParam("empId") String empId
    public String producer(@RequestBody String jsondata) throws JsonProcessingException {

        /*Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);*/
        rabbitMQSender.send(jsondata);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}
