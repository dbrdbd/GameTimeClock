package com.shaynamehta.gametimeclock;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Andrew on 9/17/16.
 */
public class AlarmCheckThread extends Thread {
    private ArrayList<TimePair> times;
    private Context context;
    public AlarmCheckThread(Context context) {
        this.times = new ArrayList<TimePair>();
        this.context = context;
    }

    public void addTime(TimePair time) {
        System.out.println(time.minute);
        times.add(time);
    }

    public void run() {
        try {
            while (true) {
                if(!times.isEmpty()) {
                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    for (TimePair time : times) {
                        if (time.hour == hour && time.minute == minute) {
                            Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                            Ringtone ringtoneSound = RingtoneManager.getRingtone(context, ringtoneUri);

                            if (ringtoneSound != null) {
                                ringtoneSound.play();
                            }
                        }
                    }
                }
                Thread.sleep(60 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
