package fr.kwizzy.app.webserver;

import fr.kwizzy.app.back.HttpStatus;
import fr.kwizzy.app.controller.*;
import fr.kwizzy.app.Config;
import fr.kwizzy.app.Mc2Web;
import fr.kwizzy.app.back.WebServer;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.json.JSONObject;
import spark.Spark;

import java.util.Base64;
import java.util.logging.Level;

import static fr.kwizzy.app.back.WebServer.*;

/**
 * Created by Alexis on 06/01/2017.
 * French author.
 */

public class Application
{

    private static Config config = Config.getInstance();
    @Getter
    private static WebServer webServer = new WebServer();
    private static boolean   auth      = true;

    public static void initialize()
    {

        Mc2Web.toConsole("§aInitialisation of MC2WEB");

        Spark.staticFileLocation("/public");

        webServer.port(config.getPort());

        Spark.webSocket("/console", Console.class);

        if (auth)
            authenticate();


        group(Player.class, c -> {
            c.post("/api/player/get/:info", Player::customInfo);
            c.post("/api/player/info", Player::info);
            c.post("/api/player/info/lite", Player::infoLite);
            c.post("/api/player/kick", Player::kick);
            c.post("/api/player/message", Player::message);
            c.post("/api/player/clear", Player::clear);
            c.post("/api/player/offline/uuid", Player::offlinePlayerUniqueId);
            c.post("/api/player/offline/name", Player::offlinePlayerName);
            c.post("/api/player/teleport", Player::teleport);
            c.post("/api/player/unwhitelist", Player::addWhitelist);
            c.post("/api/player/whitelist", Player::removeWhitelist);
            c.post("/api/player/ban", Player::banPlayer);
            c.post("/api/player/unban", Player::unbanPlayer);
            c.post("/api/player/op", Player::op);
            c.post("/api/player/deop", Player::deop);
            c.post("/api/player/heal", Player::heal);
            c.post("/api/player/feed", Player::feed);
        });

        group(Player.class, c -> {
            c.get("/api/player/banned", Player::bannedPlayers);
            c.get("/api/player/whitelisted", Player::whitelistedPlayers);
            c.get("/api/player/opped", Player::oppedPlayers);
            c.get("/api/player/all", Player::all);
            c.get("/api/player/all/lite", Player::allLite);
            c.get("/api/player/count", Player::count);
        });

        group(World.class, c -> {
            c.get("/api/world/get/:world", World::world);
            c.get("/api/world/all", World::allWorlds);

            c.post("/api/world/set/blockAt", World::setBlockAt);
            c.post("/api/world/get/blockAt", World::getBlockAt);
        });

        get(Command.class, "/api/command", Command::execute);

        group(Server.class, c -> {
            c.get("/api/server", Server::server);
            c.get("/api/server/plugins", Server::plugins);
            c.get("/api/server/plugins/lite", Server::pluginsLite);
            c.get("/api/server/stop", Server::stop);
            c.get("/api/server/modules", Server::modules);

            c.post("/api/server/broadcast", Server::broadcast);
        });

        Spark.get("/online", (request, response) -> new JSONObject().put("online", true).toString());

        Spark.get("/restricted", (request, response) -> new JSONObject().put("success", false).put("reason", Message.badToken).toString());

        Mc2Web.getModuleManager().forEach(e -> {
            if(e.isEnabled())
                e.getApplication().injectRoutes();
        });
    }




    private static void authenticate()
    {
        before("/api/*", (request, response) -> {
            Boolean authenticated = false;
            String auth = request.headers("Authorization");
            if (auth != null && auth.startsWith("Basic")) {
                String b64Credentials = auth.substring("Basic".length()).trim();
                String credentials = new String(Base64.getDecoder().decode(b64Credentials));
                if (credentials.equals(config.getUser() + ":" + config.getPwd()))
                    authenticated = true;
            }
            if (!authenticated) {
                response.header("WWW-Authenticate", "Basic realm=\"Restricted\"");
                response.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
                response.redirect("/restricted");
            }
        });
    }

    public static void stop()
    {
        Mc2Web.toConsole("§cStopping MC2WEB ...");
        Spark.stop();

    }
}
