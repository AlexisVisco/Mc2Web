package fr.kwizzy.app.controller;

import fr.kwizzy.app.back.HttpStatus;
import fr.kwizzy.app.back.Result;
import fr.kwizzy.app.back.ResultObj;
import fr.kwizzy.app.model.Location;
import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.model.Block;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.Collectors;

import static fr.kwizzy.app.webserver.Message.*;

/**
 * Created by Alexis on 06/02/2017.
 * French author.
 */

public class World extends Controller
{

    public Result world() {
        if(paramUrlNullOEmpty(":world"))
            return badRequest();
        else if(Bukkit.getWorld(getUrlParam(":world")) == null)
            return ok(c -> c.reject(noWorldFound), HttpStatus.BAD_REQUEST);
        return ok(c -> c.successData(new fr.kwizzy.app.model.World(Bukkit.getWorld(getUrlParam(":world")))));
    }

    public Result setBlockAt() {
        if(paramPostNullOEmpty("block"))
            return badRequest();
        else if(gson.fromJson(getPostParam("block"), Block.class) == null)
            return ok(c -> c.reject(noWorldFound), HttpStatus.BAD_REQUEST);
        Block block = gson.fromJson(getPostParam("location"), Block.class);
        if(block == null || block.getLoc().toBukkitLocation() == null || Material.getMaterial(block.getMaterial()) == null)
            return badRequest();

        synchronous(() -> {
            org.bukkit.Location location = block.getLoc().toBukkitLocation();
            location.getBlock().setType(Material.getMaterial(block.getMaterial()));
            location.getBlock().setData(block.getData());
        });

        return ok(ResultObj::success);
    }

    public Result getBlockAt() {
        try {
            if(paramPostNullOEmpty("location"))
                return badRequest();
            else if(gson.fromJson(getPostParam("location"), Location.class) == null)
                return ok(c -> c.reject(noWorldFound), HttpStatus.BAD_REQUEST);
            org.bukkit.Location location = gson.fromJson(getPostParam("location"), Location.class).toBukkitLocation();
            if(location == null)
                return badRequest();

            return synchronous(() -> ok(c -> c.successData(new Block(location.getBlock()))), this::badRequest);

        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

    public Result allWorlds() {
        JSONArray arr = new JSONArray();
        Bukkit.getWorlds()
                .stream()
                .map(fr.kwizzy.app.model.World::new)
                .forEach(e -> arr.put(new JSONObject(e.toGson())));

        return ok(c -> c.successData(arr));
    }

}
