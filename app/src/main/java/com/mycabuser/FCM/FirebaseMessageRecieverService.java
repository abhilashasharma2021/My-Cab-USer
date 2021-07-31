package com.mycabuser.FCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mycabuser.Activity.NavigationActivity;
import com.mycabuser.MainActivity;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Notification.PRIORITY_HIGH;

public class FirebaseMessageRecieverService extends FirebaseMessagingService {

    String body = "";

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("check", "onMessageRecieved Called");


        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();

            Log.e("sjjcbs","" + title);
        }

        if (remoteMessage.getData().size() > 0) {
            Log.e("check", "Data received");
            Log.e("check", remoteMessage.getData().toString());

            try {
                String Data = remoteMessage.getData().toString();
                String newData = "";
                if (Data.contains("=")) {
                    newData = Data.replace("=", ":");
                }
                JSONObject jsonObject = new JSONObject(newData);
                JSONObject data = jsonObject.getJSONObject("data");
                String title = data.getString("title");
                String message = data.getString("message");
                String payload = data.getString("payload");
                String id="";
                JSONObject jsonObject1=new JSONObject(payload);
                JSONArray jsonArray=new JSONArray(jsonObject1.getString("driver_ride"));
                for (int i = 0; i <jsonArray.length() ; i++) {

                    JSONObject jsonObject2=jsonArray.getJSONObject(i);
                    id=jsonObject2.getString("id");

                    Log.e("check", "id: " +id);
                }




                Log.e("check", "title: " +title);
                Log.e("check", "message: " +message);

                 Intent myIntent = new Intent("Check");
                myIntent.putExtra("title", title);
                myIntent.putExtra("id", id);
                this.sendBroadcast(myIntent);


                Log.e("gnggdgndgndng", "onMessageReceived: " + message);
                Notification notification = new NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(PRIORITY_HIGH)
                        .setColor(Color.BLACK)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(1002, notification);

              /*  Intent resultIntent = new Intent(this, MainActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(resultIntent);
*/
                SharedHelper.putKey(getApplicationContext(), Appconstant.REQUESTID,id);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("jhjjhjhjhhb", e.getMessage());

            }
        }

    }
    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.e("hfhghdggdhhgdhgd", "onDeleteMessage Called");
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("check", "onNewToken Called");
        SharedHelper.putKey(getApplicationContext(), Appconstant.REG_ID_TOKEN, s);
    }






}


