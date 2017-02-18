package fr.kwizzy.app.model;

import fr.kwizzy.app.util.Util;
import lombok.Getter;

import java.util.Date;

/**
 * Created by Alexis on 05/02/2017.
 * French author.
 */

@Getter
public class OfflinePlayer extends Player implements Bean
{

    public final String  dateLastPlayed;
    public final String  dateFirstPlayed;
    public final boolean isOnline;
    public final boolean isBan;
    public final boolean isWhitelist;
    public final boolean isOp;
    public final boolean isOnlineBefore;

    public OfflinePlayer(org.bukkit.OfflinePlayer player)
    {
        super(player.getUniqueId().toString(), player.getName(), "null");

        dateLastPlayed  = Util.parseDate(new Date(player.getLastPlayed() ));
        dateFirstPlayed = Util.parseDate(new Date(player.getFirstPlayed()));
        isOnline        = player.isOnline();
        isWhitelist     = player.isWhitelisted();
        isBan           = player.isBanned();
        isOp            = player.isOp();
        isOnlineBefore  = player.hasPlayedBefore();
    }
}
