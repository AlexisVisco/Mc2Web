package fr.kwizzy.app.command;

import fr.kwizzy.app.Mc2Web;
import fr.kwizzy.app.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Alexis on 12/02/2017.
 * French author.
 *
 * Simple system in order to check if
 * a user is registrated with the website or not.
 */

public class ConfirmUser implements CommandExecutor
{


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(!(commandSender instanceof Player))
            return false;
        Player p = (Player) commandSender;
        if(args.length >= 1)
        {
            String arg = args[0];
            Mc2Web.toConsole(arg);
            boolean b = Database.confirmCode(arg, p.getUniqueId());
            if(b)
            {
                p.sendMessage("§a§l✓ §aFélicitation ton compte est confirmé !");
                return true;
            } else {
                p.sendMessage("§c✘ Une erreur est survenu.");
            }
        }
        return false;
    }
}
