package com.onenation.oneworld.mahfuj75.crimeadmin.objectClass;

import android.content.Context;

import com.onenation.oneworld.mahfuj75.crimeadmin.R;

import java.util.Objects;

/**
 * Created by mahfu on 1/27/2017.
 */

public class SubjectSelector {

    public SubjectSelector() {
    }

    public String[] GetSubject(Context context, String subject)
    {
        String[] subjectList ;
        if(Objects.equals(subject, "সকল"))
        {
            subjectList = context.getResources().getStringArray(R.array.complain_subject);
        }

        else if(Objects.equals(subject, "দ্রব্য"))
        {
            subjectList = context.getResources().getStringArray(R.array.subject_drobbo);
        }

        else if(Objects.equals(subject, "পণ্য"))
        {
            subjectList = context.getResources().getStringArray(R.array.subject_ponno);;

        }

        else if(Objects.equals(subject, "পরিবেশ"))
        {
            subjectList = context.getResources().getStringArray(R.array.subject_porebesh);

        }
        else if(Objects.equals(subject, "অপরাধ"))
        {
            subjectList = context.getResources().getStringArray(R.array.subject_oporadh);


        }
        else if(Objects.equals(subject, "চাহিদা"))
        {
            subjectList = context.getResources().getStringArray(R.array.subject_chahida);

        }

        else{

            subjectList = context.getResources().getStringArray(R.array.complain_subject);
        }
        return subjectList;
    }
}
