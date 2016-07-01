package netroxtech.com.hvcontroller;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aleea on 12-Jun-16.
 */
public class pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

       /* DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6)); */

        final EditText contact_name = (EditText) findViewById(R.id.contactname);
        final EditText contact_no = (EditText) findViewById(R.id.contactno);

        Button save_contact = (Button) findViewById(R.id.savecontact);

        save_contact.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View v) {



                }

        }

        );
    }
}

