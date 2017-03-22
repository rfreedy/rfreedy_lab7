package com.cse40333.rfreedy.rob_freedy_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Team> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        /*Team florida_state = new Team("Saturday, February 11, 6:00pm", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "florida_state",
                "Florida State", "Seminoles", "(21-5)", "72-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team boston_college = new Team("Tuesday, February 14, 7:00pm", "Silvio O. Conte Forum, Chestnut Hill, Massachusetts", "boston_college",
                "Boston College", "Eagles", "(9-18)", "76-84", "Final", "Notre Dame", "Fighting Irish", "(21-7)", "notredamelogo", "Camera");
        Team north_carolina_state = new Team("Saturday, February 18, 12:00pm", "PNC Area, Raleigh, North Carolina", "north_carolina_state",
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
        teams.add(ncaa);*/

        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());
        String mDrawableName = "schedule";
        int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
        teams = myCsvFileReader.readCsvFile(resID);


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), teams);
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
        scheduleListView.setOnItemClickListener(clickListener);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public String gameSchedule() {
        StringBuilder str = new StringBuilder();

        for (Team s : teams)
        {
            str.append(s.getGameOpponent());
            str.append("\t");
            str.append(s.getGameLocation());
            str.append("\t");
            str.append(s.getDate());
            str.append("\n");
        }

        String string = str.toString();
        Log.d("string", string);
        return string;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            //code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("plain/text");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));
        }

        else if (res_id == R.id.sync) {
            // Snackbar with Try Again action
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);

            snackbar.setAction("Try Again", new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Snackbar.make(coordinatorLayout, "To be implemented in the upcoming labs", Snackbar.LENGTH_LONG).show();

                }

            });

            snackbar.show();

        }

        else if (res_id == R.id.settings) {
            // Floating Contextual Menu with options
            View view = (View) findViewById(R.id.scheduleListView);
            registerForContextMenu(view);
            openContextMenu(view);
            unregisterForContextMenu(view);

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //code to inflate floating_contextual_menu
        getMenuInflater().inflate(R.menu.floating_contextual_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.women) {
            //to be implemented later
        }
        if (item_id == R.id.men) {
            //to be implemented later
        }
        if (item_id == R.id.on_campus) {
            //to be implemented later
        }
        if (item_id == R.id.off_campus) {
            //to be implemented later
        }
        return false;
    }

}
