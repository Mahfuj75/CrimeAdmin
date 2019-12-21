package com.onenation.oneworld.mahfuj75.crimeadmin.viewHolder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.onenation.oneworld.mahfuj75.crimeadmin.R;


/**
 * Created by mahfu on 3/18/2017.
 */

public class MessageViewHolder  extends RecyclerView.ViewHolder{
    View mView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setMessage(String message,int i)
    {
        TextView textViewMe;
        TextView textViewHim;
        if(i==1)
        {
            textViewMe = (TextView) mView.findViewById(R.id.textViewChatMe);
            textViewHim = (TextView) mView.findViewById(R.id.textViewChatHim);
            textViewMe.setVisibility(View.VISIBLE);
            textViewHim.setVisibility(View.GONE);
            textViewMe.setText(message);
        }
        else if(i==2){
            textViewMe = (TextView) mView.findViewById(R.id.textViewChatMe);
            textViewHim = (TextView) mView.findViewById(R.id.textViewChatHim);
            textViewMe.setVisibility(View.GONE);
            textViewHim.setVisibility(View.VISIBLE);
            textViewHim.setText(message);
        }
    }

    public void setDateTime(String date, String time, int test)
    {

        TextView textViewDateTime =(TextView) mView.findViewById(R.id.messageDateTime);
        if(test==1)
        {

            textViewDateTime.setVisibility(View.VISIBLE);
            textViewDateTime.setText(date+" , "+ time);
        }
        else
        {
            textViewDateTime.setVisibility(View.GONE);
        }



    }
}
