package com.demo.hazem_food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cans extends AppCompatActivity {
    GridView gridView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cans);
        gridView  =(GridView)findViewById(R.id.checkin_gridview);

        setContentView(R.layout.activity_pizza);
        ArrayList<listviewclass> arrayList=new ArrayList<listviewclass>();
        arrayList.add(new listviewclass("cola","7",R.drawable.cola));
        arrayList.add(new listviewclass("sprite","6",R.drawable.sprite));
        arrayList.add(new listviewclass("mirnda","7",R.drawable.mirnda));
        arrayList.add(new listviewclass("7up","7",R.drawable.sevenup));
        arrayList.add(new listviewclass("coffee","15",R.drawable.coffee));
        mycustomerAdapter x=new mycustomerAdapter(arrayList);

        gridView  =(GridView)findViewById(R.id.checkin_gridview);

        gridView.setAdapter(x);
    }
    class mycustomerAdapter extends BaseAdapter {
        ArrayList<listviewclass> array = new ArrayList<listviewclass>();

        mycustomerAdapter(ArrayList<listviewclass> array) {
            this.array = array;
        }

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
            View view = inflater.inflate(R.layout.card_part, null);
            listviewclass temp = array.get(position);
            TextView name = (TextView) view.findViewById(R.id.name_item1);
            TextView coin = (TextView) view.findViewById(R.id.numitem1);
            ImageView imageView = (ImageView) view.findViewById(R.id.image1);
            name.setText(getItem(position));
            coin.setText(getItem2(position));
            imageView.setImageResource(temp.img);

            //________________________________________________________________________________
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                //                    //لودوست ع اي عنصر ف الgride هيفعل ال pop
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getApplicationContext(), array.get(position).name + " " + array.get(position).item, Toast.LENGTH_SHORT).show();

                    FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
                    pop_class pop_class = new pop_class();
                    pop_class.run(array.get(position).img,array.get(position).name,array.get(position).item);
                    pop_class.show(manager, null);
                }
            });
            return view;

        }
    }
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

}

