package fr.kwizzy.app.faction;

import fr.kwizzy.app.module.Application;
import fr.kwizzy.app.faction.controller.Faction;
import fr.kwizzy.app.faction.controller.Player;

import static fr.kwizzy.app.back.WebServer.*;


/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */

public class FactionRouter extends Application
{

    FactionRouter()
    {
        super(

                new String[]{"Factions"},
                new String[]{"Factions", "MassiveCore"}
        );
    }

    @Override
    public void injectRoutes()
    {
        group(Faction.class, c -> {

            c.get("/api/faction/get/name/:faction", Faction::getFactionFromName);
            c.get("/api/faction/get/player/:uuid", Faction::getFactionFromPlayer);

        });

        group(Player.class, c -> {

            c.get("/api/faction/player/:uuid", Player::getFPlayerFromUUID);

        });
    }
}
