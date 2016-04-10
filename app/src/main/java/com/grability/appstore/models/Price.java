package com.grability.appstore.models;

import io.realm.RealmObject;

/**
 * Created by wilson on 7/04/16.
 */
public class Price {
    private final float amount;
    private final String currency;

    public Price(float amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
