package fr.kwizzy.app;


import fr.kwizzy.app.command.ConfirmUser;
import fr.kwizzy.app.listener.ConsoleStream;
import fr.kwizzy.app.module.ModuleLoader;
import fr.kwizzy.app.module.ModuleManager;
import fr.kwizzy.app.util.plugin.JavaPlugin;
import fr.kwizzy.app.webserver.Application;
import fr.kwizzy.app.database.Database;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Alexis on 29/01/2017.
 * French author.
 */

public class Mc2Web extends JavaPlugin
{

    @Getter private static Mc2Web        instance;
    @Getter private static Config        globalConfig;
    @Getter private static ModuleManager moduleManager;
    @Getter private static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void onEnable()
    {
        init();
        registerEvents();
        enableModules();
        enableDatabase();
        setupWebServer();
        sendSponsorMessage();
    }

    private synchronized void init() {
        instance = this;
        globalConfig = Config.getInstance();
        moduleManager = new ModuleManager(new ModuleLoader(getClassLoader(), this));
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ConsoleStream(), this);
    }

    private void enableModules() {
        ModuleManager.enableAllModules(this);
    }

    private void enableDatabase() {
        if(Database.isConnected()) {
            getCommand("link-account").setExecutor(new ConfirmUser());
            Database.createTable();
        }
    }

    private void setupWebServer() {
        new BukkitRunnable() {
            @Override
            public void run()
            {
                Application.initialize();
            }
        }.runTaskLaterAsynchronously(this, 20*2);
    }

    @Override
    public void onDisable()
    {
        if (!moduleManager.getModules().isEmpty())
            moduleManager.disableModules();
        Application.stop();
        Database.close();
    }

    public static void toConsole(ChatColor c, Object o) {
        console.sendMessage("§b[§9" + getInstance().getDescription().getName() + "§b] "+ c + o.toString());
    }

    public static void toConsole(Object o) {
        toConsole(ChatColor.BLUE, o);
    }

    private void sendSponsorMessage() {
        new BukkitRunnable() {
            @Override
            public void run()
            {
                toConsole("************************************");
                toConsole("*- -        --        -           -*");
                toConsole("* §cMC2WEB IS SPONSORED BY NOCTISCMS §9*");
                toConsole("*--       -          -    -    -- -*");
                toConsole("************************************");
            }
        }.runTaskTimer(this, 20, 20*60*5L);
    }
}
