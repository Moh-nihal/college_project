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

public class custom_view_doubt extends BaseAdapter {

        private Context context;
        ArrayList<String> did1,dob1,reply1,date1;

        SharedPreferences sh;



    public custom_view_doubt(Context applicationContext, ArrayList<String> did, ArrayList<String> dob, ArrayList<String> reply, ArrayList<String> date) {
            // TODO Auto-generated constructor stub
            this.context=applicationContext;
            this.did1=did;
            this.dob1=dob;
            this.reply1=reply;
            this.date1=date;
            sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
        }

        @Override
        public int getCount() {
            return did1.size();
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
                gridView=inflator.inflate(R.layout.activity_custom_view_doubt, null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView dat=(TextView)gridView.findViewById(R.id.date);
            TextView dobname=(TextView)gridView.findViewById(R.id.hii);
            TextView replyy=(TextView)gridView.findViewById(R.id.hiii);



            dat.setText(date1.get(i));
            dobname.setText(dob1.get(i));
            replyy.setText(reply1.get(i));






            dat.setTextColor(Color.BLACK);
            dobname.setTextColor(Color.BLACK);
            replyy.setTextColor(Color.BLACK);











            return gridView;
    }
}