package com.ky.swagger.service;

import org.springframework.stereotype.Service;

@Service
public class SwaggerService {

    public String sendVoice(String aNumber, String bNumber, int duration, String location) {
        return String.format("A call was made from %s to %s for %d minutes in %s.", aNumber, bNumber, duration, location);
    }

    public String sendSms(String aNumber, String bNumber, String location) {
        return String.format("An SMS was sent from %s to %s in %s.", aNumber, bNumber, location);
    }

    public String sendData(String aNumber, int mb, String rg, String location) {
        return String.format("Data of %d MB with rating group %s was sent from %s in %s.", mb, rg, aNumber, location);
    }
}