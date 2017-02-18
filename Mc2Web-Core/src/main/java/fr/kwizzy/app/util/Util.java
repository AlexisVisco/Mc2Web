package fr.kwizzy.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * Created by Alexis on 05/02/2017.
 * French author.
 */

public class Util
{

    public static String parseDate(Date d) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
    }

    public static <FROM, TO> Function<FROM, TO> changeListType(Class<FROM> fromClass , Class<TO> toClass, Function<FROM, TO> apply) {
        return apply;
    }
}
