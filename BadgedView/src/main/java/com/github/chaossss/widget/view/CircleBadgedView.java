package com.github.chaossss.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;

import com.github.chaossss.widget.R;
import com.github.chaossss.widget.badge.CircleBadge;

/**
 * Created by chaos on 2016/1/6.
 */
public class CircleBadgedView extends BadgedView {
    private int circlebadgeGravity;
    private boolean badgeBoundsSet = false;

    public CircleBadgedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecBadgedView, 0, 0);
        circlebadgeGravity = a.getInt(R.styleable.CircleBadgedView_circlebadgeGravity, Gravity.TOP | Gravity
                .RIGHT);
        a.recycle();
    }

    @Override
    public void initBadge(Context context) {
        badge = new CircleBadge(context, badgeText, badgeColor, badgeTextColor);
        badge.setTextSize(badgeTextSize);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (drawBadge) {
            badge.draw(canvas);

            if (!badgeBoundsSet) {
                layoutBadge();
            }
        }
    }

    private void layoutBadge() {
        Rect badgeBounds = badge.getBounds();

        Gravity.apply(circlebadgeGravity,
                badge.getIntrinsicWidth(),
                badge.getIntrinsicHeight(),
                new Rect(0, 0, getWidth(), getHeight()),
                badgeBounds);
        badge.setBounds(badgeBounds);
        badgeBoundsSet = true;
    }
}
