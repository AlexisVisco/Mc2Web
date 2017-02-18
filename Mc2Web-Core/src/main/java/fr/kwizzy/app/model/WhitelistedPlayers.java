package fr.kwizzy.app.model;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexis on 05/02/2017.
 * French author.
 */

@Getter
public class WhitelistedPlayers implements Bean
{

    private List<OfflinePlayer> whitelistedPlayers = new ArrayList<>();

    public WhitelistedPlayers()
    {
        whitelistedPlayers = Bukkit.getWhitelistedPlayers().stream()
                .map(OfflinePlayer::new)
                .collect(Collectors.toList());
    }
}
