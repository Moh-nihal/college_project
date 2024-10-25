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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_view_services extends BaseAdapter {


    private Context context;
    ArrayList<String> sid1,servname1,servdesc1;

    SharedPreferences sh;



    public Custom_view_services(Context applicationContext, ArrayList<String> sid, ArrayList<String> servname1, ArrayList<String> servdesc ) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.sid1=sid;

            this.servname1=servname1;
            this.servdesc1=servdesc;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        return servname1.size();
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
            gridView=inflator.inflate(R.layout.activity_custom_view_services, null);

        }
        else
        {
            gridView=(View)view;

        }

            TextView servicename=(TextView)gridView.findViewById(R.id.textView15);
            TextView description=(TextView)gridView.findViewById(R.id.textView17);
        ImageView image=(ImageView) gridView.findViewById(R.id.imageView2);
        image.setTag(sid1.get(i));
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("service_id", view.getTag().toString());
                edp.commit();

                Intent req= new Intent(context.getApplicationContext(),Sendrequest.class);
                context.startActivity(req);

            }
        });





            servicename.setText(servname1.get(i));
            description.setText(servdesc1.get(i));












            servicename.setTextColor(Color.BLACK);
            description.setTextColor(Color.BLACK);













        return gridView;


    }
}