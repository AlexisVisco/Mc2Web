package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexis on 05/02/2017.
 * French author.
 */
@Getter
public class BannedPlayers implements Bean
{

    private List<fr.kwizzy.app.model.OfflinePlayer> bannedPlayers = new ArrayList<>();

    public BannedPlayers()
    {
        bannedPlayers = Bukkit.getBannedPlayers().stream()
                .map(fr.kwizzy.app.model.OfflinePlayer::new)
                .collect(Collectors.toList());
    }
}
