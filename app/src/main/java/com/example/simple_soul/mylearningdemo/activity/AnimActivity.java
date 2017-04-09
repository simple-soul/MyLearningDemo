package com.example.simple_soul.mylearningdemo.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.simple_soul.mylearningdemo.R;

/**
 * Created by simple_soul on 2017/4/8.
 */

public class AnimActivity extends BaseActivity implements View.OnClickListener
{
    private ImageView image;
    private Button rotation, alpha, translation, scale;

    @Override
    public View initView()
    {
        View view = View.inflate(this, R.layout.activity_anim, null);

        image = (ImageView) view.findViewById(R.id.anim_image);
        rotation = (Button) view.findViewById(R.id.anim_btn_rotation);
        alpha = (Button) view.findViewById(R.id.anim_btn_alpha);
        translation = (Button) view.findViewById(R.id.anim_btn_translation);
        scale = (Button) view.findViewById(R.id.anim_btn_scale);

        rotation.setOnClickListener(this);
        alpha.setOnClickListener(this);
        translation.setOnClickListener(this);
        scale.setOnClickListener(this);

        return view;
    }

    @Override
    public void initData()
    {
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.anim_btn_rotation:
                ObjectAnimator rotateAnim = new ObjectAnimator();
                rotateAnim.setTarget(image);
                rotateAnim.setPropertyName("rotationX");
                rotateAnim.setDuration(1000);
                rotateAnim.setFloatValues(0, 360);
                rotateAnim.setInterpolator(new OvershootInterpolator());
                rotateAnim.start();
                break;

            case R.id.anim_btn_alpha:
                ObjectAnimator alphaAnim = new ObjectAnimator();
                alphaAnim.setTarget(image);
                alphaAnim.setPropertyName("alpha");
                alphaAnim.setDuration(1000);
                alphaAnim.setFloatValues(1, 0, 1);
                alphaAnim.start();

                break;

            case R.id.anim_btn_translation:
                float x = image.getTranslationX();
                ObjectAnimator translateAnim = new ObjectAnimator();
                translateAnim.setTarget(image);
                translateAnim.setPropertyName("translationX");
                translateAnim.setDuration(1000);
                translateAnim.setFloatValues(x, 500, x);
                translateAnim.start();
                translateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        image.setTranslationZ((Float) animation.getAnimatedValue());
                    }
                });
                break;

            case R.id.anim_btn_scale:
                ObjectAnimator.ofFloat(image, "scaleX", 1, 2, 1).setDuration(1000).start();
                break;


        }
    }
}
