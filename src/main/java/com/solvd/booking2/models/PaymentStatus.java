package com.solvd.booking2.models;

public enum PaymentStatus {

    PENDING("Pending Payment", false, false),
    COMPLETED("Payment Completed", true, false),
    REJECTED("Payment Rejected", false, true);

    private final String description;
    private final boolean isFinalState;
    private final boolean hasError;

    PaymentStatus(String description, boolean isFinalState, boolean hasError) {
        this.description = description;
        this.isFinalState = isFinalState;
        this.hasError = hasError;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinalState() {
        return isFinalState;
    }

    public boolean hasError() {
        return hasError;
    }

    public boolean canRetry() {
        return this == REJECTED;
    }

    @Override
    public String toString() {
        return description + " (final=" + isFinalState + ", error=" + hasError + ")";
    }
}
