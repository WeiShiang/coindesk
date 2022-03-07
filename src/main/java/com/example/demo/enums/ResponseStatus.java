package com.example.demo.enums;

import org.springframework.lang.Nullable;

public enum ResponseStatus {
    SUCCESSFUL(0, "executed successfully"),
    FAIL(1, "operation not work"),
    EMPTY_RESULTS(2, "query executed successfully but did not return records")
    ;

    private static final ResponseStatus[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;
    private final String reasonPhrase;

    ResponseStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static ResponseStatus valueOf(int statusCode) {
        ResponseStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    @Nullable
    public static ResponseStatus resolve(int statusCode) {
        // Use cached VALUES instead of values() to prevent array allocation.
        for (ResponseStatus status : VALUES) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

}
