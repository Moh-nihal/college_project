package com.example.service_pro_blossom;

import android.content.ComponentName;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class custom_viewservice extends BaseAdapter {


        private Context context;
        ArrayList<String> sid1, cenphoto1,cenname1,cenemail1,cencontact1,cenplace1,servname1,servdesc1;

        SharedPreferences sh;



        public custom_viewservice(Context applicationContext, ArrayList<String> sid,ArrayList<String> cenphoto, ArrayList<String> cenname, ArrayList<String> cenemail, ArrayList<String> cencontact, ArrayList<String> cenplace ) {
            // TODO Auto-generated constructor stub
            this.context=applicationContext;
            this.sid1=sid;
            this.cenphoto1=cenphoto;
            this.cenname1=cenname;
            this.cenemail1=cenemail;
            this.cencontact1=cencontact;
            this.cenplace1=cenplace;
//            this.servname1=servname;
//            this.servdesc1=servdesc;
            sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
        }

        @Override
        public int getCount() {
            return sid1.size();
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
                gridView=inflator.inflate(R.layout.activity_custom_viewservice, null);

            }
            else
            {
                gridView=(View)view;

            }
            ImageView photo=(ImageView) gridView.findViewById(R.id.imageView);
            TextView name=(TextView)gridView.findViewById(R.id.textView4);
            TextView email=(TextView)gridView.findViewById(R.id.textView5);
            TextView contact=(TextView)gridView.findViewById(R.id.textView6);
            TextView place=(TextView)gridView.findViewById(R.id.textView7);
//            TextView servicename=(TextView)gridView.findViewById(R.id.textView15);
//            TextView description=(TextView)gridView.findViewById(R.id.textView17);
            ImageView image=(ImageView) gridView.findViewById(R.id.imageView2);
            image.setTag(sid1.get(i));
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor edp = sh.edit();
                    edp.putString("service_centerid", view.getTag().toString());
                    edp.commit();

                    Intent req= new Intent(context.getApplicationContext(),View_services.class);
                    context.startActivity(req);

                }
            });


            ImageView image3=(ImageView) gridView.findViewById(R.id.imageView3);
            image3.setTag(cencontact1.get(i));
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    SharedPreferences.Editor edp = sh.edit();
//                    edp.putString("service_centerid", view.getTag().toString());
//                    edp.commit();
//
//                    Intent req= new Intent(context.getApplicationContext(),View_services.class);
//                    context.startActivity(req);




                    try {
                        // Open WhatsApp with the specified phone number
                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                        sendIntent.putExtra("jid", view.getTag() + "@s.whatsapp.net");
                        context.startActivity(sendIntent);
                    } catch (Exception e) {
                        // If WhatsApp is not installed or an error occurs
                        Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
            });







            name.setText(cenname1.get(i));
            email.setText(cenemail1.get(i));
            contact.setText(cencontact1.get(i));
            place.setText(cenplace1.get(i));
//            servicename.setText(servname1.get(i));
//            description.setText(servdesc1.get(i));

            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
            String ip=sh.getString("ip","");

            String url="http://" + ip + ":5000"+cenphoto1.get(i);


            Picasso.with(context).load(url). into(photo);










            name.setTextColor(Color.BLACK);
            email.setTextColor(Color.BLACK);
            contact.setTextColor(Color.BLACK);
            place.setTextColor(Color.BLACK);
//            servicename.setTextColor(Color.BLACK);
//            description.setTextColor(Color.BLACK);













            return gridView;


    }
}