package com.shufflegame.shuffleword;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvscore,tvWord;
    EditText etword;
    Dialog score;
    String []words={"APPLE","ADVENTURE","TECHNOLOGY","ANDROID","PLACEMENT","PEACE","PROXY","DETAINED"};
    int index=0,retry=0;
    Shuffle shuffle=new Shuffle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvscore=(TextView)findViewById(R.id.tvscore);
        tvWord=(TextView)findViewById(R.id.tvword);
        etword=(EditText)findViewById(R.id.newWord);
        tvWord.setText(shuffle.getShuffleWord(words[index]));
        score=new Dialog(MainActivity.this);
    }

    public void checkWord(View view)
    {
        String uword=etword.getText().toString().trim().toUpperCase();

        if(uword.equals(words[index]))
        {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            etword.setText("");
            index++;
            if(index==words.length)
            {
                getScore();
                index=0;
                MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.scoretone);
                mp.start();
            }
            tvWord.setText(shuffle.getShuffleWord(words[index]));
            tvscore.setText(""+index);
        }
        else
        {
            retry++;
            Toast.makeText(this, "Incorect!", Toast.LENGTH_SHORT).show();
            if(retry==words.length)
            {
                Vibrator vibe=(Vibrator)MainActivity.this.getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(500);

                AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
                ad.setCancelable(false);
                ad.setMessage("Too many retries!! X(");
                ad.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recreate();
                    }
                });
                ad.show();







            }
        }
    }

    public void getScore()
    {
        score.setCancelable(false);
        score.setContentView(R.layout.scoreboard);
        score.show();
        Window window=score.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView dscore=(TextView)score.findViewById(R.id.tvfinalscore);
        TextView retries=(TextView)score.findViewById(R.id.tvretries);
        Button button=(Button)score.findViewById(R.id.btnplay);
        dscore.setText(""+(index-retry));
        retries.setText(""+retry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score.dismiss();
                recreate();
            }
        });
    }










}
