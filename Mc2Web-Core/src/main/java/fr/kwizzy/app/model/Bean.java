package fr.kwizzy.app.model;

import com.google.gson.Gson;

/**
 * Created by Alexis on 30/01/2017.
 * French author.
 */
public interface Bean
{
    Gson g = new Gson();

    default String toGson() {
        return g.toJson(this);
    }
}
