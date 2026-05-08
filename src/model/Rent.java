package model;

import java.time.LocalDate;

public class Rent extends BaseEntity {
    private int days;
    private LocalDate dateAt;
    private Client client;
    private Car car;

    public Rent(int days, LocalDate dateAt, Client client, Car car){
        super();
        this.days = days;
        this.dateAt = dateAt;
        this.client = client;
        this.car = car;
    }


    public Rent(int id,int days, LocalDate dateAt, Client client, Car car){
        super(id);
        this.days = days;
        this.dateAt = dateAt;
        this.client = client;
        this.car = car;
    }

    public LocalDate getDateAt() {
        return dateAt;
    }

    public Client getClient() {
        return client;
    }

    public Car getCar() {
        return car;
    }

    public int getDays(){
        return days;
    }

    public void setDateAt(LocalDate dateAt) {
        this.dateAt = dateAt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDays(int days){
        this.days = days;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "days=" + days +
                ", dateAt=" + dateAt +
                ", client=" + client +
                ", car=" + car +
                '}';
    }
}
