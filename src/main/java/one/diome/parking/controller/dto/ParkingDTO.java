package one.diome.parking.controller.dto;

import java.time.LocalDateTime;

public class ParkingDTO {
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
}
