package com.example.hayasi.acctest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //センサー
    private SensorManager mSensorManager;
    private Sensor mSensor;
    final float alpha = 0.8f;
    float gravityX, gravityY, gravityZ;
    ImageView viewBikkuri;


    //表示
    TextView vectorView;
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
    //activity_main.xmlというレイアウトファイルとjava内のTextViewを紐づけるためのもの(findViewById)
    //R.id.<activity_mamin.xmlのID>で参照することが可能です
    protected void findView() {
        vectorView = (TextView)findViewById(R.id.vectorView);
        xTextView = (TextView)findViewById(R.id.xValue);
        yTextView = (TextView)findViewById(R.id.yValue);
        zTextView = (TextView)findViewById(R.id.zValue);
        viewBikkuri = (ImageView)findViewById(R.id.viewBikkuri);
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

            /*===== LPF =====*/
            //X軸
            gravityX = alpha * gravityX + (1 - alpha) * event.values[0];
            float x = event.values[0] - gravityX;
            //Y軸
            gravityY = alpha * gravityY + (1 - alpha) * event.values[1];
            float y = event.values[1] - gravityY;
            //Z軸
            gravityZ = alpha * gravityZ + (1 - alpha) * event.values[2];
            float z = event.values[2] - gravityZ;

            //各成分の絶対値
            x = Math.abs(x);
            y = Math.abs(y);
            z = Math.abs(z);

            //合成ベクトルsv(synthetic vector)を求める
            float sv = (float)Math.sqrt(x*x*x + y*y*y + z*z*z);

            vectorView.setText("合成ベクトル: " + sv);
            xTextView.setText("ACC X: " + x);
            yTextView.setText("ACC Y: " + y);
            zTextView.setText("ACC Z: " + z);

            if(sv > 10.0f) {
                viewBikkuri.setImageResource(R.drawable.bikkuri);
            }else{
                viewBikkuri.setImageBitmap(null);
            }

            Log.d("ACC_INFO", "x:" + x + ":y:" + y + ":z:" + z);
        }
    }

}
