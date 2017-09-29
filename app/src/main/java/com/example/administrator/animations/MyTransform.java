package com.example.administrator.animations;

import android.support.v4.view.ViewPager;
import android.util.Log;
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

public class MyTransform  implements ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPH = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();

        if (position<-1){
            page.setAlpha(0);
        }else if (position<=1){
            float scaleRate = Math.max(MIN_SCALE,1- Math.abs(position));
            float widthMargin = width*(1-scaleRate)/2;
            float heightMargin = height*(1-scaleRate)/2;

            if (position<0){
                page.setTranslationX(widthMargin-heightMargin/2);
            }else{
                page.setTranslationX(-widthMargin+heightMargin/2);
            }

            page.setScaleX(scaleRate);
            page.setScaleY(scaleRate);
            page.setAlpha(MIN_ALPH + (scaleRate - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPH));



        }else{
            page.setAlpha(0);
        }

    }
}
