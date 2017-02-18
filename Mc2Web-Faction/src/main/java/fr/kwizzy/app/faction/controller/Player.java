package fr.kwizzy.app.faction.controller;

import com.massivecraft.factions.entity.MPlayer;
import fr.kwizzy.app.faction.Message;
import fr.kwizzy.app.faction.bean.FPlayer;
import fr.kwizzy.app.back.Result;
import fr.kwizzy.app.back.Controller;

import java.util.UUID;

/**
 * Created by Alexis on 15/02/2017.
 * French author.
 */

public class Player extends Controller
{

    public Result getFPlayerFromUUID() {
        if (paramUrlNullOEmpty(":uuid"))
            return badRequest();
        String uuid = getUrlParam(":uuid");
        MPlayer mPlayer = MPlayer.get(UUID.fromString(uuid));
        if (mPlayer ==null || mPlayer.getFaction() == null)
            return ok(c -> c.reject(Message.factionNotFound));
        return ok(c -> c.successData(new FPlayer(mPlayer)));
    }

}
