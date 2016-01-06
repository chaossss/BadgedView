package com.github.chaossss.widget.badge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

/**
 * Class defines what attr Badge owns, and how badge finish its drawing
 * Created by chaos on 2015/11/26.
 */
public class BaseBadge extends Drawable {
    protected static final String TYPEFACE = "sans-serif-black";
    protected static final int TYPEFACE_STYLE = Typeface.NORMAL;

    protected int badgePadding;
    protected int badgeCornerRadius;

    protected int textSize;
    protected int badgeColor;
    protected int badgeTextColor;

    protected String badgeText;

    protected Paint paint;
    protected Bitmap badge;
    protected Context context;

    public BaseBadge(Context context, String badgeText, int badgeColor, int badgeTextColor) {
        this.badgeText = badgeText;

        this.badgeColor = badgeColor;
        this.badgeTextColor = badgeTextColor;

        paint = new Paint();
        this.context = context;
    }

    public void setText(String text) {
        this.badgeText = text;
    }

    public void setCornerRadius(int corner_radius) {
        this.badgeCornerRadius = corner_radius;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setPadding(int padding) {
        this.badgePadding = padding;
    }

    @Override
    public void draw(Canvas canvas) {
    }

    public void initBadge(){

    }

    public void reset() {
        if(badge != null) {
            badge.recycle();
            badge = null;
        }
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
