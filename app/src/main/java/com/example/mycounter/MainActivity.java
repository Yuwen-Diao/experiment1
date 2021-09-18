package com.example.mycounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.VolumeShaper;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuInflater;
import android.view.OrientationEventListener;
import android.view.SubMenu;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        private EditText edit;
        private TextView view;
        private Button add;
        private Button substact;
        private Button multiply;
        private Button divide;
        private Button seven;
        private Button eight;
        private Button nine;
        private Button left;
        private Button four;
        private Button five;
        private Button six;
        private Button right;
        private Button three;
        private Button two;
        private Button one;
        private Button Clean;
        private Button dot;
        private Button zero;
        private Button n;
        private Button equal;
        private Button sin,cos,sqrt;
        private Button Help,upset;
        private String lastresult;
        private boolean now;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setfirst();
    }

    private void setfirst() {
        setContentView(R.layout.activity_main);

        Log.v("tag","test");
        edit = (EditText) findViewById(R.id.edit);
        view = (TextView) findViewById(R.id.view);
        add = (Button) findViewById(R.id.add);
        substact = (Button) findViewById(R.id.substact);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        left = (Button) findViewById(R.id.left);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        right = (Button) findViewById(R.id.right);
        three = (Button) findViewById(R.id.three);
        two = (Button) findViewById(R.id.two);
        one = (Button) findViewById(R.id.one);
        Clean = (Button) findViewById(R.id.Clean);
        dot = (Button) findViewById(R.id.dot);
        zero = (Button) findViewById(R.id.zero);
        equal = (Button) findViewById(R.id.equal);
        n=(Button) findViewById(R.id.n);
        sin=(Button)findViewById(R.id.sin);
        cos=(Button)findViewById(R.id.cos);
        sqrt=(Button)findViewById(R.id.sqrt);
        Help=(Button)findViewById(R.id.Help);
        upset=(Button)findViewById(R.id.upset);

        edit.setOnClickListener(this);
        view.setOnClickListener(this);
        add.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        dot.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        right.setOnClickListener(this);
        Clean.setOnClickListener(this);
        left.setOnClickListener(this);
        equal.setOnClickListener(this);
        substact.setOnClickListener(this);
        n.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        Help.setOnClickListener(this);
        upset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                else if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        String text = edit.getText().toString();//获取当前文本编译框内容
        String lasttext;
        boolean clean = false;
        Count a = new Count();
        if (text.equals("0"))
        {
            edit.setText("");
        }
        switch(v.getId())
        {
            case R.id.Help:
                edit.setText("这是帮助");
                break;
            case R.id.zero:
                edit.setText(edit.getText() + "0");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.one:
                edit.setText(edit.getText() + "1");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.two:
                edit.setText(edit.getText() + "2");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.three:
                edit.setText(edit.getText() + "3");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.four:
                edit.setText(edit.getText() + "4");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.five:
                edit.setText(edit.getText() + "5");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.six:
                edit.setText(edit.getText() + "6");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.seven:
                edit.setText(edit.getText() + "7");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.eight:
                edit.setText(edit.getText() + "8");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.nine:
                edit.setText(edit.getText() + "9");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.dot:
                edit.setText(edit.getText() + ".");
                Clean.setText("Clear");
                now = false;
                break;

            case R.id.right:
                edit.setText(edit.getText() + ")");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.left:
                edit.setText(edit.getText() + "(");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.add:
                edit.setText(edit.getText() + "+");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.substact:
                edit.setText(edit.getText() + "-");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.multiply:
                edit.setText(edit.getText() + "×");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.divide:
                edit.setText(edit.getText() + "÷");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.n:
                edit.setText(edit.getText() + "^");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.sin:
                edit.setText(edit.getText() + "s");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.cos:
                edit.setText(edit.getText() + "c");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.sqrt:
                edit.setText(edit.getText() + "✔");
                Clean.setText("Clear");
                now = false;
                break;
            case R.id.equal:
                edit.setText(edit.getText() + "=");
                lasttext = edit.getText().toString();
                String a1 = String.valueOf(a.caculate(lasttext));
                view.setText(a1);
                break;
            //清楚操作，保留上一结果，引入计算部分
            case R.id.Clean:
                edit.setText("0");
                Clean.setText("C");
                view.setText("0");
                now = true;
                //归零操作，不保留上一次结果
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.f2){
            double y=Double.parseDouble(edit.getText().toString());
            double l=2.54*y;
            String m=Double.toString(l);
            view.setText(m+"厘米");
        }
        if(item.getItemId()==R.id.f3){
            double y=Double.parseDouble(edit.getText().toString());
            double l=1.30795*y;
            String m=Double.toString(l);
            view.setText(m+"立方码（英制）");
        }
        else {
            switch (item.getItemId()) {
                case R.id.s1:
                    int i1 = Integer.parseInt(edit.getText().toString());
                    String cg1 = Integer.toBinaryString(i1).toString();
                    view.setText(cg1);
                    return true;
                case R.id.s2:
                    int i2 = Integer.parseInt(edit.getText().toString());
                    String cg2 = Integer.toOctalString(i2).toString();
                    view.setText(cg2);
                    return true;
                case R.id.s3:
                    int i3 = Integer.parseInt(edit.getText().toString());
                    String cg3 = Integer.toString(i3).toString();
                    edit.setText(cg3);
                    view.setText(cg3);
                    return true;
                case R.id.s4:
                    int i4 = Integer.parseInt(edit.getText().toString());
                    String cg4 = Integer.toHexString(i4).toString();
                    view.setText(cg4);
                    return true;
            }
        }
        return false;
    }
}