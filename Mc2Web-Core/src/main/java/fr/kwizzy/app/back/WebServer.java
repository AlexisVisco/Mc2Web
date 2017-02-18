package fr.kwizzy.app.back;

import fr.kwizzy.app.back.routing.Group;
import fr.kwizzy.app.back.routing.Route;
import fr.kwizzy.app.back.routing.Router;
import fr.kwizzy.app.back.routing.Routing;
import spark.ExceptionHandler;
import spark.Filter;
import spark.ModelAndView;
import spark.Spark;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */

public class WebServer implements ISpark
{


    public static <T extends Controller> Route group(Class<T> classz, Group<T> r) {
        Router<T> router = Router.createRouter(classz);
        r.addGroup(router);
        return new Route() {};
    }

    public static  <T extends Controller> Route get(Class<T> classz, String path, Routing<T> r) {
        Spark.get(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route post(Class<T> classz, String path, Routing<T> r) {
        Spark.post(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route put(Class<T> classz, String path, Routing<T> r) {
        Spark.put(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route options(Class<T> classz, String path, Routing<T> r) {
        Spark.options(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route patch(Class<T> classz, String path, Routing<T> r) {
        Spark.patch(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route delete(Class<T> classz, String path, Routing<T> r) {
        Spark.delete(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static <T extends Controller> Route trace(Class<T> classz, String path, Routing<T> r) {
        Spark.delete(path, (request, response) -> {
            T t = classz.newInstance();
            t.inject(request, response);
            return r.route(t).getResult();
        });
        return new Route() {};
    }

    public static Route before(String path, Filter... filters) {
        for (Filter filter : filters) {
            Spark.before(path, filter);
        }
        return new Route() {};
    }

    @Override
    public void exception(Class<? extends Exception> exceptionClass, ExceptionHandler handler)
    {
        Spark.exception(exceptionClass, handler);
    }

    @Override
    public void halt()
    {
        Spark.halt();
    }

    @Override
    public void halt(int status)
    {
        Spark.halt(status);
    }

    @Override
    public void halt(String body)
    {
        Spark.halt(body);
    }

    @Override
    public void halt(int status, String body)
    {
        Spark.halt(status, body);
    }

    @Override
    public void setIpAddress(String ipAddress)
    {
        Spark.setIpAddress(ipAddress);
    }

    @Override
    public void ipAddress(String ipAddress)
    {
        Spark.ipAddress(ipAddress);
    }

    @Override
    public void setPort(int port)
    {
        Spark.setPort(port);
    }

    @Override
    public void port(int port)
    {
        Spark.port(port);
    }

    @Override
    public int port()
    {
        return Spark.port();
    }

    @Override
    public void setSecure(String keystoreFile, String keystorePassword, String truststoreFile, String truststorePassword)
    {
        Spark.setSecure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
    }

    @Override
    public void secure(String keystoreFile, String keystorePassword, String truststoreFile, String truststorePassword)
    {
        Spark.secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
    }

    @Override
    public void threadPool(int maxThreads)
    {
        Spark.threadPool(maxThreads);
    }

    @Override
    public void threadPool(int maxThreads, int minThreads, int idleTimeoutMillis)
    {
        Spark.threadPool(maxThreads, minThreads, idleTimeoutMillis);
    }


    @Override
    public void externalFileLocation(String externalFolder)
    {
        Spark.externalStaticFileLocation(externalFolder);
    }

    @Override
    public void awaitInitialization()
    {
        Spark.awaitInitialization();
    }

    @Override
    public void stop()
    {
        Spark.stop();
    }

    @Override
    public void webSocket(String path, Class<?> handler)
    {
        Spark.webSocket(path, handler);
    }

    @Override
    public void webSocket(String path, Object handler)
    {
        Spark.webSocket(path, handler);
    }

    @Override
    public void webSocketIdleTimeoutMillis(int timeoutMillis)
    {
        Spark.webSocketIdleTimeoutMillis(timeoutMillis);
    }

    @Override
    public void init()
    {
        Spark.init();
    }

    @Override
    public ModelAndView modelAndView(Object model, String viewName)
    {
        return Spark.modelAndView(model, viewName);
    }
}
