package com.demo.hazem_food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class order_to_fastdriver extends AppCompatActivity {
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    TextView customer_name;
    TextView   customer_phone;
    TextView  customer_address;
    int total=0;

    //ده هيتحط جميع البيانات ف ليسته التاكيد
    static ArrayList<listviewclass_total> arrayList=new ArrayList<listviewclass_total>();

    GridView gridView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_fastdriver);
        //هيبعت الاسم ورقم الفون
        databaseReference1= FirebaseDatabase.getInstance().getReference("myfood");

        mycustomerAdapter mycustomerAdapter=new mycustomerAdapter(arrayList);

        gridView  =(GridView)findViewById(R.id.fastdrivergridview);


        gridView.setAdapter(mycustomerAdapter);


        TextView      customer_name=(TextView)findViewById(R.id.send_name);
        TextView   customer_phone=(TextView)findViewById(R.id.send_phone);
        TextView  customer_address=(TextView)findViewById(R.id.send_address);



        //...............................................................
TextView alloftotal=(TextView) findViewById(R.id.alloftotlal);

for(int i=0;i<arrayList.size();i++)
{
     int x=Integer.parseInt(arrayList.get(i).total);
total+=x;
}
alloftotal.setText("All of Total:: "+String.valueOf(total)+"\n"+"items:: "+String.valueOf(arrayList.size()));

    }
    //...................................................




    //زرار المسح
    public void remove(View view) {

        if(arrayList.size()!=0)
        {
            //قائمه منبثقه
            AlertDialog.Builder builder=new AlertDialog.Builder(order_to_fastdriver.this);
            builder.setMessage("\n"+"All Items");

            builder.setIcon(R.drawable.exite);
            builder.setTitle("sure to delete?!" );

            builder.setPositiveButton( Html.fromHtml("<font color='#FF7F27'>yes</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(getApplicationContext(), order_to_fastdriver.class);

                    arrayList.clear();
                    order_to_fastdriver.this.finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Delete ALL::", Toast.LENGTH_SHORT).show();

                }
            });
            builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    dialog.dismiss();
                }
            });
            AlertDialog alert=builder.create();
            alert.show();

        }}







    public void send_to_firebase(View view) {
        customer_name=(TextView)findViewById(R.id.send_name);
        customer_phone=(TextView)findViewById(R.id.send_phone);
        customer_address=(TextView)findViewById(R.id.send_address);
        String name_size=String.valueOf(customer_phone.getText().toString().length());
        if(arrayList.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"No items",Toast.LENGTH_SHORT).show();


        }

        else {
            if(Integer.parseInt(name_size)==11&!customer_name.getText().toString().equals("")&!customer_address.getText().toString().equals(""))


            {
                senddata();
                customer_address.setText("");
                customer_phone.setText("");
                customer_name.setText("");
                arrayList.clear();
                Toast.makeText(getApplicationContext(),"send your order",Toast.LENGTH_LONG).show();
                replay();


            }
            else    Toast.makeText(getApplicationContext(),"check your data",Toast.LENGTH_LONG).show();}




    }


    class mycustomerAdapter extends BaseAdapter {
        ArrayList<listviewclass_total> array = new ArrayList<listviewclass_total>();

        mycustomerAdapter(ArrayList<listviewclass_total> array) {
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
            View view = inflater.inflate(R.layout.card_order_of_details, null);
            listviewclass_total temp = array.get(position);
            TextView name = (TextView) view.findViewById(R.id.name_item);
            TextView coin = (TextView) view.findViewById(R.id.numitem);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            TextView number = (TextView) view.findViewById(R.id.num);
            TextView total_cost=(TextView)view.findViewById(R.id.total_cost);


            name.setText(getItem(position));
            coin.setText(getItem2(position));
            imageView.setImageResource(temp.img);
            number.setText(temp.number);
            total_cost.setText(temp.total);


            //لما اجي ادوس ع اي عنصر ف اليسته
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ////////////////تجربه للي تحت



                    AlertDialog.Builder builder=new AlertDialog.Builder(order_to_fastdriver.this);
                    builder.setMessage("\n"+ array.get(position).name);

                    builder.setIcon(R.drawable.exite);
                    builder.setTitle("sure to delete?!" );

                    builder.setPositiveButton( Html.fromHtml("<font color='#FF7F27'>yes</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(), order_to_fastdriver.class);
                            Toast.makeText(getApplicationContext(), "Delete the"+array.get(position).name, Toast.LENGTH_SHORT).show();
                            arrayList.remove(position);
                            order_to_fastdriver.this.finish();
                            startActivity(intent);

                        }
                    });
                    builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert=builder.create();
                    alert.show();

                }

            });
            return view;
        }


    }

    // هستلم هنا العناصر اللي اكدت عليها ف pop

    void setiteminfast_driver(String name, String cost, int image, String number, String totalcost)
    {
        arrayList.add(new listviewclass_total(name,cost,image,number,totalcost));


    }

    void replay()
    {
        this.finish();
        Intent intent=new Intent(this, order_to_fastdriver.class);

        startActivity(intent);
    }
    void replaytoname_address()
    {     this.finish();
        Intent intent=new Intent(this, name_address_in_firebase.class);

        startActivity(intent);}
    //menu
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
            this.finish();
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




        return super.onOptionsItemSelected(item);
    }
    //////////////تجربه فيربيز
    void senddata()
    {

        int x=0;

        //notification
        NotificationManagerCompat notificationManagerCompat;
        Notification notification;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel("j","b", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        Bitmap icon= BitmapFactory.decodeResource(getResources(),R.drawable.bbq);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"j").setOngoing(true).setSmallIcon(R.drawable.fastfood).setContentText("Successful operation \ntotal:"+total).setPriority(NotificationCompat.BADGE_ICON_LARGE).setLights(0xfffffff,500,500).setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.icon)).setColor(0xffc6014).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(icon));
        NotificationManagerCompat notificationManagerCompat1 =NotificationManagerCompat.from(this);

        notificationManagerCompat1.notify(x, builder.build());

        x++;
        //هنا هضيف الداتا بتاعتي ف الفيربيز

        databaseReference= FirebaseDatabase.getInstance().getReference(customer_phone.getText().toString());

        String notekey1= databaseReference1.push().getKey();

        {for(int i=0;i<arrayList.size();i++)
        {

            String notekey= databaseReference.push().getKey();

            firebaseclass send=new firebaseclass(notekey,customer_name.getText().toString(),customer_phone.getText().toString(),customer_address.getText().toString(),arrayList.get(i).name,arrayList.get(i).item,arrayList.get(i).number,arrayList.get(i).total);
            databaseReference.child(notekey).setValue(send);


            //هجيب ك خاص للداتا الاولي اللي هسجل فيها الفون والاسم والمكان


            name_and_phone_in_firebase_class send1=new name_and_phone_in_firebase_class(notekey1,customer_name.getText().toString(),customer_phone.getText().toString(),customer_address.getText().toString());
            databaseReference1.child(notekey1).setValue(send1);

        }
        }

    }}
//داله خاصه علشان ابعت الداتا للفيربيز





