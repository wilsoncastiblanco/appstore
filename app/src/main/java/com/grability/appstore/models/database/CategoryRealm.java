package com.grability.appstore.models.database;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wilson on 7/04/16.
 */
public class CategoryRealm extends RealmObject {
    @PrimaryKey
    private String id;
    private String term;
    private String scheme;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
