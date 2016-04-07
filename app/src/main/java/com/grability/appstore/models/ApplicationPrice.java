package com.grability.appstore.models;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationPrice {
    private final float amount;
    private final String currency;

    public ApplicationPrice(float amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
