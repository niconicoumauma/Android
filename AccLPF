package com.example.hayasi.acctest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //センサー
    private SensorManager mSensorManager;
    private Sensor mSensor;
    final float alpha = 0.8f;
    float gravityX, gravityY, gravityZ;


    //表示
    TextView xTextView;
    TextView yTextView;
    TextView zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ビュー登録
        findView();



        // センサーの準備
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    /* ---------- FindView (From activity_main.xml) ---------- */
    protected void findView() {
        xTextView = (TextView)findViewById(R.id.xValue);
        yTextView = (TextView)findViewById(R.id.yValue);
        zTextView = (TextView)findViewById(R.id.zValue);
    }

    /* ---------- ACC ---------- */
    @Override
    protected void onResume() {
        super.onResume();
        // センサーの取得
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mSensor != null) {
            // センサーへのイベントリスナーを設定
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // センサーへのイベントリスナーの解除
        mSensorManager.unregisterListener(this);
    }

    //センサー精度更新
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //センサー値更新
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            /*float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];*/

            //X軸
            gravityX = alpha * gravityX + (1 - alpha) * event.values[0];
            float x = event.values[0] - gravityX;


            //Y軸
            gravityY = alpha * gravityY + (1 - alpha) * event.values[1];
            float y = event.values[1] - gravityY;


            //Z軸
            gravityZ = alpha * gravityZ + (1 - alpha) * event.values[2];
            float z = event.values[2] - gravityZ;

            xTextView.setText("ACC X: " + x);
            yTextView.setText("ACC Y: " + y);
            zTextView.setText("ACC Z: " + z);

            Log.d("ACC_INFO", "x:" + x + ":y:" + y + ":z:" + z);
        }
    }

}
