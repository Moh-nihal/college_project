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

public class custom_viewexpert extends BaseAdapter {

    private Context context;
    ArrayList<String> eid1,ename1,eemail1,eplace1,ephone1;

    SharedPreferences sh;



    public custom_viewexpert(Context applicationContext, ArrayList<String> eid, ArrayList<String> ename, ArrayList<String> eemail, ArrayList<String> eplace, ArrayList<String> ephone) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.eid1=eid;
        this.ename1=ename;
        this.eemail1=eemail;
        this.eplace1=eplace;
        this.ephone1=ephone;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        return ename1.size();
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
            gridView=inflator.inflate(R.layout.activity_custom_viewexpert, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView name=(TextView)gridView.findViewById(R.id.textView10);
        TextView email=(TextView)gridView.findViewById(R.id.textView12);
        TextView place=(TextView)gridView.findViewById(R.id.textView18);
        TextView phone=(TextView)gridView.findViewById(R.id.textView22);
        Button bt1=(Button)gridView.findViewById(R.id.button7);





        name.setText(ename1.get(i));
        email.setText(eemail1.get(i));
        place.setText(eplace1.get(i));
        phone.setText(ephone1.get(i));
        bt1.setTag(eid1.get(i));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ED=sh.edit();
                ED.putString("expertid",view.getTag().toString());
                ED.commit();

                Intent ik = new Intent(context.getApplicationContext(), send_doubt.class);
//                ik.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ik);



            }
        });












        name.setTextColor(Color.BLACK);
        email.setTextColor(Color.BLACK);
        place.setTextColor(Color.BLACK);
        phone.setTextColor(Color.BLACK);













        return gridView;
    }
}