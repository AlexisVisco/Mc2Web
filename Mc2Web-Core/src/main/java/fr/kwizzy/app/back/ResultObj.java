package fr.kwizzy.app.back;

import fr.kwizzy.app.model.Bean;
import org.json.JSONObject;
import spark.ModelAndView;

/**
 * Created by Alexis on 17/02/2017.
 * French author.
 *
 * This class is a result object for ok(...)
 * methods in controller.
 */
public class ResultObj
{

    private JSONObject responseJson = new JSONObject();
    private ModelAndView modelAndView;

    public ResultObj setSuccess(boolean b) {
        responseJson.put("success", b);
        return this;
    }

    public ResultObj success() {
        setSuccess(true);
        return this;
    }

    public ResultObj reject(String reason) {
        setSuccess(false).put("reason", reason);
        return this;
    }

    public ResultObj setData(Bean b) {
        responseJson.put("data", new JSONObject(b.toGson()));
        return this;
    }

    public ResultObj successData(Bean b) {
        setSuccess(true).setData(b);
        return this;
    }

    public ResultObj successData(JSONObject b) {
        setSuccess(true).put("data", b);
        return this;
    }

    public ResultObj successData(Object b) {
        setSuccess(true).put("data", b);
        return this;
    }

    public ResultObj setModelAndView(ModelAndView mav) {
        modelAndView = mav;
        return this;
    }

    public ResultObj put(String key, Object value) {
        responseJson.put(key, value);
        return this;
    }

    public String getJson() {
        return responseJson.toString();
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

}
