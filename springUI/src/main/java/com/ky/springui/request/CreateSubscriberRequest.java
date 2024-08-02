package com.ky.springui.request;


public record CreateSubscriberRequest(
        String subscriberName,
        String subscriberSurname,
        String subscriberMsisdn,
        String subscriberTariffId
        ){
}
