package fr.kwizzy.app.controller;

import com.google.gson.Gson;
import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.back.Result;
import fr.kwizzy.app.back.ResultObj;
import fr.kwizzy.app.model.Module;
import fr.kwizzy.app.model.Plugin;
import fr.kwizzy.app.model.ServerInfo;
import org.bukkit.Bukkit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */

public class Server extends Controller
{

    public Result server()
    {
        return ok(c -> c.successData(new ServerInfo()));
    }

    public Result broadcast()
    {
        if (paramPostNullOEmpty("message"))
            return badRequest();
        Bukkit.broadcastMessage(getPostParam("message"));
        return ok(ResultObj::success);
    }

    public Result plugins()
    {
        JSONArray ja = new JSONArray();
        Arrays.stream(Bukkit.getPluginManager().getPlugins()).forEach(e -> ja.put(new JSONObject(new Plugin(e).toGson())));
        return ok(c -> c.successData(ja));
    }

    public Result pluginsLite()
    {
        JSONArray ja = new JSONArray();
        Arrays.stream(Bukkit.getPluginManager().getPlugins()).forEach(e -> ja.put(e.getName()));
        return ok(c -> c.successData(ja));
    }

    public Result stop()
    {
        synchronous(() -> Bukkit.getServer().shutdown());
        return ok(ResultObj::success);
    }

    public Result modules()
    {
        JSONArray jsonObject = new JSONArray(new Gson().toJson(Module.getModules()));
        return ok(c -> c.successData(jsonObject));
    }
}
