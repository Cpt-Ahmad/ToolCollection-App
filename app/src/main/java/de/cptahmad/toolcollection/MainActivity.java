package de.cptahmad.toolcollection;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.cptahmad.toolcollection.utils.Counter;
import de.cptahmad.toolcollection.utils.SimpleTimer;

public class MainActivity extends AppCompatActivity
{
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metronom);

        final Button start = findViewById(R.id.startButton);
        final EditText textField = findViewById(R.id.bpmTextField);

        // Debug
        final TextView    textView        = findViewById(R.id.testTextField);
        final SimpleTimer runTimer        = new SimpleTimer();
        final SimpleTimer correctionTimer = new SimpleTimer();
        final Counter counter = new Counter();

        final MediaPlayer beat = MediaPlayer.create(this, R.raw.click);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable()
        {
            double value = 2;

            @Override
            public void run()
            {
                long target = 60_000 / Long.parseLong(textField.getText().toString());
                long errorFromTarget = runTimer.time() % target;
                if(Math.abs(errorFromTarget) > 200) errorFromTarget = 0;

                correctionTimer.reset();
                counter.count();

                beat.start();
                handler.postDelayed(this, target - errorFromTarget);
                textView.setText(String.format("Error[%d]; Time[%d]; Count[%d]", errorFromTarget, runTimer.time(), counter.getCounter()));
            }
        };

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Button button = (Button) v;
                isPlaying = !isPlaying;

                if(isPlaying)
                {
                    button.setText(R.string.stop);
                    handler.post(runnable);
                    runTimer.reset();
                    correctionTimer.reset();
                    counter.reset();
                } else
                {
                    button.setText(R.string.start);
                    handler.removeCallbacks(runnable);
                }
            }
        });
    }
}
