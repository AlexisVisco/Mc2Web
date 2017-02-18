package fr.kwizzy.app.back.routing;

import fr.kwizzy.app.back.Controller;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */
public interface Router<T extends Controller>
{
    Route get(String path, Routing<T> route);

    Route delete(String path, Routing<T> route);

    Route post(String path, Routing<T> route);

    Route trace(String path, Routing<T> route);

    Route patch(String path, Routing<T> route);

    Route options(String path, Routing<T> route);

    Route put(String path, Routing<T> route);

    static <X extends Controller> Router createRouter(Class<X> cl) {
        return new RouteGroupe(cl);
    }
}
