package com.example.service_pro_blossom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_view_request extends BaseAdapter {
    private Context context;
    ArrayList<String> rid1,services1,cenname1,reqst1,date1,status1;

    SharedPreferences sh;



    public custom_view_request(Context applicationContext, ArrayList<String> rid, ArrayList<String> services, ArrayList<String> cenname, ArrayList<String> reqst, ArrayList<String> date,ArrayList<String> status) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.rid1=rid;
        this.services1=services;
        this.cenname1=cenname;
        this.reqst1=reqst;
        this.date1=date;
        this.status1=status;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        return services1.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_view_request, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView service=(TextView)gridView.findViewById(R.id.textView10);
        TextView center=(TextView)gridView.findViewById(R.id.textView12);
        TextView request=(TextView)gridView.findViewById(R.id.textView18);
        TextView date=(TextView)gridView.findViewById(R.id.textView22);
        TextView status=(TextView)gridView.findViewById(R.id.textView23);
        Button btpay=(Button) gridView.findViewById(R.id.button7);






        service.setText(services1.get(i));
        center.setText(cenname1.get(i));
        request.setText(reqst1.get(i));
        date.setText(date1.get(i));
        status.setText(status1.get(i));

        if (status1.get(i).equalsIgnoreCase("Pending"))
        {
            btpay.setVisibility(View.INVISIBLE);
        }
        else {
            btpay.setVisibility(View.VISIBLE);

        }


        btpay.setTag(i);
        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= Integer.parseInt(v.getTag().toString());
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("requestid", rid1.get(pos));
                edp.putString("st", status1.get(pos));
                edp.commit();
//                Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
                Intent ik = new Intent(context, total_bill.class);
                context.startActivity(ik);
            }
        });














        service.setTextColor(Color.BLACK);
        center.setTextColor(Color.BLACK);
        request.setTextColor(Color.BLACK);
        date.setTextColor(Color.BLACK);
        status.setTextColor(Color.BLACK);













        return gridView;


    }
}