package com.demo.hazem_food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class help extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);





    }





    public boolean onCreateOptionsMenu(Menu menu)
    {getMenuInflater().inflate(R.menu.menu,menu);
        return true;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();


        if (id==R.id.fast)
        {
            this.finish();
            LayoutInflater inflater =getLayoutInflater();
            View view=inflater.inflate(R.layout.layoutinflater,(ViewGroup)findViewById(R.id.linear));
            Toast toast=new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
        else if(id==R.id.Admin)
        {
            Intent login_admain=new Intent(this, admin_login.class);
            startActivity(login_admain);

        }

        else if(id==R.id.fastdriver)
        {
            this.finish();
            Intent intentt=new Intent(this, order_to_fastdriver.class);
            startActivity(intentt);

        }
        else if(id==R.id.Admin)
        {
            Intent login_admain=new Intent(this, admin_login.class);
            startActivity(login_admain);

        }



        return super.onOptionsItemSelected(item);
    }
    int x=1;
    public void rr(View view) {
        Uri alarm= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
        NotificationManagerCompat notificationManagerCompat;
        Notification notification;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {NotificationChannel notificationChannel=new NotificationChannel("j","b",NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

        }
        Bitmap icon= BitmapFactory.decodeResource(getResources(),R.drawable.bbq);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"j").setOngoing(true).setSmallIcon(R.drawable.icon).setContentText("hhhh").setPriority(NotificationCompat.BADGE_ICON_LARGE).setSound(alarm).setLights(0xfffffff,500,500).setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.chicken)).setColor(0xffc6014).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(icon));
        NotificationManagerCompat notificationManagerCompat1 =NotificationManagerCompat.from(this);

        notificationManagerCompat1.notify(x, builder.build());
        x++;
        }
    }
