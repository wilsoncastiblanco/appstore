package com.grability.appstore.models.database;

import io.realm.RealmObject;

/**
 * Created by wilson on 7/04/16.
 */
public class RealmPrice extends RealmObject {
    private float amount;
    private String currency;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
