package com.example.demo.enums;

public enum Currency {
    USD("美元"),
    GBP("英鎊"),
    EUR("歐元");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
