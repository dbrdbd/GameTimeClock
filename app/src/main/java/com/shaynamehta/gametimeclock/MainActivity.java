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

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker1;
    private Button SaveButton;
    ArrayList times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker);


        SaveButton = (Button) findViewById(R.id.addbutton);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");*/
                timePicker1.clearFocus();

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                if( timePicker1.getCurrentHour() == hour && timePicker1.getCurrentMinute() == minute ) {
                    Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                    Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

                    if (ringtoneSound != null) {
                        ringtoneSound.play();
                    }
                }
            }
        });


    }
}
