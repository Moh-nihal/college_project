package com.example.service_pro_blossom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_viewfeedback extends BaseAdapter {

        private Context context;
        ArrayList<String> fid1,feed1,rtn1,date1;

        SharedPreferences sh;



        public custom_viewfeedback(Context applicationContext, ArrayList<String> fid, ArrayList<String> feed, ArrayList<String> rtn, ArrayList<String> date) {
            // TODO Auto-generated constructor stub
            this.context=applicationContext;
            this.fid1=fid;
            this.feed1=feed;
            this.rtn1=rtn;
            this.date1=date;
            sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
        }

        @Override
        public int getCount() {
            return feed1.size();
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
                gridView=inflator.inflate(R.layout.activity_custom_viewfeedback, null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView dat=(TextView)gridView.findViewById(R.id.date);
            TextView feedname=(TextView)gridView.findViewById(R.id.hii);
            RatingBar rating=(RatingBar)gridView.findViewById(R.id.ratingBar2);



            dat.setText(date1.get(i));
            feedname.setText(feed1.get(i));
            rating.setRating(Float.parseFloat(rtn1.get(i)));
            rating.setIsIndicator(true);






            dat.setTextColor(Color.BLACK);
            feedname.setTextColor(Color.BLACK);













            return gridView;
    }
}