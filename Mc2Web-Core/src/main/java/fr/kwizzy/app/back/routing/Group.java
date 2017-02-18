package fr.kwizzy.app.back.routing;

import fr.kwizzy.app.back.Controller;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */
@FunctionalInterface
public interface Group<T extends Controller>
{
    void addGroup(Router<T> r);


}
