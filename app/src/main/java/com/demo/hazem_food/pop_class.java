package com.demo.hazem_food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class pop_class extends DialogFragment implements View.OnClickListener {
    ImageView ImageView;
    View view;
    Button busearch;
    int image;
    int total;
    String total_;
    String cost;
    String name;
    int counter=1;
    Button bucancel;
    order_to_fastdriver fast_drivr=new order_to_fastdriver();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_pop, container, false);

        bucancel = (Button) view.findViewById(R.id.butoncancel);
        Button sure=(Button)view.findViewById(R.id.sure);

        //مهمه☺
        bucancel.setOnClickListener(this);


        //التفاعل مع  الpop بباظي ليها الصوره
        ImageView imageView=(ImageView) view.findViewById(R.id.image_pop);
        TextView text_name=(TextView) view.findViewById(R.id.name_pop);
        TextView text_cost=(TextView) view.findViewById(R.id.cost);
        TextView text_counter=(TextView) view.findViewById(R.id.txt_counter);
        TextView total_cost=(TextView) view.findViewById(R.id.totalcost);

        Button but_counter_plus=(Button)view.findViewById(R.id.but_counter_plus);
        Button but_counter_minus=(Button)view.findViewById(R.id.but_counter_minus);
        total=Integer.parseInt(cost);
        total_cost.setText(cost);
        text_name.setText(name);
        text_cost.setText(cost);
        imageView.setImageResource(image);
        //لو دوست ع زرار +
        but_counter_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter<=20)
                {
                    text_counter.setText(String.valueOf(counter));

                    total+=Integer.parseInt(cost);

                    total_cost.setText(String.valueOf(total));

                }
                else
                    counter=20;
            }
        });

        //لما ادوس ع زرار الناقص
        but_counter_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_cost.setText(String.valueOf(total));
                counter--;
                if(counter>0)
                {
                    text_counter.setText(String.valueOf(counter));
                    total-=Integer.parseInt(cost);;

                    total_cost.setText(String.valueOf(total));
                }
                else counter=1;
            }

        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getContext(),"sure",Toast.LENGTH_SHORT).show();
                //هبعت الداتا هنا للفاست ضرايف اللي اخترتها

                int  total=counter*Integer.parseInt(cost);
                String total_=String.valueOf(total);
                order_to_fastdriver set=new order_to_fastdriver();

                set.setiteminfast_driver(name,cost,image,String.valueOf(counter),total_);

            }

        });

        return view;
    }

    //لما ادوس ع x هخرج
    @Override
    public void onClick(View view) {



        this.dismiss();        }









    // ده لما ببعت من عليه الاسم والسعر والصوره علشان اعرف اضفها ف pop وواخدها منfish
    void run(int image,String name,String cost)
    {
        this.cost=cost;
        this.image=image;
        this.name=name;

    }




}

