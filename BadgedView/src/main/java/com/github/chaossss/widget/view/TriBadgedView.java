package com.github.chaossss.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.github.chaossss.widget.R;
import com.github.chaossss.widget.badge.TriBadge;

/**
 * An extension to {@link BadgedView} which has a Triangle Badge.
 * Created by chaos on 2015/11/27.
 */
public class TriBadgedView extends BadgedView {
    private int tribadgeGravity;

    public TriBadgedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TriBadgedView, 0, 0);
        tribadgeGravity = a.getInt(R.styleable.TriBadgedView_TribadgeGravity, TriBadge.BOTTOMRIGHT);
        a.recycle();
    }

    @Override
    public void initBadge(Context context) {
        badge = new TriBadge(context, badgeText, badgeColor, badgeTextColor);
        badge.setPadding(badgePadding);
        badge.setTextSize(badgeTextSize);
        badge.setCornerRadius(badgeCornerRadius);
    }

    @Override
    public void draw(Canvas canvas) {
        ((TriBadge)badge).setTribadgeGravity(tribadgeGravity);
        ((TriBadge)badge).setRootViewParam(getWidth(), getHeight());
        super.draw(canvas);
    }
}
