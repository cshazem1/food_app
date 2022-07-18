package com.demo.hazem_food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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

public class faster_man_admin_ extends AppCompatActivity {
    static DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    RecyclerView recyclerView;
    ArrayList<firebaseclass> myListData;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faster_man_admin);
        //فيها الاسم ورقم الموب والعنوان
        myListData=new ArrayList<firebaseclass>();
        Bundle bundle=getIntent().getExtras();

        String phone=bundle.getString("key") ;
        databaseReference= firebaseDatabase.getInstance().getReference(phone);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }
    //Adapter
    public static class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
        private ArrayList<firebaseclass> listdata;

        // RecyclerView recyclerView;
        public MyListAdapter(ArrayList<firebaseclass>listdata, Context context) {
            this.listdata = listdata;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.card_details, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.textname.setText(listdata.get(position).getCustomer_name())
            ;
            holder.textphone.setText(listdata.get(position).getCustomer_phone())
            ;

            holder.textcost.setText(listdata.get(position).getCost())
            ;
            holder.textnumber.setText(listdata.get(position).getNumber())
            ;
            holder.texttotal.setText(listdata.get(position).getTotal())
            ;
            holder.textaddress.setText(listdata.get(position).getCustomer_Adrees())

            ;
            holder.textname_item.setText(listdata.get(position).getOrder_name());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //لوداس ع اي عنصر يتمسح

                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\n" + "name:- "+listdata.get(position).customer_name+"\n"+listdata.get(position).order_name+"\n");

                    builder.setIcon(R.drawable.exite);
                    builder.setTitle("sure to delete?!");

                    builder.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>yes</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String key=listdata.get(position).idkey;

                            databaseReference.child(listdata.get(position).idkey).removeValue();


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

                }

            });


        }




        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textphone;
            public TextView textname;
            public TextView textaddress;
            public TextView textcost;
            public TextView textnumber;
            public TextView texttotal;
            public TextView textname_item;

            public RelativeLayout relativeLayout;
            public ViewHolder(View itemView) {

                super(itemView);
                textname = (TextView) itemView.findViewById(R.id.customer_name);
                textphone= (TextView) itemView.findViewById(R.id.customer_phone);
                textcost= (TextView) itemView.findViewById(R.id.cost);
                texttotal= (TextView) itemView.findViewById(R.id.total_cost);
                textaddress= (TextView) itemView.findViewById(R.id.customer_address);
                textnumber= (TextView) itemView.findViewById(R.id.num);
                textname_item= (TextView) itemView.findViewById(R.id.name_item);





            }
        }}







//قراءه من فيربيز

    void getnotes()
    {
        Bundle bundle=getIntent().getExtras();

        String phone=bundle.getString("key") ;

        databaseReference= FirebaseDatabase.getInstance().getReference(phone);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myListData.clear();
                for(DataSnapshot ds: snapshot.getChildren())

                {

                    firebaseclass send=ds.getValue(firebaseclass.class);
                    myListData.add(send);
                    MyListAdapter adapter = new MyListAdapter(myListData,faster_man_admin_.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(faster_man_admin_.this));
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


}