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
public class OppedPlayers implements Bean
{

    private List<OfflinePlayer> opPlayers = new ArrayList<>();

    public OppedPlayers()
    {
        opPlayers = Bukkit.getOperators().stream()
                .map(OfflinePlayer::new)
                .collect(Collectors.toList());

    }
}
