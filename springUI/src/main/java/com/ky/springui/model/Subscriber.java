package com.ky.springui.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "SUBSCRIBERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
    public class Subscriber {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_seq")
        @SequenceGenerator(name = "subscriber_seq", sequenceName = "SUBSCRIBER_SEQ", allocationSize = 1)
        private Integer id;

        @Column(name = "subscriber_name")
        private String subscriberName;

        @Column(name = "subscriber_surname")
        private String subscriberSurname;

        @Column(name = "subscriber_msisdn")
        private String subscriberMsisdn;

        @Column(name = "subscriber_tariff_id")
        private String subscriberTariffId;

        @Column(name = "start_date")
        private Date startDate;

    }
