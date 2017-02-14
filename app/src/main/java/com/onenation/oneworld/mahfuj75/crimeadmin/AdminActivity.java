package com.onenation.oneworld.mahfuj75.crimeadmin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onenation.oneworld.mahfuj75.crimeadmin.objectClass.Complain;
import com.onenation.oneworld.mahfuj75.crimeadmin.objectClass.SubjectSelector;
import com.onenation.oneworld.mahfuj75.crimeadmin.viewHolder.ComplainViewHolder;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinnerShortSubject;
    private Spinner spinnerSubject;

    private TextView textViewAdmin;

    private DatabaseReference mRef;

    private String [] shortSubjectList;
    private String [] subjectList;



    private SubjectSelector subjectSelector = new SubjectSelector();


    private FirebaseRecyclerAdapter<Complain,ComplainViewHolder> firebaseRecycleComplainAdapter;
    
    private RecyclerView complainRecyclerView;

    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///////////////start/////////////////////////

        mAuth = FirebaseAuth.getInstance();
        spinnerShortSubject = (Spinner) findViewById(R.id.spinnerShortSubject);
        spinnerSubject = (Spinner) findViewById(R.id.spinnerSubject);

        textViewAdmin = (TextView) findViewById(R.id.textView_admin);

        shortSubjectList = getResources().getStringArray(R.array.complain_subject_short);
        complainRecyclerView =(RecyclerView) findViewById(R.id.complainRecyclerView);

        complainRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        subjectList = getResources().getStringArray(R.array.complain_subject);



        mRef = FirebaseDatabase.getInstance().getReference().child("Complain");
        mRef.keepSynced(true);

        spinnerWork();






        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    textViewAdmin.setVisibility(View.VISIBLE);
                } else {
                    textViewAdmin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }


    @Override
    protected void onStart() {
        super.onStart();


        firebaseRecycleComplainAdapter = new FirebaseRecyclerAdapter<Complain, ComplainViewHolder>(
                Complain.class,
                R.layout.complain_card_view,
                ComplainViewHolder.class,
                mRef

        ) {
            @Override
            protected void populateViewHolder(ComplainViewHolder viewHolder, Complain model, int position) {
                viewHolder.setPostDate(model.getPostDate());
                viewHolder.setPostTime(model.getPostTime());
                viewHolder.setSubject(model.getComplainSubject());
                viewHolder.setIncidentDate(model.getIncidentDate());
                viewHolder.setIncidentLocation(model.getIncidentSpotSubLocation());
            }
        };


        complainRecyclerView.setAdapter(firebaseRecycleComplainAdapter);

    }

    private void spinnerWork() {

        ArrayAdapter<String> adapterShortSubject = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, shortSubjectList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 0) { // we're on an even row
                    view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                return view;

            }

        };
        spinnerShortSubject.setAdapter(adapterShortSubject);


        spinnerShortSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shortSubject = spinnerShortSubject.getSelectedItem().toString().trim();

                subjectList = subjectSelector.GetSubject(getApplicationContext(),shortSubject);

                ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, subjectList) {

                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        if (position % 2 == 0) { // we're on an even row
                            view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        } else {
                            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        }
                        return view;

                    }

                };
                spinnerSubject.setAdapter(subjectAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterSubject = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, subjectList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 0) { // we're on an even row
                    view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                return view;

            }

        };

        spinnerSubject.setAdapter(adapterSubject);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_log_out) {
            mAuth.signOut();
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
