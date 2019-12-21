package com.onenation.oneworld.mahfuj75.crimeadmin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onenation.oneworld.mahfuj75.crimeadmin.objectClass.Message;
import com.onenation.oneworld.mahfuj75.crimeadmin.viewHolder.MessageViewHolder;


/**
 * Created by mahfu on 12/17/2016.
 */

public class ChatPopFragment extends DialogFragment {

    String complainKey;
    RecyclerView recyclerView;
    static ChatPopFragment f1;
    FirebaseAuth mauth;
    String uid;

    public static ChatPopFragment newInstance() {
        f1 = new ChatPopFragment();
        f1.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog);
        return f1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background


        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.popup_comment_layout, container, false);

        complainKey = f1.getTag();
        mauth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) v.findViewById(R.id.chatPopRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        String uid = mauth.getCurrentUser().getUid();
        ChatPop(uid);


        // Set up a click listener to dismiss the popup if they click outside
        // of the background view
        v.findViewById(R.id.popup_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    private void ChatPop(final String userUid)
    {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Complain").child(complainKey).child("message");

        FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.chat_layout,
                MessageViewHolder.class,
                mRef
        ) {

            @Override
            public int getItemCount() {

                return super.getItemCount();
            }

            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {

                String chatUid = model.getUid().trim();

                if(chatUid.equals(userUid))
                {
                    viewHolder.setMessage(model.getMessage(),1);

                    if(getItemCount()-1==position)
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),1);
                    }
                    else if((position%3)==0 && position!= 0)
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),1);
                    }
                    else
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),0);
                    }
                }
                else{
                    viewHolder.setMessage(model.getMessage(),2);

                    if(getItemCount()-1==position)
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),1);
                    }
                    else if((position%3)==0 && position!= 0)
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),1);
                    }
                    else
                    {
                        viewHolder.setDateTime(model.getMessageDate(),model.getMessageTime(),0);
                    }
                }



            }
        };
        //Toast.makeText(getApplicationContext(),firebaseRecyclerAdapter.getItem(1).toString(),Toast.LENGTH_LONG).show();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}