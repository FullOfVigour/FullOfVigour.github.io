package first.zptc.cn.mywork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BestWay on 2018/1/3.
 */

public class A extends Activity{

    String str;
    EditText editText;
    int score;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        editText= (EditText) findViewById(R.id.editText2);
    }
    public void start(View v){
        Intent intent =new Intent(A.this,MainActivity.class);
        startActivity(intent);
        str=editText.getText().toString();
        intent.putExtra("str",str);
        score=0;
        startActivity(intent);
        Toast.makeText(this, "欢迎您"+str, Toast.LENGTH_LONG).show();
    }
}
