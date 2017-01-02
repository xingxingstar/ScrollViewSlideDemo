package com.example.zhuwojia.scrollviewslidedemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zhuwojia.scrollviewslidedemo.view.MyScrollView;


public class MainActivity extends AppCompatActivity implements MyScrollView.MyScrollListener,MyScrollView.WindowFocusChanged {

    private int topDistance, height;
    private TextView move, head, stop;
    private MyScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        head = (TextView) findViewById(R.id.head);
        move = (TextView) findViewById(R.id.move);
        stop = (TextView) findViewById(R.id.stop);
        myScrollView = (MyScrollView) findViewById(R.id.scv);
        myScrollView.setWindowFocusChanged(this);
        myScrollView.setMyScrollListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void sendDistanceY(int scrollY) {
        Log.d("scroll", "----------------------height:" + scrollY);
        if (scrollY >= topDistance - height) {  //如果滑动的距离大于或等于位置3到位置2的距离，那么说明内部绿色的顶部在位置2上面了，我们需要显示外部绿色栏了
            stop.setVisibility(View.VISIBLE);
            head.setBackgroundColor(getResources().getColor(android.R.color.white));
        } else {  //反之隐藏
            stop.setVisibility(View.GONE);
            head.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        }
    }

    @Override
    public void OnWindowFousChanged(boolean hasFocus) {
        if (hasFocus) {
            topDistance = move.getTop();  //获取位置3，即内部绿色栏的顶部到布局顶部的距离
            height = head.getMeasuredHeight();  //获取位置2，不就是搜索栏的高度么，啊哈哈哈，是不是很机智，当然你也可以用getButtom，一样的，看你自己
        }
    }
}
