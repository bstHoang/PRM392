package vn.edu.fpt.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private int danhDauThuTuChay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(" Danh dau onCreate chay thu: " + danhDauThuTuChay++);

    }

    @Override
   protected void onStart() {
    super.onStart();
        System.out.println(" Danh dau onCreate chay thu: " + danhDauThuTuChay++);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(" Danh dau onResume chay thu: " + danhDauThuTuChay++);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(" Danh dau onPause chay thu: " + danhDauThuTuChay++);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(" Danh dau onStop chay thu: " + danhDauThuTuChay++);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(" Danh dau onDestroy chay thu: " + danhDauThuTuChay++);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(" Danh dau onRestart chay thu: " + danhDauThuTuChay++);
    }
}