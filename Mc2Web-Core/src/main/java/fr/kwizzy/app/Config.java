package fr.kwizzy.app;

import com.google.gson.Gson;
import fr.kwizzy.app.database.DatabaseConfig;
import fr.kwizzy.app.util.storage.JSONStorage;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Created by Alexis on 07/01/2017.
 * French author.
 */

@ToString
@Getter
public class Config
{

    private static Config instance;

    private String pwd = UUID.randomUUID().toString();
    private String user = "admin";
    private int port = 9276;
    private DatabaseConfig database = new DatabaseConfig();



    private Config()
    {}

    public static Config getInstance()
    {
        if(instance == null)
        {
            JSONStorage jsonStorage = new JSONStorage("config");
            if(jsonStorage.exist()){
                instance = new Gson().fromJson(jsonStorage.getFullJson().get("data").toString(), Config.class);
                return instance;
            } else {
                jsonStorage.putEntireObject("data", new Config());
                jsonStorage.save();
                instance = new Gson().fromJson(jsonStorage.getFullJson().get("data").toString(), Config.class);
                return instance;
            }
        }
        else {
            return instance;
        }
    }

    public void save()
    {
        new JSONStorage("config").save();
    }
}
