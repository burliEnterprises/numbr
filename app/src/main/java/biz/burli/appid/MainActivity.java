package biz.burli.appid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    TextView tv_number, tv_min, tv_max;
    EditText et_min, et_max;
    TableLayout tabelle;
    Button btn_generate;
    ImageView iv_menu, iv_arrow;
    int zahl;
    Random r;
    public int min, max;
    RelativeLayout rl_activitymain;
    ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_number = (TextView) findViewById(R.id.tv_number);
        btn_generate = (Button) findViewById(R.id.btn_generate);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_arrow = (ImageView) findViewById(R.id.iv_arrow);

        tv_min = (TextView) findViewById(R.id.tv_min);
        tv_max = (TextView) findViewById(R.id.tv_max);
        et_min = (EditText) findViewById(R.id.et_min);
        et_max = (EditText) findViewById(R.id.et_max);
        tabelle = (TableLayout) findViewById(R.id.tabelle);

        rl_activitymain = (RelativeLayout) findViewById(R.id.activity_main);

        min = 100000;
        max = 1000000;

        //final Intent intent_menu = new Intent(this, SettingsActivity.class);


        Typeface face = Typeface.createFromAsset(getAssets(), "manteka.ttf");
        tv_number.setTypeface(face);

        r = new Random();
        et_min.setText("100000");
        et_max.setText("1000000");




        valueAnimator = ValueAnimator.ofInt(1, 2);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //tv_number.setText(valueAnimator.getAnimatedValue().toString());
                zahl = r.nextInt(1000000 - 100000) + 100000;
                tv_number.setText(Integer.toString(zahl));
            }

        });
        valueAnimator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator valueAnimator)
            {
                try {
                    int x = getRandom();
                    tv_number.setText(Integer.toString(x));
                } catch (IllegalArgumentException e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Minimum declared larger than Maximum!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(R.mipmap.ic_launcher)
                            .show();
                }

            }
        });
        valueAnimator.start();




        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMinMax();
                setGradient();
                valueAnimator.start();
            }
        });

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("NUMBR")
                        .setMessage("More features coming soon!")
                        .setIcon(R.mipmap.ic_launcher)
                        .show();
            }
        });


        iv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tabelle.getVisibility() == View.VISIBLE) {
                    animation_iv(180f, 0.0f);
                    tabelle.setVisibility(View.INVISIBLE);
                } else {
                    animation_iv(0.0f, 180f);
                    tabelle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void animation_iv(float from, float to) {
        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(from, to,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animRotate.setDuration(300);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        iv_arrow.startAnimation(animSet);
    }

    private int getRandom() {
        int i = r.nextInt(max - min) + min;
        return i;
    }

    public void setMinMax() {
        max = Integer.parseInt(et_max.getText().toString());
        min = Integer.parseInt(et_min.getText().toString());
    }

    public void setGradient() {
        int bg = r.nextInt(4-0) + 0;
        switch (bg) {
            case 0:
                rl_activitymain.setBackgroundResource(R.drawable.gradient_blau);
                return;
            case 1:
                rl_activitymain.setBackgroundResource(R.drawable.gradient_ora);
                return;
            case 2:
                rl_activitymain.setBackgroundResource(R.drawable.gradient_rot);
                return;
            case 3:
                rl_activitymain.setBackgroundResource(R.drawable.gradient_turk);
                return;
            case 4:
                rl_activitymain.setBackgroundResource(R.drawable.gradient_white);
                return;
        };
    }

}

