package first.zptc.cn.mywork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String user,maxuser;
    float x1=0,x2=0,y1=0,y2=0;
    TextView textViews[][]=new TextView[4][4],userText;
    EditText editText;
    int arr[][]=new int[4][4];
    int k,score=0,maxscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captur();
        clear();
        grab();
    }

    //获取最大分数
    public void grab(){
        SharedPreferences sharedPreference= getSharedPreferences("test",Activity.MODE_PRIVATE);
        maxuser =sharedPreference.getString("user", "");
        maxscore =sharedPreference.getInt("score",0);
        userText.setText(""+maxuser+":"+maxscore);
        Toast.makeText(this, "最大分数如下："+"\n"+"name：" + maxuser + "\n" + "habit：" + maxscore,Toast.LENGTH_LONG).show();
    }

    //存储最大分数
    public void storage() {
        //获取一个文件名为test、权限为private的xml文件的SharedPreferences对象
        SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("user", user);
        editor.putInt("score", score);
        Toast.makeText(this, "当前分数如下："+"\n"+"name：" + user + "\n" + "habit：" + score,Toast.LENGTH_LONG).show();
        editor.commit();
    }

    @Override
    //对手指滑动设置监听
    public boolean onTouchEvent(MotionEvent event){
        //设置一个标记，判定该方格移动几次
        int t[][]=new int[4][4];
        for (int i=0;i<4;i++)
            for(int j=0;j<4;j++)t[i][j]=0;
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            x1=event.getX();
            y1=event.getY();
        }
        if (event.getAction()==MotionEvent.ACTION_UP){
            x2=event.getX();
            y2=event.getY();

            //向上滑
            if(y1 - y2 > 50){
                for (int i=1;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(arr[i][j]==arr[i-1][j]&&arr[i][j]!=0){
                            arr[i-1][j]+=arr[i][j];
                            score+=arr[i-1][j];
                            editText.setText(""+score);
                            arr[i][j]=0;
                            setTV(i-1,j,arr[i-1][j]);
                            setTV(i,j,arr[i][j]);
                            k--;
                        }
                        else if(arr[i-1][j]==0){
                            arr[i-1][j]=arr[i][j];
                            arr[i][j]=0;
                            setTV(i-1,j,arr[i-1][j]);
                            setTV(i,j,arr[i][j]);
                        }
                    }
                }
            }

            //向下滑
            else if(y2 - y1 > 50){
                for (int i=2;i>-1;i--){
                    for(int j=0;j<4;j++){
                        if(arr[i][j]==arr[i+1][j]&&arr[i][j]!=0){
                            arr[i+1][j]+=arr[i][j];
                            score+=arr[i+1][j];
                            editText.setText(""+score);
                            arr[i][j]=0;
                            setTV(i+1,j,arr[i+1][j]);
                            setTV(i,j,arr[i][j]);
                            k--;
                        }
                        else if(arr[i+1][j]==0){
                            arr[i+1][j]=arr[i][j];
                            arr[i][j]=0;
                            setTV(i+1,j,arr[i+1][j]);
                            setTV(i,j,arr[i][j]);
                        }
                    }
                }
            }

            //向左滑
            else if(x1 - x2 > 50){
                for(int j=1;j<4;j++){
                    for(int i=0;i<4;i++){
                        if(arr[i][j]==arr[i][j-1]&&arr[i][j]!=0){
                            arr[i][j-1]+=arr[i][j];
                            score+=arr[i][j-1];
                            editText.setText(""+score);
                            arr[i][j]=0;
                            setTV(i,j-1,arr[i][j-1]);
                            setTV(i,j,arr[i][j]);
                            k--;
                        }
                        else if(arr[i][j-1]==0){
                            arr[i][j-1]=arr[i][j];
                            arr[i][j]=0;
                            setTV(i,j-1,arr[i][j-1]);
                            setTV(i,j,arr[i][j]);
                        }
                    }
                }
            }

            //向右滑
            else if(x2 - x1 > 50){
                for(int j=2;j>-1;j--){
                    for(int i=0;i<4;i++){
                        if(arr[i][j]==arr[i][j+1]&&arr[i][j]!=0){
                            arr[i][j+1]+=arr[i][j];
                            score+=arr[i][j+1];
                            editText.setText(""+score);
                            arr[i][j]=0;
                            setTV(i,j+1,arr[i][j+1]);
                            setTV(i,j,arr[i][j]);
                            k--;
                        }
                        else if(arr[i][j+1]==0){
                            arr[i][j+1]=arr[i][j];
                            arr[i][j]=0;
                            setTV(i,j+1,arr[i][j+1]);
                            setTV(i,j,arr[i][j]);
                        }
                    }
                }
            }
            Log.d("gameover", "onTouchEvent: "+k);
            Random();
        }
        return  super.onTouchEvent(event);
    }

    //对不同的值设置不同的textView
    public void setTV(int y,int x,int number){
        Log.d("onCreate", "setTV: ");
        if(number!=0) {
            textViews[y][x].setText("" + arr[y][x]);
            switch (arr[y][x]) {
                case 2:
                    textViews[y][x].setBackgroundResource(R.drawable.value_0);
                    break;
                case 4:
                case 8:
                    textViews[y][x].setBackgroundResource(R.drawable.value_1);
                    break;
                case 16:
                case 32:
                    textViews[y][x].setBackgroundResource(R.drawable.value_2);
                    break;
                case 64:
                case 128:
                    textViews[y][x].setBackgroundResource(R.drawable.value_3);
                    break;
                case 256:
                case 512:
                    textViews[y][x].setBackgroundResource(R.drawable.value_4);
                    break;
                case 1024:
                case 2048:
                    textViews[y][x].setBackgroundResource(R.drawable.value_5);
                    break;
                case 4096:
                case 8129:
                    textViews[y][x].setBackgroundResource(R.drawable.value_6);
                    break;
                default:
                    textViews[y][x].setBackgroundResource(R.drawable.value_7);
                    break;
            }
        }
        else {
            textViews[y][x].setText("");
            textViews[y][x].setBackgroundResource(R.drawable.value_0);
        }
    }


    //随机在两个方块中生成数字
    public void Random(){
        Log.d("onCreate", "Random: ");
        int a, b;
        Random random = new Random();
        if(k!=16) {
            do {
                a = random.nextInt(4);
                b = random.nextInt(4);
            } while (arr[a][b] != 0);
            arr[a][b] += 2;
            setTV(a, b, arr[a][b]);
            k ++;
            Log.d("gameover", "random: "+k);

        }
        else{
            if (game() != 1) {
                gameover();
            }
        }
    }

    //游戏结束
    public void gameover(){
        Intent intent =getIntent();
        user=intent.getStringExtra("str");
        if(score>=maxscore)
            storage();
        Intent intent1=new Intent(MainActivity.this,F.class);
        intent1.putExtra("score",score);
        startActivity(intent1);
    }


    //判定游戏是否结束
    public int game() {
        Log.d("onCreate", "game: ");
        int i, j, flag = 0;
        for (i = 0; i < 4; i++) {
            if (flag == 0) {
                for (j = 0; j < 4; j++) {
                    if (i != 3 && j != 3) {
                        if (arr[i][j] == arr[i][j + 1] || arr[i][j] == arr[i + 1][j]) {
                            flag = 1;
                            break;
                        }
                    } else if (i == 3 && j != 3) {
                        if (arr[i][j] == arr[i][j + 1]) {
                            flag = 1;
                            break;
                        }
                    } else if (i != 3 && j == 3) {
                        if (arr[i][j] == arr[i + 1][j]) {
                            flag = 1;
                            break;
                        }
                    }
                }
            }
        }
        if(flag==0) return flag;
        else return  flag;
    }
    //初始化所有的textview
    public void clear(){
        Log.d("onCreate: ", "Clear: ");
        int i,j;
        k=0;
        editText.setText(""+score);
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                arr[i][j]=0;
                setTV(i, j, arr[i][j]);
                Log.d("onCreate: ", "clear: ");
                textViews[i][j].setTextColor(Color.BLACK);
                textViews[i][j].setTextSize(20);
            }
        }
        Random();
    }

    //获取所有的textview
    public void captur(){
        Log.d("onCreate: ", "captur: ");
        editText= (EditText) findViewById(R.id.scoreET);
        userText= (TextView) findViewById(R.id.Hscore);
        textViews[0][0]= (TextView) findViewById(R.id.text1);
        textViews[0][1]= (TextView) findViewById(R.id.text2);
        textViews[0][2]= (TextView) findViewById(R.id.text3);
        textViews[0][3]= (TextView) findViewById(R.id.text4);
        textViews[1][0]= (TextView) findViewById(R.id.text5);
        textViews[1][1]= (TextView) findViewById(R.id.text6);
        textViews[1][2]= (TextView) findViewById(R.id.text7);
        textViews[1][3]= (TextView) findViewById(R.id.text8);
        textViews[2][0]= (TextView) findViewById(R.id.text9);
        textViews[2][1]= (TextView) findViewById(R.id.text10);
        textViews[2][2]= (TextView) findViewById(R.id.text11);
        textViews[2][3]= (TextView) findViewById(R.id.text12);
        textViews[3][0]= (TextView) findViewById(R.id.text13);
        textViews[3][1]= (TextView) findViewById(R.id.text14);
        textViews[3][2]= (TextView) findViewById(R.id.text15);
        textViews[3][3]= (TextView) findViewById(R.id.text16);
    }
}
