package com.web123.Movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web123.EModel.MID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderToComment(MID id) {

        this.rabbitTemplate.convertAndSend("DeleteCommentQueue",id);
    }
    public void sendOrderToRating(MID id) {

        this.rabbitTemplate.convertAndSend("DeleteRateQueue",id);
    }
}