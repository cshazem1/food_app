package com.demo.hazem_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class name_address_in_firebase extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
  static   DatabaseReference databaseReference;
    Context context;

    RecyclerView recyclerView;
    ArrayList<name_and_phone_in_firebase_class> myListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_address_in_firebase);
        //فيها الاسم ورقم الموب والعنوان
        databaseReference= firebaseDatabase.getInstance().getReference("myfood");
        myListData=new ArrayList<name_and_phone_in_firebase_class>();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_name_adress_firebase);
    }
    //Adapter
    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
        private ArrayList<name_and_phone_in_firebase_class> listdata;

        // RecyclerView recyclerView;
        public MyListAdapter(ArrayList<name_and_phone_in_firebase_class>listdata, Context context) {
            this.listdata = listdata;
        }
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.card_details2, parent, false);
            ViewHolder viewHolder = new MyListAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.textname.setText(listdata.get(position).getName())
            ;
            holder.textphone.setText(listdata.get(position).getPhone())
            ;

            holder.textaddress.setText(listdata.get(position).getAddress())
            ;

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //لوداس ع اي عنصر
                public void onClick(View v) {

                   AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\n" + "name:- "+listdata.get(position).name+"\n"+listdata.get(position).phone+"\n");

                    builder.setIcon(R.drawable.exite);
                    builder.setTitle("sure to delete?!"+"\n"+"if you want to Activity_Enter click no");

                    builder.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>yes</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseReference.child(listdata.get(position).key).removeValue();

                        }
                    });
                    builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();




                    //ارسال لل faster_man_admin روت بتاع الفون اللي من خلاله هيعرض طلبات الرقم ده
                    display(listdata.get(position).phone);
                }

            });


        }




        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textphone;
            public TextView textname;
            public TextView textaddress;


            public RelativeLayout relativeLayout;
            public ViewHolder(View itemView) {

                super(itemView);
                textname = (TextView) itemView.findViewById(R.id.customer_name);
                textphone= (TextView) itemView.findViewById(R.id.customer_phone);
                textaddress= (TextView) itemView.findViewById(R.id.customer_address);




            }
        }}







//قراءه من فيربيز

    void getnotes()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myListData.clear();
                for(DataSnapshot ds: snapshot.getChildren())

                {

                    name_and_phone_in_firebase_class send=ds.getValue(name_and_phone_in_firebase_class.class);
                    myListData.add(send);
                    MyListAdapter adapter = new MyListAdapter(myListData,name_address_in_firebase.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(name_address_in_firebase.this));
                    recyclerView.setAdapter(adapter);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        getnotes();
    }


    //ارسال لل faster_man_admin روت بتاع الفون اللي من خلاله هيعرض طلبات الرقم ده
    void display(String phone)
    {

Intent intent=new Intent(this,faster_man_admin_.class);
           Bundle bundle =new Bundle();
           bundle.putString("key",phone);
           intent.putExtras(bundle);
           startActivity(intent);




    }}