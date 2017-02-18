package fr.kwizzy.app.database;

import lombok.Getter;

/**
 * Created by Alexis on 12/02/2017.
 * French author.
 */

@Getter
public class DatabaseConfig
{

    private String password = "root";
    private String user = "root";
    private String host = "localhost";
    private int port = 3306;
    private String database = "website";
    private transient final String table = "confirm_user";

}
