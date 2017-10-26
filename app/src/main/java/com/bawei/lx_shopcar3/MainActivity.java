package com.bawei.lx_shopcar3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lx_shopcar3.adapters.MyAdapter;
import com.bawei.lx_shopcar3.bean.MyBean;
import com.bawei.lx_shopcar3.net.MyOkhttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ExpandableListView mElv;
    private String url = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=evaluation";
    private CheckBox mCbAll;
    /**
     * 总计
     */
    private TextView mTvPrice;
    /**
     * 0元
     */
    private Button mButton;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();

    }

    private void getData() {

        MyOkhttp.getAsync(url, new MyOkhttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this, "" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                MyBean myBean = new Gson().fromJson(result, MyBean.class);
                List<MyBean.DataBean> data = myBean.getData();
                myAdapter = new MyAdapter(data, MainActivity.this);
                mElv.setAdapter(myAdapter);
            }
        });
    }
    public void setCb(boolean bool) {
        mCbAll.setChecked(bool);
    }

    private void jiage(int i){
        mTvPrice.setText(i+"元");
    }
    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);

        mCbAll = (CheckBox) findViewById(R.id.cb_all);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCbAll.isChecked()){
                    mCbAll.setChecked(true);
                    myAdapter.ckall();
                    myAdapter.notifyDataSetChanged();
                }else{
                    mCbAll.setChecked(false);
                    myAdapter.cknull();
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                break;
        }
    }


}
