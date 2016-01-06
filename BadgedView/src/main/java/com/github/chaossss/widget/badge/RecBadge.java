package com.github.chaossss.widget.badge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.DisplayMetrics;

/**
 * Badge that draws a rectangle
 * Created by chaos on 2015/11/26.
 */
public class RecBadge extends BaseBadge {
    private int width;
    private int height;

    public RecBadge(Context context, String badgeText, int badgeColor, int badgeTextColor) {
        super(context, badgeText, badgeColor, badgeTextColor);
    }

    @Override
    public void initBadge(){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float density = dm.density;
        float scaledDensity = dm.scaledDensity;

        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint
                .SUBPIXEL_TEXT_FLAG);
        textPaint.setTypeface(Typeface.create(TYPEFACE, TYPEFACE_STYLE));
        textPaint.setTextSize(textSize * scaledDensity);
        textPaint.setColor(badgeTextColor);

        float padding = badgePadding * density;
        float cornerRadius = badgeCornerRadius * density;

        Rect textBounds = new Rect();
        textPaint.getTextBounds(badgeText, 0, badgeText.length(), textBounds);
        height = (int) (padding + textBounds.height() + padding);
        width = (int) (padding + textBounds.width() + padding);

        badge = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        badge.setHasAlpha(true);

        Canvas canvas = new Canvas(badge);
        Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(badgeColor);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, width, height, cornerRadius, cornerRadius, backgroundPaint);
        }else {
            // TODO: 11/21/15 support cornerRadius for api < 21
            canvas.drawRect(0, 0, width, height, backgroundPaint);
        }

        // punch out the word ,leaving transparency
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawText(badgeText, padding, height - padding, textPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        if(badge == null) {
            initBadge();
        }
        canvas.drawBitmap(badge, getBounds().left, getBounds().top, paint);
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }
}
