package com.eightyfourfiftyone.mediametrics.service;

import com.eightyfourfiftyone.mediametrics.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.json.simple.JSONObject;

//import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
//import google.gson

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value(value = "")
    private String exchange;

    @Value(value = "")
    private String routingkey;

    //Employee company
    public void send(String jsondata) throws JsonProcessingException {
        //rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Message before sending = " + jsondata);

//        Gson gson = new Gson();
//        String json = gson.toJson(jsondata);


        jsondata = jsondata.replaceAll("\n", "");

        jsondata = jsondata.replaceAll("\t", "");
//        jsondata = jsondata.replaceAll("\"", "\\\"");

//        JSONObject objjson = new JSONObject();



//        String payload = new ObjectMapper().writeValueAsString(new Payload(jsondata));

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_JSON_PAYLOAD, jsondata);
        System.out.println("Send msg = " + jsondata);

    }

}
