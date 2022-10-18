package com.demo.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Demo_Log";
    private ViewGroup mContainer = null;
    private View mAnimTarget = null;
    private Button mAddView = null;
    private Button mRemoveView = null;
    private Button mStartAnim = null;
    private Button mAddThenStartAnimator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.container);
        mAddView = findViewById(R.id.addTargetView);
        mRemoveView = findViewById(R.id.removeTargetView);
        mStartAnim = findViewById(R.id.startAnimator);
        mAddThenStartAnimator = findViewById(R.id.addThenStartAnimator);
        mAddView.setOnClickListener(this);
        mRemoveView.setOnClickListener(this);
        mStartAnim.setOnClickListener(this);
        mAddThenStartAnimator.setOnClickListener(this);
    }

    private void addTargetView() {
        if (mAnimTarget == null) {
            mAnimTarget = new FrameLayout(this);
        }
        if (mContainer.indexOfChild(mAnimTarget) == -1) {
            FrameLayout.LayoutParams lp =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT);
            mAnimTarget.setBackgroundColor(Color.GREEN);
            mContainer.addView(mAnimTarget, lp);
            Log.i(TAG, "addTargetView: ");
        } else {
            Log.w(TAG, "mAnimTarget is already attached");
        }
    }

    private void startAnimator() {
        if (mContainer.indexOfChild(mAnimTarget) == -1) {
            Log.w(TAG, "startAnimator fail, target view removed ");
        }
        int centerX = mContainer.getWidth() / 2;
        int centerY = mContainer.getHeight() / 2;
        int radius = mContainer.getWidth() / 2;
        Animator animator = ViewAnimationUtils.createCircularReveal(mAnimTarget, centerX, centerY
                , radius, 0);
        animator.setDuration(2000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTargetView:
                addTargetView();
                break;
            case R.id.removeTargetView:
                if (mContainer.indexOfChild(mAnimTarget) != -1) {
                    mContainer.removeView(mAnimTarget);
                    Log.i(TAG, "removeTargetView: ");
                } else {
                    Log.w(TAG, "mAnimTarget is already removed");
                }
                break;
            case R.id.startAnimator:
                startAnimator();
                break;
            case R.id.addThenStartAnimator:
                addTargetView();
                startAnimator();
                break;
        }
    }
}