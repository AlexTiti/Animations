package com.example.administrator.animations;

import android.animation.Animator;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.animations.zoom.ZoomActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private ProgressBar mProgressBar;
    private int dunation_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView.setVisibility(View.GONE);
        dunation_time = getResources().getInteger(android.R.integer.config_shortAnimTime);


    }

    private void addAnimation(){

        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(0);
        textView.animate().alpha(1f).setDuration(dunation_time);
        mProgressBar.animate().alpha(0).setDuration(dunation_time).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    public void click(View view){
        switch (view.getId()){
            case  R.id.button2:
                addAnimation();
                break;
            case  R.id.button:
                startActivity(new Intent(this,ViewPagerActivity.class));
                overridePendingTransition(R.anim.abc_slide_in_top,R.anim.abc_slide_out_bottom);
                break;
            case R.id.button3:
                startActivity(new Intent(this,CardFlipActivity.class));
                break;
            case  R.id.button4:
                startActivity(new Intent(this,ZoomActivity.class));
                break;
            case  R.id.button5:
               startActivity(new Intent(this,LayoutChangesActivity.class));

//                Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
//                startActivity(intent);
                break;
        }

    }
}
