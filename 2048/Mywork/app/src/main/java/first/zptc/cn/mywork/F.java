package first.zptc.cn.mywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by BestWay on 2018/1/3.
 */

public class F extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        EditText editText= (EditText) findViewById(R.id.editText);
        Intent intent =getIntent();
        int score=intent.getIntExtra("score",0);
        if(score!=0)editText.setText(score+"");
    }
    public void restart(View v){
        Intent intent =new Intent(F.this,MainActivity.class);
        startActivity(intent);
    }
}
