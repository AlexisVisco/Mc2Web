package fr.kwizzy.app.back.routing;

import fr.kwizzy.app.back.Result;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */
@FunctionalInterface
public interface Routing<T>
{
    Result route(T t);
}
