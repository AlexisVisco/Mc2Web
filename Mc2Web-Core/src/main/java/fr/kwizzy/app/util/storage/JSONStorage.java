package fr.kwizzy.app.util.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.kwizzy.app.Mc2Web;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Par Alexis le 26/10/2016.
 */

public class JSONStorage implements Storage
{
    private static final String PATH = Mc2Web.getInstance().getDataFolder().getAbsolutePath() + File.separator;
    private static HashMap<String, JSONObject> jsonObjects = new HashMap<>();

    /* like folder/file */
    private String pathFile;

    public JSONStorage(String path)
    {
        this.pathFile = path;
        init();
    }

    public static void save(String k, JSONObject v)
    {
        File f = getFile(k);
        if (!f.exists()) {
            try {

                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fw = new FileWriter(f.getAbsolutePath())) {
            saveFile(v, fw);
        } catch (IOException ignored) {
        }
    }

    public void reload()
    {
        jsonObjects.remove(pathFile);
        init();
    }

    private static void saveFile(JSONObject v, FileWriter fw) throws IOException
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(v.toString());
        final byte[] gsonBytes = gson.toJson(je).getBytes(Charset.forName("UTF-8"));
        final String utf8 = new String(gsonBytes, Charset.forName("UTF-8"));
        fw.write(utf8);
        fw.flush();
        fw.close();
    }

    private static File getFile(String path)
    {
        String[] parts = path.split(Pattern.quote("/"));
        StringBuilder pathAbsolute = new StringBuilder();
        for (int x = 0; x < parts.length; x++) {
            if (x == parts.length - 1)
                pathAbsolute.append(parts[x]);
            else
                pathAbsolute.append(parts[x]).append(File.separator);
        }
        final File f = new File(PATH, pathAbsolute.toString() + ".json");
        return f;

    }

    public static void saveAll()
    {
        jsonObjects.forEach(JSONStorage::save);
    }

    private void init()
    {
        if (!jsonObjects.containsKey(pathFile)) {
            String doc = readFile(getFile(pathFile).getAbsolutePath());
            JSONObject j = new JSONObject();
            if (doc != null && !doc.isEmpty())
                j = new JSONObject(doc);

            jsonObjects.put(pathFile, j);
        }
    }

    public void put(String path, Object o)
    {
        String[] split = path.split(Pattern.quote("."));
        Map<String, Object> base = getFullJson().toMap();
        Map<String, Object> m = base;
        for (int i = 0; i < split.length - 1; i++) {
            String p = split[i];
            Object mp = m.get(p);
            if (mp == null || !(mp instanceof Map)) {
                mp = new HashMap<>();
                m.put(p, mp);
            }
            m = (Map<String, Object>) mp;
        }
        m.put(split[split.length - 1], o);
        jsonObjects.put(pathFile, new JSONObject(base));
    }

    public void putEntireObject(String path, Object o)
    {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject(new Gson().toJson(o));
        put(path, jsonObject);
    }

    public Object get(String path)
    {
        String[] split = path.split(Pattern.quote("."));
        JSONObject jsonObject = getFullJson();
        JSONObject json = null;
        Object o = "";
        try {
            for (int i = 0; i < split.length; i++) {
                if (i == split.length - 1)
                    o = ((json == null) ? jsonObject.get(split[i]) : json.get(split[i]));
                else
                    json = (JSONObject) ((json == null) ? jsonObject.get(split[i]) : json.get(split[i]));

            }
        } catch (JSONException e) { return null;}
        return o;
    }

    public void disable()
    {
        jsonObjects.remove(pathFile);
    }

    public <T> T getObject(Class<? extends T> clazz, String path)
    {
        Object o = get(path);
        return new Gson().fromJson(o.toString(), clazz);
    }

    public String getString(String path)
    {
        return get(path).toString();
    }

    public boolean remove(String path)
    {
        String[] paths = path.split(Pattern.quote("."));
        Map<String, Object> base = getFullJson().toMap();
        Map<String, Object> m = base;
        for (int i = 1; i < paths.length - 1; i++) {
            String p = paths[i];
            Object mp = m.get(p);
            try {
                m = (Map<String, Object>) mp;
            } catch (Exception e) {
                return false;
            }
        }
        m.remove(paths[paths.length - 1]);
        if (pathFile != null)
            jsonObjects.put(pathFile, new JSONObject(base));
        return true;
    }

    public boolean contain(String path)
    {
        return get(path) != null;
    }

    public void delete()
    {
        if (pathFile != null) {
            jsonObjects.remove(pathFile);
            getFile(pathFile).deleteOnExit();
        }
    }

    public JSONObject getFullJson()
    {
        return jsonObjects.get(pathFile);
    }

    public void save()
    {
        if (pathFile != null)
            save(pathFile, jsonObjects.get(pathFile));
    }

    private String readFile(String filePath)
    {

        String result;
        try (FileReader fileReader = new FileReader(filePath)) {
            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
            fileReader.close();
            br.close();
        } catch (Exception ignored) {
            return null;
        }
        return result;
    }

    public boolean exist()
    {
        File f = getFile(pathFile);
        return (f.exists());
    }
}
