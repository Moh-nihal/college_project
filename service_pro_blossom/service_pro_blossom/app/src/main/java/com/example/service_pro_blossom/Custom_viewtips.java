package com.example.service_pro_blossom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_viewtips extends BaseAdapter {

    private Context context;
    ArrayList<String> tip1,tipdet1,exname1,extemail1,expplace1,tipid1;

    SharedPreferences sh;



    public Custom_viewtips(Context applicationContext, ArrayList<String> tip, ArrayList<String> tipdet, ArrayList<String> exname, ArrayList<String> extemail, ArrayList<String> expplace ,ArrayList<String> tipid) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.tip1=tip;
        this.tipdet1=tipdet;
        this.exname1=exname;
        this.extemail1=extemail;
        this.expplace1=expplace;
        this.tipid1=tipid;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        return tip1.size();
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
            gridView=inflator.inflate(R.layout.activity_custom_viewtips, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.usrname);
         TextView tvemail=(TextView)gridView.findViewById(R.id.usrmail);
         TextView tvplace=(TextView)gridView.findViewById(R.id.date);

         TextView tvtip=(TextView)gridView.findViewById(R.id.hii);

         TextView tvtx=(TextView)gridView.findViewById(R.id.hiii);



        tvname.setText(exname1.get(i));
        tvemail.setText(extemail1.get(i));
        tvplace.setText(expplace1.get(i));
        tvtip.setText(tip1.get(i));
        tvtx.setText(tipdet1.get(i));





        tvname.setTextColor(Color.BLACK);
        tvemail.setTextColor(Color.BLACK);
        tvplace.setTextColor(Color.BLACK);
        tvtip.setTextColor(Color.BLACK);
        tvtx.setTextColor(Color.BLACK);











        return gridView;


    }
}