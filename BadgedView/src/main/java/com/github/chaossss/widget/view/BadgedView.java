package com.github.chaossss.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import com.github.chaossss.widget.R;
import com.github.chaossss.widget.badge.BaseBadge;

/**
 * An extension to {@link ViewGroup} which has a Badge.
 * Created by chaos on 2015/11/27.
 */
public class BadgedView extends ViewGroup {
    protected BaseBadge badge;

    protected boolean drawBadge = false;

    protected int badgeColor;
    protected int widthRatio;
    protected int heightRatio;
    protected int badgePadding;

    protected int badgeTextSize;
    protected int badgeTextColor;
    protected int badgeCornerRadius;

    protected String badgeText;

    public BadgedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BadgedImageView, 0, 0);

        badgePadding = a.getDimensionPixelSize(R.styleable.BadgedImageView_badgePadding, 4);
        badgeTextSize = a.getDimensionPixelSize(R.styleable.BadgedImageView_badgeTextSize, 12);
        badgeCornerRadius = a.getDimensionPixelSize(R.styleable.BadgedImageView_badgeCornerRadius, 2);

        badgeText = a.getString(R.styleable.BadgedImageView_badgeText);

        badgeColor = a.getColor(R.styleable.BadgedImageView_badgeColor, ContextCompat.getColor(context, R.color.default_badge_color));
        badgeTextColor = a.getColor(R.styleable.BadgedImageView_badgeTextColor, ContextCompat.getColor(context, R.color.default_badge_text_color));

        widthRatio = a.getInt(R.styleable.BadgedImageView_badgeWidthRatio, 1);
        heightRatio = a.getInt(R.styleable.BadgedImageView_badgeHeightRatio, 1);

        initBadge(context);

        a.recycle();
        setWillNotDraw(false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    public void initBadge(Context context) {
        badge = new BaseBadge(context, badgeText, badgeColor, badgeTextColor);
        badge.setPadding(badgePadding);
        badge.setTextSize(badgeTextSize);
        badge.setCornerRadius(badgeCornerRadius);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getScaleWidth(widthMeasureSpec);
        int height = getScaleHeight(heightMeasureSpec);
        super.onMeasure(width, height);
        measureChildren(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            child.layout(lp.width, lp.height, lp.width + child.getMeasuredWidth(), lp.height + child.getMeasuredHeight());
        }
    }

    private int getScaleWidth(int widthMeasureSpec){
        return !isScaleWidth() ? widthMeasureSpec : MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) * widthRatio / heightRatio,
                MeasureSpec.EXACTLY);
    }

    private int getScaleHeight(int heightMeasureSpec){
        return isScaleWidth() ? heightMeasureSpec : MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) * heightRatio / widthRatio,
                MeasureSpec.EXACTLY);
    }

    private boolean isScaleWidth() {
        return widthRatio / heightRatio < 1;
    }

    public void showBadge(boolean show) {
        drawBadge = show;
        invalidate();
    }

    public void setBadgeText(String newText) {
        badge.setText(newText);
        badge.reset();
        invalidate();
    }

    public boolean isBadgeVisible() {
        return drawBadge;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (drawBadge) {
            badge.draw(canvas);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
