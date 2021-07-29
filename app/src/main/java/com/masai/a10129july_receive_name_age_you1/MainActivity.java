package com.masai.a10129july_receive_name_age_you1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTvName;
    private TextView mTvAge;
    private Button mBtnSend;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnSend = findViewById(R.id.Btn1);
        mTvName = findViewById(R.id.tvName);
        mTvAge = findViewById(R.id.tvAge);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        registerLocalReceiver();

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.ava");
                intent.putExtra("key", "AVADHUT");
                intent.putExtra("key1", "21");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    private void registerLocalReceiver() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("com.ava");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    public class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String data = intent.getStringExtra("key");
                String data1 = intent.getStringExtra("key1");
                mTvName.setText(data);
                mTvAge.setText(data1);
            }
//            else if (intent != null) {
//                String data1 = intent.getStringExtra("key1");
//                mTvAge.setText(data1);
//            }
        }
    }
}