package com.yashtech.rrs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //All fields in train table 
    private int trainId;
    private String trainName;
    private int seatsAvailable;
    private String source;
    private String destination;

    public Train() {
    }
    public Train(int trainId, String trainName, int seatsAvailable, String source, String destination) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.seatsAvailable = seatsAvailable;
        this.source = source;
        this.destination = destination;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", seatsAvailable=" + seatsAvailable +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
