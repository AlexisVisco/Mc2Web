package fr.kwizzy.app.back;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spark.ModelAndView;

/**
 * Created by Alexis on 04/02/2017.
 * French author.
 */
@AllArgsConstructor
public class Result
{

    ResultObj result;

    public String getResult()
    {
        return result.getJson();
    }

    public ModelAndView getResultV()
    {
        return result.getModelAndView();
    }

}
