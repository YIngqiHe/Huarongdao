package com.hanlh.klotski.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hanlh.klotski.R;

import com.hanlh.klotski.util.SpUtils;
import com.hanlh.klotski.util.util;

public class GameSelectActivity extends Activity {
    TypedArray mGameBoardList;
    LinearLayout mGameTable;
    View.OnClickListener mOnClickListener;

    //已经通关数  默认为 第一关
  private    String strLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        mGameBoardList = getResources().obtainTypedArray(R.array.game_board_list);
        mGameTable = findViewById(R.id.game_table);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                int id = mGameBoardList.getResourceId((int) view.getTag() * 2 + 1, R.array.game_board_0);
                intent.putExtra("game_board_index", (int) view.getTag());
                intent.putExtra("game_board_id", id);
                intent.putExtra("game_board_name", ((Button)view).getText());
                startActivity(intent);
            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initLevel();
    }

    // 初始化关卡
    private void initLevel() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = util.px2dp(this, dm.widthPixels);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(util.dp2px(this, 250), util.dp2px(this, 50));
        int margin_y = util.dp2px(this, 10);
        int margin_x = util.dp2px(this, (width - 250) / 2);

        SpUtils.initialize(this);
        strLevel = SpUtils.get(SpUtils.LEVEL);
        int level = 0;

        if (!TextUtils.isEmpty(strLevel)) {
             level = Integer.valueOf(strLevel);
        }

        mGameTable.removeAllViews();
        for (int i = 0; i < mGameBoardList.length(); i += 2) {
            Button button = new Button(this);
            button.setTag(i / 2);
            button.setText(mGameBoardList.getString(i));
            button.setBackgroundResource(R.drawable.selector_button);

            button.setOnClickListener(mOnClickListener);
            if( level < i/2){
                button.setClickable(false);
                button.setEnabled(false);
                button.setTextColor(getResources().getColor(R.color.colorTextLight));
            }
            else{
                button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            button.setTextSize(20);
            layoutParams.setMargins(margin_x, margin_y, margin_x, margin_y);
            mGameTable.addView(button, layoutParams);
        }
    }

    public void navigateBack(View view) {
        finish();
    }
}
