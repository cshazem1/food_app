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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Enter extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ArrayList<listviewclass> arrayList=new ArrayList<listviewclass>();


        arrayList.add(new listviewclass("BBQ","10 Items",R.drawable.bbq));
        arrayList.add(new listviewclass("Pizza","10 Items",R.drawable.pizza));
        arrayList.add(new listviewclass("Soups","5 Items",R.drawable.soups));
        arrayList.add(new listviewclass("Appetizers","3 Items",R.drawable.appetizers));
        arrayList.add(new listviewclass("Salads","4 Items",R.drawable.saladss));
        arrayList.add(new listviewclass("Drinks","5 Items",R.drawable.cans));

        mycustomerAdapter mycustomerAdapter=new mycustomerAdapter(arrayList);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(mycustomerAdapter);
    }
    //تفعيل menu
    public boolean onCreateOptionsMenu(Menu menu)
    {getMenuInflater().inflate(R.menu.menu,menu);
        return true;}

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
        else if(id==R.id.Admin)
        {
            Intent login_admain=new Intent(this, admin_login.class);
            startActivity(login_admain);

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


        return super.onOptionsItemSelected(item);
    }
    class mycustomerAdapter extends BaseAdapter
    {
        ArrayList<listviewclass> array=new ArrayList<listviewclass>();
        mycustomerAdapter(ArrayList<listviewclass>array)
        {this.array=array;}

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public String getItem(int position) {
            return array.get(position).name;
        }
        public String getItem2(int position) {
            return array.get(position).item;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.card_department, null);
            listviewclass temp = array.get(position);
            TextView name = (TextView) view.findViewById(R.id.name_item);
            TextView coin = (TextView) view.findViewById(R.id.numitem);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);

            name.setText(getItem(position));
            coin.setText(getItem2(position));
            imageView.setImageResource(temp.img);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    display(array.get(position).name);
                }
            });
            return view;




        }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        void display(String name)
        {
            if(name=="Pizza") {
                Intent intentt=new Intent(Activity_Enter.this, Pizza.class);

                startActivity(intentt);

            }
           else if(name=="BBQ") {
                Intent intentt=new Intent(Activity_Enter.this, BBQ.class);

                startActivity(intentt);

            }
            else if(name=="Salads") {
                Intent intentt=new Intent(Activity_Enter.this, Salads.class);

                startActivity(intentt);

            }

                     else if(name=="Soups") {
            Intent intentt=new Intent(Activity_Enter.this, soups.class);

            startActivity(intentt);

        }

                                else if(name=="Appetizers") {
            Intent intentt=new Intent(Activity_Enter.this, Appetizers.class);

            startActivity(intentt);

        }
            else if(name=="Drinks") {
                Intent intentt=new Intent(Activity_Enter.this, Cans.class);

                startActivity(intentt);

            }

        }

    }
}




