package com.ky.springui.controller;

import com.ky.springui.model.Subscriber;
import com.ky.springui.request.CreateSubscriberRequest;
import com.ky.springui.service.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/subscriber")
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/createSubscriber")
    public ResponseEntity<Subscriber> createNewSubscriber(@RequestBody CreateSubscriberRequest request){
        return ResponseEntity.ok(subscriberService.createNewSubscriber(request));
    }
}
