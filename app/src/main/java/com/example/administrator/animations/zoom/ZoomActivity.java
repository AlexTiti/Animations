package com.example.administrator.animations.zoom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.administrator.animations.R;

public class ZoomActivity extends AppCompatActivity {

    private Animator mAnimator;
    private int shortAnimatorTime;
    private ImageButton imageButton ,imageButton2;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
         imageView = (ImageView) findViewById(R.id.imageView4);

        shortAnimatorTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    public  void click(View view){

        switch (view.getId()){
            case R.id.imageButton:
                zoomImageByResouce(view,R.drawable.beauty);
                break;
            case R.id.imageButton2:
                zoomImageByResouce(view,R.drawable.a);
                break;
        }

    }

    public void zoomImageByResouce( final View view,int id){
        if (mAnimator != null){
            mAnimator.cancel();
        }
        imageView.setImageResource(id);
        final Rect startRect = new Rect();
        final Rect endREct = new Rect();
        final Point point = new Point();
        view.getGlobalVisibleRect(startRect);
        findViewById(R.id.contair).getGlobalVisibleRect(endREct,point);
        startRect.offset(-point.x,-point.y);
        endREct.offset(-point.x,-point.y);

        float startScale ;
        if (endREct.width()/endREct.height() > startRect.width()/startRect.height()){
            startScale = startRect.height()/endREct.height();
            float startWidth = startScale*endREct.width();
            float deltWidth = (startWidth-startRect.width())/2;
            startRect.left -= deltWidth;
            startRect.right += deltWidth;

        }else{
            startScale = startRect.width()/endREct.width();
            float startHeight =  startScale*endREct.height();
            float deltheight = (startHeight-startRect.height())/2;
            startRect.top -= deltheight;
            startRect.bottom += deltheight;
        }

        view.setAlpha(0);
        imageView.setVisibility(View.VISIBLE);
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(imageView,View.X,startRect.left,endREct.left))
                .with(ObjectAnimator.ofFloat(imageView,View.Y,startRect.top,endREct.top))
                .with(ObjectAnimator.ofFloat(imageView,View.SCALE_X,startScale,1.0f))
                .with(ObjectAnimator.ofFloat(imageView,View.SCALE_Y,startScale,1.0f));
        set.setDuration(shortAnimatorTime);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                mAnimator = null;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimator = null;
            }
        });
        set.start();
        mAnimator = set;
        final float scale_final  = startScale;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnimator != null){
                    mAnimator.cancel();
                }
                AnimatorSet set1 = new AnimatorSet();
                set1.play(ObjectAnimator.ofFloat(imageView,View.X,startRect.left))
                        .with(ObjectAnimator.ofFloat(imageView,View.Y,startRect.top))
                        .with(ObjectAnimator.ofFloat(imageView,View.SCALE_X,scale_final))
                        .with(ObjectAnimator.ofFloat(imageView,View.SCALE_Y,scale_final));
                set1.setDuration(shortAnimatorTime);
                set1.setInterpolator(new DecelerateInterpolator());
                set1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        view.setAlpha(1.0f);
                        imageView.setVisibility(View.GONE);
                        mAnimator = null;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setAlpha(1.0f);
                        imageView.setVisibility(View.GONE);
                        mAnimator = null;

                    }
                });

                set1.start();
                mAnimator = set1;
            }
        });














    }
}
