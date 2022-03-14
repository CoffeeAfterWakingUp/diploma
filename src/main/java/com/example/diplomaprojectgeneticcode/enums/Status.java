package com.example.diplomaprojectgeneticcode.enums;

public enum Status {
    ACTIVE("ACTIVE"),
    DISABLED("DISABLED"),
    PENDING("PENDING");

    private String status;

    Status(String status) {
        this.status = status;
    }

}
