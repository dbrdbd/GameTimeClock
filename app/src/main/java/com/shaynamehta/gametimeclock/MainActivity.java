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
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private Button SaveButton;
    //private AlarmCheckThread alarmCheckThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // spawns new thread to wait unil alarm
    public void addAlarm(View v) {


        timePicker1 = (TimePicker) findViewById(R.id.timePicker);
        timePicker1.clearFocus();

        int alarmHour = timePicker1.getCurrentHour();
        int alarmMinute = timePicker1.getCurrentMinute();
        int alarmTime = alarmHour * 3600000 + alarmMinute * 60000;

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int calendarTime = hour * 3600000 + minute * 60000;

        int timeDifference = 0;
        if(alarmTime > calendarTime) {
            timeDifference = alarmTime - calendarTime;
        } else {
            // if set to ring the next day
            timeDifference = 86400000 - (calendarTime - alarmTime);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

                if (ringtoneSound != null) {
                    ringtoneSound.play();
                }
            }
        }, timeDifference);
    }
}
