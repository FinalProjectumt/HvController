package netroxtech.com.hvcontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bilal on 7/1/2016.
 */
public class CreatePreference {



    public static final String MyPREFERENCES = "MyPrefs";

    SharedPreferences sharedpreferences;
      public  CreatePreference(Context context){
          sharedpreferences = (context).getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

      }
    public void saveCommands(String message){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(Constant.TAG_SharePref, message);
        editor.commit();
    }
    public void deleteCommands(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Constant.TAG_SharePref);
        editor.commit();

    }
    public  String getCommands(){

      String Message =    sharedpreferences.getString(Constant.TAG_SharePref, "");
        Log.v(Constant.TAG, Message);
        return  Message;
    }
}