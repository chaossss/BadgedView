package com.github.chaossss.badgedview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chaossss.widget.view.CircleBadgedView;
import com.github.chaossss.widget.view.RecBadgedView;
import com.github.chaossss.widget.view.TriBadgedView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecBadgedView anyView;
    private RecBadgedView scaleView;
    private RecBadgedView regularView;

    private TriBadgedView triView;

    private CircleBadgedView circleBadgedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anyView = (RecBadgedView) findViewById(R.id.badge_any_view);
        scaleView = (RecBadgedView) findViewById(R.id.badge_scale_view);
        regularView = (RecBadgedView) findViewById(R.id.badge_regular_view);
        circleBadgedView = (CircleBadgedView) findViewById(R.id.badge_circle_view);

        triView = (TriBadgedView) findViewById(R.id.badge_tri_view);

        anyView.setBadgeText("I'm badge");
        anyView.setOnClickListener(this);
        anyView.showBadge(true);

        scaleView.showBadge(true);
        regularView.showBadge(true);

        triView.showBadge(true);
        circleBadgedView.showBadge(true);
    }

    @Override
    public void onClick(View v) {
        if(anyView.isBadgeVisible()){
            anyView.showBadge(false);
        } else {
            anyView.showBadge(true);
        }
    }
}
