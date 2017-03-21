package com.cse40333.rfreedy.rob_freedy_application;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        //specific game info
        final ArrayList <String []> arr = new ArrayList<String []>();
        arr.add( new String []{"Saturday, February 11, 6:00pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "florida_state",
                "Florida State", "Seminoles", "(21-5)", "72-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Tuesday, February 14, 7:00pm", "Silvio O. Conte Forum, Chestnut Hill, Massachusetts", "boston_college",
                "Boston College", "Eagles", "(9-18)", "76-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Saturday, February 18, 12:00pm", "PNC Area, Raleigh, North Carolina", "north_carolina_state",
                "North Carolina State", "Wolfpack", "(14-14)", "72-81", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Sunday, February 26, 6:30pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "georgia_tech",
                "Georgia Tech", "Yellow Jackets", "(15-11)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Wednesday, March 1, 8:00pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "boston_college",
                "Boston College", "Eagles", "(9-18)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Saturday, March 4, 2:00pm", "KFC Yum! Center, Louisville, Kentucky", "louisville_pic",
                "Louisville", "Cardinals", "(22-5)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Tuesday, March 7, 12:00pm", "Barclay's Center, New York, New York", "acc",
                "ACC Tournament", "N/A", "N/A", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});
        arr.add( new String []{"Thursday, March 16", "N/A", "ncaa",
                "NCAA Tournament", "N/A", "N/A", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera"});

        //add teams to array
        /*al.add(new String[]{"florida_state", "Florida State", "Feb 11"});
        al.add(new String[]{"boston_college", "Boston College", "Feb 14"});
        al.add(new String[]{"north_carolina_state", "North Carolina State", "Feb 18"});
        al.add(new String[]{"georgia_tech", "Georgia Tech", "Feb 26"});
        al.add(new String[]{"boston_college", "Boston College", "March 1"});
        al.add(new String[]{"louisville_pic", "Louisville", "March 4"});
        al.add(new String[]{"acc", "ACC Tournament", "March 7"});
        al.add(new String[]{"ncaa", "NCAA Tournament", "March 16"});*/

        final ArrayList<Team> teams = new ArrayList<>();
        Team florida_state = new Team("Saturday, February 11, 6:00pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "florida_state",
                "Florida State", "Seminoles", "(21-5)", "72-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team boston_college = new Team("Tuesday, February 14, 7:00pm", "Silvio O. Conte Forum, Chestnut Hill, Massachusetts", "boston_college",
                "Boston College", "Eagles", "(9-18)", "76-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team north_carolina_state = new Team ("Saturday, February 18, 12:00pm", "PNC Area, Raleigh, North Carolina", "north_carolina_state",
                "North Carolina State", "Wolfpack", "(14-14)", "72-81", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team georgia_tech = new Team("Sunday, February 26, 6:30pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "georgia_tech",
                "Georgia Tech", "Yellow Jackets", "(15-11)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team boston_college_1 = new Team("Wednesday, March 1, 8:00pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "boston_college",
                "Boston College", "Eagles", "(9-18)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team louisville = new Team("Saturday, March 4, 2:00pm", "KFC Yum! Center, Louisville, Kentucky", "louisville_pic",
                "Louisville", "Cardinals", "(22-5)", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team acc = new Team("Tuesday, March 7, 12:00pm", "Barclay's Center, New York, New York", "acc",
                "ACC Tournament", "N/A", "N/A", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team ncaa = new Team("Thursday, March 16", "N/A", "ncaa",
                "NCAA Tournament", "N/A", "N/A", "@", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        teams.add(florida_state);
        teams.add(boston_college);
        teams.add(north_carolina_state);
        teams.add(georgia_tech);
        teams.add(boston_college_1);
        teams.add(louisville);
        teams.add(acc);
        teams.add(ncaa);

        MyCsvFileReader reader = new MyCsvFileReader(getApplicationContext());
        String mDrawableName = "schedule";
        int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
        Log.d("tag", "resID="+resID);
        final ArrayList <String []> al = reader.readCsvFile(resID);


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), al);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(MainActivity.this, DetailActivity.class);
                mIntent.putExtra("team", teams.get(position));
                startActivity(mIntent);
            }
        };
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener (clickListener);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {*/
            /*code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
            shareIntent.putExtra("android.content.Intent.EXTRA_TEXT", gameSchedule());
            startActivity(Intent.createChooser(shareIntent), "Share via"); */
        }

        /*else if (res_id == R.id.sync) {
            // Snackbar with Try Again action
        }

        else if (res_id == R.id.settings) {
            // Floating Contextual Menu with options

        }
        return true;
    }*/
