package fr.kwizzy.app.back.routing;

import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.back.WebServer;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */

public class RouteGroupe<T extends Controller> implements Router<T>
{

    Class<T> tClass;

    RouteGroupe(Class<T> tClass)
    {
        this.tClass = tClass;
    }

    @Override
    public Route get(String path, Routing<T> route) {
        return WebServer.get(tClass, path, route);
    }

    @Override
    public Route delete(String path, Routing<T> route) {
        return WebServer.delete(tClass, path, route);
    }

    @Override
    public Route post(String path, Routing<T> route) {
        return WebServer.post(tClass, path, route);
    }

    @Override
    public Route trace(String path, Routing<T> route) {
        return WebServer.trace(tClass, path, route);
    }

    @Override
    public Route patch(String path, Routing<T> route) {
        return WebServer.patch(tClass, path, route);
    }

    @Override
    public Route options(String path, Routing<T> route) {
        return WebServer.options(tClass, path, route);
    }

    @Override
    public Route put(String path, Routing<T> route) {
        return WebServer.put(tClass, path, route);
    }
}
