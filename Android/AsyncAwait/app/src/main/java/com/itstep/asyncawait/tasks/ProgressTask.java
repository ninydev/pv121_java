package com.itstep.asyncawait.tasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itstep.asyncawait.MainActivity;

import java.util.UUID;

public class ProgressTask extends AsyncTask<Void, Integer, Void> {

    final MainActivity activity;
    final ProgressBar indicatorBar;
    final TextView statusView;

    int[] integers=null;

    final UUID id;
    public ProgressTask(
            MainActivity activity,
            ProgressBar indicatorBar,
            TextView statusView,
            UUID id
    ) {
        this.activity = activity;
        this.indicatorBar = indicatorBar;
        this.statusView = statusView;
        this.id = id;

        integers = new int[100];
        for(int i=0;i<100;i++) {
            integers[i] = i + 1;
        }
    }

    @Override
    protected Void doInBackground(Void... unused) {
        for (int i = 0; i<integers.length;i++) {

            publishProgress(i);
            SystemClock.sleep(40);
        }
        return(null);
    }
    @Override
    protected void onProgressUpdate(Integer... items) {
        indicatorBar.setProgress(items[0]+1);
        statusView.setText("Статус: " + String.valueOf(items[0]+1));
    }
    @Override
    protected void onPostExecute(Void unused) {
        activity.toast( "Задача завершена");
    }
}
