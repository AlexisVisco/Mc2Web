package fr.kwizzy.app.controller;

import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.back.HttpStatus;
import fr.kwizzy.app.back.Result;
import fr.kwizzy.app.back.ResultObj;
import fr.kwizzy.app.model.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.UUID;

import static fr.kwizzy.app.webserver.Message.*;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */

public class Player extends Controller
{

    public Result clear()
    {

        if (playerCheck() != null)
            return playerCheck();
        String uuid = getPostParam("uuid");
        synchronous(() -> Bukkit.getPlayer(UUID.fromString(uuid)).getInventory().clear());
        return ok(ResultObj::success);
    }

    public Result bannedPlayers()
    {
        return ok(c -> c.successData(new BannedPlayers()));
    }

    public Result teleport()
    {

        if (paramPostNullOEmpty("location") || paramPostNullOEmpty("uuid"))
            return badRequest();
        if (playerCheck() != null)
            return playerCheck();
        else if (gson.fromJson(getPostParam("location"), Location.class) == null)
            return ok(c -> c.reject(noWorldFound), HttpStatus.BAD_REQUEST);
        org.bukkit.Location location = gson.fromJson(getPostParam("location"), Location.class).toBukkitLocation();
        if (location == null)
            return badRequest();
        String uuid = getPostParam("uuid");
        synchronous(() -> Bukkit.getPlayer(UUID.fromString(uuid)).teleport(location));
        return ok(ResultObj::success);

    }

    public Result oppedPlayers()
    {
        return ok(c -> c.successData(new OppedPlayers()));
    }

    public Result whitelistedPlayers()
    {
        return ok(c -> c.successData(new WhitelistedPlayers()));
    }

    public Result customInfo()
    {

        if (playerCheck() != null)
            return playerCheck();
        if (paramUrlNullOEmpty(":info"))
            return badRequest();
        String param = getUrlParam(":info");
        ComplexPlayer complexPlayer = new ComplexPlayer(Bukkit.getPlayer(UUID.fromString(getPostParam("uuid"))));
        JSONObject jo = new JSONObject(complexPlayer.toGson());
        if (!jo.has(param))
            return badRequest();
        return ok(c -> c.put("data", jo.get(getUrlParam(":info"))).success());
    }

    public Result info()
    {
        if (playerCheck() != null)
            return playerCheck();
        ComplexPlayer complexPlayer = new ComplexPlayer(Bukkit.getPlayer(UUID.fromString(getPostParam("uuid"))));
        return ok(c -> c.successData(complexPlayer));
    }

    public Result banPlayer()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setBanned(true));
        return ok(ResultObj::success);
    }

    public Result unbanPlayer()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setBanned(false));
        return ok(ResultObj::success);
    }

    public Result addWhitelist()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setWhitelisted(true));
        return ok(ResultObj::success);
    }

    public Result removeWhitelist()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setWhitelisted(false));
        return ok(ResultObj::success);
    }

    public Result op()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setOp(true));
        return ok(ResultObj::success);
    }

    public Result deop()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        synchronous(() -> player.setOp(false));
        return ok(ResultObj::success);
    }

    public Result count()
    {
        return ok(c -> c.success().put("count", Bukkit.getOnlinePlayers().size()));
    }

    public Result offlinePlayerUniqueId()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(getPostParam("uuid")));
        if (player == null)
            return ok(c -> c.reject(playerNotFound), HttpStatus.BAD_REQUEST);
        return ok(c -> c.successData(new fr.kwizzy.app.model.OfflinePlayer(player)));
    }

    public Result offlinePlayerName()
    {
        if (paramPostNullOEmpty("name"))
            return badRequest();
        OfflinePlayer player = Bukkit.getOfflinePlayer(getPostParam("name"));
        if (player == null)
            return ok(c -> c.reject(playerNotFound), HttpStatus.BAD_REQUEST);
        return ok(c -> c.successData(new fr.kwizzy.app.model.OfflinePlayer(player)));
    }

    public Result all()
    {
        final JSONArray complexPlayers = new JSONArray();
        try {
            Collection<? extends org.bukkit.entity.Player> players = Bukkit.getOnlinePlayers();
            if (players.isEmpty())
                return ok(c -> c.reject(noPlayersOnline));
            for (org.bukkit.entity.Player player : players) {
                complexPlayers.put(new JSONObject(gson.toJson(new ComplexPlayer(player))));
            }
        } catch (Exception e) {
            return badRequest();
        }
        return ok(c -> c.successData(complexPlayers));
    }

    public Result allLite()
    {
        Collection<? extends org.bukkit.entity.Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty())
            return ok(c -> c.reject(noPlayersOnline), HttpStatus.BAD_REQUEST);
        JSONArray pl = new JSONArray();
        for (org.bukkit.entity.Player player : players) {
            pl.put(new JSONObject(gson.toJson(new fr.kwizzy.app.model.Player(player))));
        }
        return ok(c -> c.successData(pl));
    }

    public Result kick()
    {
        if (playerCheck() != null)
            return playerCheck();
        if (paramPostNullOEmpty("reason"))
            return badRequest();
        String uuid = getPostParam("uuid");
        String reason = getPostParam("reason");
        org.bukkit.entity.Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        synchronous(() -> p.kickPlayer(reason.replace("{player}", p.getName())));
        return ok(ResultObj::success);
    }

    public Result message()
    {
        if (playerCheck() != null)
            return playerCheck();
        if (paramPostNullOEmpty("message"))
            return badRequest();
        String uuid = getPostParam("uuid");
        String message = getPostParam("message");
        org.bukkit.entity.Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        synchronous(() -> p.sendMessage(message.replace("{player}", p.getName())));
        return ok(ResultObj::success);
    }

    public Result heal()
    {
        if (playerCheck() != null)
            return playerCheck();
        String uuid = getPostParam("uuid");
        org.bukkit.entity.Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        synchronous(() -> p.setHealth(p.getMaxHealth()));
        return ok(ResultObj::success);
    }

    public Result feed()
    {
        if (playerCheck() != null)
            return playerCheck();
        String uuid = getPostParam("uuid");
        org.bukkit.entity.Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        synchronous(() -> p.setFoodLevel(20));
        return ok(ResultObj::success);
    }

    public Result infoLite()
    {
        if (playerCheck() != null)
            return playerCheck();
        fr.kwizzy.app.model.Player player = new fr.kwizzy.app.model.Player(Bukkit.getPlayer(UUID.fromString(getPostParam("uuid"))));
        return ok(c -> c.successData(new JSONObject(gson.toJson(player))));
    }

    private Result playerCheck()
    {
        if (paramPostNullOEmpty("uuid"))
            return badRequest();
        else if (playerNull(Bukkit.getPlayer(UUID.fromString(getPostParam("uuid")))))
            return ok(c -> c.reject(playerNotFound));
        return null;
    }


}
