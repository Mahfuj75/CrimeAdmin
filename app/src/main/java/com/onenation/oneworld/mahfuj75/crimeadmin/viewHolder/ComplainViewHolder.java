package com.onenation.oneworld.mahfuj75.crimeadmin.viewHolder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.onenation.oneworld.mahfuj75.crimeadmin.R;

/**
 * Created by mahfu on 1/29/2017.
 */

public class ComplainViewHolder extends RecyclerView.ViewHolder {

    View mView;
    CardView cv;

    public ComplainViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
        cv = (CardView) itemView.findViewById(R.id.complain_card_view);
        cv.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    public void setSubject(String complainSubject)
    {
        TextView textViewSubject = (TextView) mView.findViewById(R.id.textView_complain_subject);

        textViewSubject.setText(complainSubject);
    }
    public void setIncidentDate(String incidentDate)
    {
        TextView textViewIncidentDate = (TextView) mView.findViewById(R.id.textView_incident_date);

        textViewIncidentDate.setText(incidentDate);
    }

    public void setIncidentLocation(String incidentSpotSubLocation)
    {
        TextView textViewIncidentLocation = (TextView) mView.findViewById(R.id.textView_incident_location);
        textViewIncidentLocation.setText(incidentSpotSubLocation);
     }

    public void setPostDate (String postDate)
    {
        TextView textViewComplainDate = (TextView) mView.findViewById(R.id.textView_complain_date);
        textViewComplainDate.setText(postDate);
    }
    public void setPostTime (String postTime)
    {
        TextView textViewComplainTime = (TextView) mView.findViewById(R.id.textView_complain_time);
        textViewComplainTime.setText(postTime);
    }
}
