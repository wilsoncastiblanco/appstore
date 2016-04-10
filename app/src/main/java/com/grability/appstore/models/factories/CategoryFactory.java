package com.grability.appstore.models.factories;

import com.grability.appstore.models.Category;
import com.grability.appstore.models.database.RealmCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilson on 10/04/16.
 */
public class CategoryFactory {

    public static List<Category> getObjectListByRealmList(List<RealmCategory> realmCategories){
        List<Category> categories = new ArrayList<>(realmCategories.size());
        for(RealmCategory realmCategory : realmCategories){
            Category category = getObjectByRealmObject(realmCategory);
            categories.add(category);
        }
        return categories;
    }

    public static Category getObjectByRealmObject(RealmCategory realmCategory){
        return new Category(realmCategory.getId(), realmCategory.getTerm(), realmCategory.getScheme(), realmCategory.getLabel());
    }
}
