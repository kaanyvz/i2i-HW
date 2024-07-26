package org.example;

import java.util.Date;

public class Subscriber {
    private int subscId;
    private String subscName;
    private String subscSurname;
    private String msisdn;
    private int tariffId;
    private Date startDate;

    public Subscriber(int subscId,
                      String subscName,
                      String subscSurname,
                      String msisdn,
                      int tariffId,
                      Date startDate) {
        this.subscId = subscId;
        this.subscName = subscName;
        this.subscSurname = subscSurname;
        this.msisdn = msisdn;
        this.tariffId = tariffId;
        this.startDate = startDate;
    }

    public int getSubscId() {
        return subscId;
    }

    public void setSubscId(int subscId) {
        this.subscId = subscId;
    }

    public String getSubscName() {
        return subscName;
    }

    public void setSubscName(String subscName) {
        this.subscName = subscName;
    }

    public String getSubscSurname() {
        return subscSurname;
    }

    public void setSubscSurname(String subscSurname) {
        this.subscSurname = subscSurname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscId=" + subscId +
                ", subscName='" + subscName + '\'' +
                ", subscSurname='" + subscSurname + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", tariffId=" + tariffId +
                ", startDate=" + startDate +
                '}';
    }
}

