package com.shaynamehta.gametimeclock;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private Button SaveButton;
    private AlarmCheckThread alarmCheckThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmCheckThread = new AlarmCheckThread(getApplicationContext());
        alarmCheckThread.start();
    }

    public void addTime(View v) {

        timePicker1 = (TimePicker) findViewById(R.id.timePicker);


//        SaveButton = (Button) findViewById(R.id.addbutton);
//
//        SaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//                /*DialogFragment newFragment = new TimePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "timePicker");*/
        timePicker1.clearFocus();

        TimePair newPair = new TimePair();
        newPair.hour = timePicker1.getCurrentHour();
        newPair.minute = timePicker1.getCurrentMinute();
        alarmCheckThread.addTime(newPair);
        //});


    }
}
