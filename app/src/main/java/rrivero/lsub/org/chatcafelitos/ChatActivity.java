package rrivero.lsub.org.chatcafelitos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import mov.rrivero.msg.Msg;
import mov.rrivero.msg.Msg.*;

public class ChatActivity extends Activity {
    private Msg msg;
    private DataInputStream idata;
    private DataOutputStream odata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent activityIntent = new Intent(this, LoginActivity.class);
        startActivityForResult(activityIntent, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String email;
        String password;
        Bundle info = intent.getExtras();
        if (info != null) {
            email = info.getString("email");
            password = info.getString("password");
            Log.d("prueba", "email: " + email);
            Log.d("prueba", "pass: " + password);
            ClientWorker c = new ClientWorker(email, password);
            new Thread(c).start();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("prueba", "onRestart");
    }
}
