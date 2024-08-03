package com.ky.swagger.controller;

import com.ky.swagger.service.SwaggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    private final SwaggerService swaggerService;

    public SwaggerController(SwaggerService swaggerService) {
        this.swaggerService = swaggerService;
    }

    @PostMapping("/send-voice")
    public ResponseEntity<String> sendVoice(@RequestParam("aNumber") String aNumber,
                                            @RequestParam("bNumber") String bNumber,
                                            @RequestParam("duration") int duration,
                                            @RequestParam("location") String location) {
        return ResponseEntity.ok(swaggerService.sendVoice(aNumber, bNumber, duration, location));
    }

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestParam("aNumber") String aNumber,
                                          @RequestParam("bNumber") String bNumber,
                                          @RequestParam("location") String location) {
        return ResponseEntity.ok(swaggerService.sendSms(aNumber, bNumber, location));
    }

    @PostMapping("/send-data")
    public ResponseEntity<String> sendData(@RequestParam("aNumber") String aNumber,
                                           @RequestParam("mb") int mb,
                                           @RequestParam("rg") String rg,
                                           @RequestParam("location") String location) {
        return ResponseEntity.ok(swaggerService.sendData(aNumber, mb, rg, location));
    }
}