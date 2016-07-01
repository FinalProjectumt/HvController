package netroxtech.com.hvcontroller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionMenu;

import java.text.CollationElementIterator;

public class MainActivity extends AppCompatActivity {


    SmsManager smsManager;
    private static final int RESULT_PICK_CONTACT = 100;

    private EditText ph_noo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageButton contact_open = (ImageButton) findViewById(R.id.opencontact);
       contact_open.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
            }


        });




        FloatingActionButton openspeak_button = (FloatingActionButton) findViewById(R.id.mic_fab);
        openspeak_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeakToText.class));
            }
        });
        final ImageButton send_msg = (ImageButton) findViewById(R.id.send_btn);
        final EditText msg_body = (EditText) findViewById(R.id.msg);
          ph_noo = (EditText) findViewById(R.id.ph_no);

            FloatingActionButton opentext = (FloatingActionButton) findViewById(R.id.text_fab);
        opentext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                msg_body.setVisibility(View.VISIBLE);
                send_msg.setVisibility(View.VISIBLE);

            }
        });

        smsManager = SmsManager.getDefault();

        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ph_noo.getText().toString().equals("") && msg_body.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this,"Please enter number and message",Toast.LENGTH_SHORT).show();
                }
                else{
                    String number = ph_noo.getText().toString();
                    String body1 = msg_body.getText().toString();
                    smsManager.sendTextMessage(number, null, body1, null, null);
                    Toast.makeText(getApplicationContext(), "Send  Sms Successfully", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString(phoneIndex);



            ph_noo.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
