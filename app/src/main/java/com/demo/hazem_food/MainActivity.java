package com.demo.hazem_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"☺Food open♥",Toast.LENGTH_SHORT).show();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}

    //خاص بالmenu انها تبان ف الصفحه
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {getMenuInflater().inflate(R.menu.menu,menu);
        return true;}
    //..............................................

    //خاص ب اني اقدر اتفاعل مع اجزاء menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.exite)
        {this.finish();
            Toast.makeText(this,"Exite☺",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.help)
        {
            Intent intentt=new Intent(this,help.class);
            startActivity(intentt);
            Toast.makeText(this,"♥Help♥",Toast.LENGTH_SHORT).show();

        }
        else if (id==R.id.fast)
        {
            LayoutInflater inflater =getLayoutInflater();
            View view=inflater.inflate(R.layout.layoutinflater,(ViewGroup)findViewById(R.id.linear));
            Toast toast=new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }

        else if(id==R.id.fastdriver)
        {
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
//...................................................

    //زرار الدخول للصفحه التاليه
    public void enter(View view) {

        Intent intent=new Intent(this, circular.class);
        startActivity(intent);
    }


}