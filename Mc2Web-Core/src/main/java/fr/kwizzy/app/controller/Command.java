package fr.kwizzy.app.controller;

import fr.kwizzy.app.back.ResultObj;
import fr.kwizzy.app.webserver.Message;
import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.back.HttpStatus;
import fr.kwizzy.app.back.Result;
import org.bukkit.Bukkit;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */

public class Command extends Controller
{

    public Result execute() {
        if(paramPostNullOEmpty("command"))
            return badRequest();
        String command = getPostParam("command");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        return ok(ResultObj::success);
    }

}
