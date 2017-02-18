package fr.kwizzy.app.faction.controller;

import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import fr.kwizzy.app.faction.Message;
import fr.kwizzy.app.back.Controller;
import fr.kwizzy.app.back.Result;

import java.util.UUID;

/**
 * Created by Alexis on 13/02/2017.
 * French author.
 */

public class Faction extends Controller
{

    public Result getFactionFromName()
    {
        try {
            if (paramUrlNullOEmpty(":faction"))
                return badRequest();
            String factionName = getUrlParam(":faction");
            if (FactionColl.get().getByName(factionName) == null)
                return ok(c -> c.reject(Message.factionNotFound));
            return ok(c -> c.successData(new fr.kwizzy.app.faction.bean.Faction(factionName)));
        } catch (Exception e) {
            return ok(c -> c.reject(Message.factionNotFound));
        }
    }

    public Result getFactionFromPlayer()
    {
        try {
        if (paramUrlNullOEmpty(":uuid"))
            return badRequest();
        String uuid = getUrlParam(":uuid");
        MPlayer mPlayer = MPlayer.get(UUID.fromString(uuid));
        if (mPlayer ==null || mPlayer.getFaction() == null)
            return ok(c -> c.reject(Message.factionNotFound));
        return ok(c -> c.successData(new fr.kwizzy.app.faction.bean.Faction(uuid)));
        } catch (Exception e) {
            return ok(c -> c.reject(Message.factionNotFound));
        }
    }
}
