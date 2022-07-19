package com.yashtech.rrs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {
	//All fields in reservation table 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservationId;
    private String fromLocation;
    private String toLocation;
    private int numberOfTickets;
    private double totalBill;

    public Reservation() {
    }

    public Reservation(int reservationId, String from, String toLocation, int numberOfTickets, double totalBill) {
        this.reservationId = reservationId;
        this.fromLocation = from;
        this.toLocation = toLocation;
        this.numberOfTickets = numberOfTickets;
        this.totalBill = totalBill;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String from) {
        this.fromLocation = from;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String to) {
        this.toLocation = to;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", from='" + fromLocation + '\'' +
                ", to='" + toLocation + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                ", totalBill=" + totalBill +
                '}';
    }
}
