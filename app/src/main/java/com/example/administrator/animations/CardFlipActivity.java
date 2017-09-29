package com.example.administrator.animations;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class CardFlipActivity extends AppCompatActivity {
    boolean isFail = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container,new FrontFragment()).commit();
        }
    }

    public void click(View view){

        if (isFail){
            getFragmentManager().popBackStack();
            isFail = false;

            return;
        }

        isFail = true;

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.right_in,R.animator.right_out,R.animator.left_in,R.animator.left_out).replace(R.id.container,new BackFragment())
                .addToBackStack(null).commit();

    }



}
