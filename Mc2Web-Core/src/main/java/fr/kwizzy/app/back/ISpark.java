package fr.kwizzy.app.back;

import spark.ExceptionHandler;
import spark.ModelAndView;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */
interface ISpark
{
    void exception(Class<? extends Exception> exceptionClass, ExceptionHandler handler);

    void halt();

    void halt(int status);

    void halt(String body);

    void halt(int status, String body);

    void setIpAddress(String ipAddress);

    void ipAddress(String ipAddress);

    void setPort(int port);

    void port(int port);

    int port();

    void setSecure(String keystoreFile,
                   String keystorePassword,
                   String truststoreFile,
                   String truststorePassword);

    void secure(String keystoreFile,
                String keystorePassword,
                String truststoreFile,
                String truststorePassword);

    void threadPool(int maxThreads);

    void threadPool(int maxThreads, int minThreads, int idleTimeoutMillis);

    void externalFileLocation(String externalFolder);

    void awaitInitialization();

    void stop();

    void webSocket(String path, Class<?> handler);

    void webSocket(String path, Object handler);

    void webSocketIdleTimeoutMillis(int timeoutMillis);

    void init();

    ModelAndView modelAndView(Object model, String viewName);
}
