package com.example.blooddiamond;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameOver {

    private Context context;
    private MainActivity mainActivity;
    private GameLoop gameLoop;
    private DatabaseReference myRef;
    private boolean GotInfo = true;
    private boolean PutInfo = true;
    private List<String> LeaderBoard = new ArrayList<>();
    private List<String> PreviousLeaderBoard = new ArrayList<>();
    private Integer[] s;

    public GameOver(Context context) {
        this.context = context;
    }

    public void firebase (int Counter) {
        LeaderBoard.addAll(PreviousLeaderBoard);
        LeaderBoard.add(String.valueOf(Counter));
        s = new Integer[LeaderBoard.size()];
        for (int i = 0; i<LeaderBoard.size(); i++) {
            s[i] = Integer.valueOf(LeaderBoard.get(i));
        }
        Arrays.sort(s, Collections.reverseOrder());
        while (PutInfo) {
            myRef.setValue(LeaderBoard);
            Log.d("Test", "Test");
            PutInfo = false;
        }
    }

    public void InitializeFirebase() {
        myRef = FirebaseDatabase.getInstance().getReference().child("Counter");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        String counter = dss.getValue(String.class);
                        Log.d("Test", counter);
                        PreviousLeaderBoard.add(counter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }
    public void draw(Canvas canvas) {
        String text = "Game Over";
        float x = 600;
        float y = 175;
        float LBx = 900;
        float LBy = 60;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);
        canvas.drawText(text,x,y,paint);
        for (int i = 0; i<LeaderBoard.size(); i++) {
            if (i == 10) {
                return;
            }
            textSize = 50;
            paint.setTextSize(textSize);
            canvas.drawText((i+1)+".",LBx,((i+1)*LBy)+250,paint);
            canvas.drawText(String.valueOf(s[i]),LBx+100,((i+1)*LBy)+250,paint);
        }
    }

    public void restart() {
        Intent mStartActivity = new Intent(context, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, mPendingIntent);
        System.exit(0);
    }
}
