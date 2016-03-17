package rrivero.lsub.org.chatcafelitos;

import android.util.Log;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import mov.rrivero.msg.Msg;
import mov.rrivero.msg.Msg.*;

/**
 * Created by rrivero on 16/03/16.
 */
public class ClientWorker implements Runnable {
    private String email;
    private String pass;
    private DataInputStream idata;
    private DataOutputStream odata;

    public ClientWorker(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    @Override
    public void run() {
        Socket fd = null;
        try{
            fd = new Socket("10.0.2.2", 1234);
            idata = new DataInputStream(fd.getInputStream());
            odata = new DataOutputStream(fd.getOutputStream());
            TLogin tmsg = new TLogin(email, pass);
            tmsg.writeTo(odata);
            Msg msg = Msg.readMsg(idata);
            if (msg instanceof ROk) {
                Log.d("prueba", msg.toString());
            } else if (msg instanceof RErr) {
                Log.d("prueba", ((RErr) msg).getErr());
            }
        } catch (Exception e){

            System.err.println(e.toString());
        } finally {
            if (fd != null) {
                try {
                    fd.close();
                    odata.close();
                    idata.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
