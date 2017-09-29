package com.example.administrator.animations;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * <pre>
 *
 *   author   :   Alex
 *   e_mail   :   18238818283@sina.cn
 *   time     :   2017/09/25
 *   desc     :
 *   version  :   V 1.0.5
 */

public class MyTransform_simple implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();

        if (position<-1){
            page.setAlpha(0);
        }else if (position <= 0){
            page.setAlpha(1);
            page.setScaleX(1);
            page.setScaleY(1);
            page.setTranslationX(0);
        }else if (position <= 1){
            page.setAlpha(1- position);
            page.setTranslationX(width*-position);

            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

        }else{
            page.setAlpha(0);
        }

    }
}
