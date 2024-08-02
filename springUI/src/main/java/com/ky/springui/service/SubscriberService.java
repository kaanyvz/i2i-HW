package com.ky.springui.service;

import com.ky.springui.model.Subscriber;
import com.ky.springui.repository.SubscriberRepository;
import com.ky.springui.request.CreateSubscriberRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private static final AtomicInteger idGenerator = new AtomicInteger(0);


    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public Subscriber createNewSubscriber(CreateSubscriberRequest createSubscriberRequest){
        Subscriber subscriber = Subscriber.builder()
                .id(generateUniqueId())
                .subscriberName(createSubscriberRequest.subscriberName())
                .subscriberSurname(createSubscriberRequest.subscriberSurname())
                .subscriberMsisdn(createSubscriberRequest.subscriberMsisdn())
                .subscriberTariffId(createSubscriberRequest.subscriberTariffId())
                .startDate(new java.sql.Date(new java.util.Date().getTime()))
                .build();

        return subscriberRepository.save(subscriber);
    }



    private Integer generateUniqueId() {
        return idGenerator.incrementAndGet();
    }
}
