# BadgedView

BadgedView is what [Plaid](https://github.com/nickbutcher/plaid) and [HuPu Sports](http://mobile.hupu.com/?_r=globalNav) uses to show a badge in an ImageView. 

BadgedView allows you show a badge into any View. The idea of this widget is from [BadgedImageview](https://github.com/yesidlazaro/BadgedImageview). I think the origin project has good idea but doesn't implement with good code. Therefor, for adding new feature and implementing it in a better way, I implement my own version of BadgedView, and it seems better than yesidlazaro's.

PreView：

![](example.png)

#Feature

## Implemented

- set badge's text
- set badge's gravity
- set badge's background color
- set BadgedView's length-width ratio
- customize you own badge and BadgedView
- show badges into any View u want
- set recbadge or tribadge's gravity

## Processing

- add any number of badges u want without implementing a subclass of BadgedView
- uses image as badge

#Usage

##Dependency

Open you Project's build.gradle, and add line like this：

```
dependencies {
    ...
    compile project('com.github.chaossss:BadgedView:1.0.0')
}
```

##Attr

| Attr | usage |
|---------|--------|
| app:badgeText | set badges's text |
| app:badgeTextSize | set badge's text size |
| app:badgeTextColor | set badge's text color |
| app:badgePadding | set badge's padding |
| app:badgeWidthRatio | set layout's width ratio |
| app:badgeHeightRatio | set layout's height ratio |
| app:badgeCornerRadius | set corner radius |
| app:recbadgeGravity(RecBadgedView) | set rectangle badge's position |
| app:tribadgeGravity(TriBadgedView) | set triangle badge's position |

##API

| API | usage |
|---------|--------|
| showBadge(boolean show) | show the badge |
| setBadgeText(String newText) | set badge's text |

##Customize BadgedView

1. Creates a subclass which extends from BadgedView，BadgedView completes attrs' initialize
2. Adds your specified attr scope in subclass
3. Overrides initBadge(Context context) and draw(Canvas canvas) to implement what u want

If customized badge needed：

4. Creates a subclass of BaseBadge
5. Adds your specified attr scope in subclass
6. Overrides initBadge() to finish drawing the badge you wanted. Please remember, u should draw your badge in the `Bitmap instance -> badge`, or u may meet some trouble

##Activity

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecBadgedView anyView;
    private RecBadgedView scaleView;
    private RecBadgedView regularView;

    private TriBadgedView triView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anyView = (RecBadgedView) findViewById(R.id.badge_any_view);
        scaleView = (RecBadgedView) findViewById(R.id.badge_scale_view);
        regularView = (RecBadgedView) findViewById(R.id.badge_regular_view);

        triView = (TriBadgedView) findViewById(R.id.badge_tri_view);

        anyView.setBadgeText("I'm badge");
        anyView.setOnClickListener(this);
        anyView.showBadge(true);

        scaleView.showBadge(true);
        regularView.showBadge(true);

        triView.showBadge(true);
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
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <com.chaos.customview.badgeview.view.RecBadgedView
            android:id="@+id/badge_scale_view"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:foreground="?selectableItemBackground"
            app:recbadgeGravity="bottom|right"
            app:badgeWidthRatio="2"
            app:badgeHeightRatio="4"
            app:badgeColor="@color/colorAccent"
            app:badgeText="@string/lab_gif" >
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="fitXY"
                android:src="@mipmap/gem"/>
        </com.chaos.customview.badgeview.view.RecBadgedView>

        <com.chaos.customview.badgeview.view.RecBadgedView
            android:id="@+id/badge_regular_view"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:foreground="?selectableItemBackground"
            app:badgeColor="@color/colorAccent"
            app:recbadgeGravity="bottom|left"
            app:badgeText="@string/lab_gif" >
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="fitXY"
                android:src="@mipmap/hebe"/>
        </com.chaos.customview.badgeview.view.RecBadgedView>

        <com.chaos.customview.badgeview.view.RecBadgedView
            android:id="@+id/badge_any_view"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:foreground="?selectableItemBackground"
            app:recbadgeGravity="top|right"
            app:badgeText="@string/lab_gif" >
            <Button
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="btn"/>
        </com.chaos.customview.badgeview.view.RecBadgedView>

        <com.chaos.customview.badgeview.view.TriBadgedView
            android:id="@+id/badge_tri_view"
            android:layout_width="200dp"
            android:layout_height="200dp"
            app:badgeText="@string/lab_gif">
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="fitXY"
                android:src="@mipmap/hebe"/>
        </com.chaos.customview.badgeview.view.TriBadgedView>
    </LinearLayout>
</ScrollView>
```
