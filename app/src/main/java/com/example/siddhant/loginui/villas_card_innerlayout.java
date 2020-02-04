package com.example.siddhant.loginui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderView;

public class villas_card_innerlayout extends AppCompatActivity {
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villas_card_innerlayout);

        sliderView=findViewById(R.id.imageSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderView.setScrollTimeInSec(2);

        setSliderViews();
    }
    private void setSliderViews(){

    }
}
