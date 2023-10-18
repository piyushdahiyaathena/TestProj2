package com.testproj2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sdk.karzalivness.KLivenessView;
import com.sdk.karzalivness.enums.CameraFacing;
import com.sdk.karzalivness.enums.FaceStatus;
import com.sdk.karzalivness.enums.FaceTypeStatus;
import com.sdk.karzalivness.enums.KEnvironment;
import com.sdk.karzalivness.enums.KLiveStatus;
import com.sdk.karzalivness.interfaces.KLivenessCallbacks;
import com.sdk.karzalivness.models.KLiveResult;

public class LivenessViewActivity extends AppCompatActivity implements KLivenessCallbacks {

    KLivenessView kLivenessView;
    TextView faceStatusTextView;
    TextView faceTypeStatusTextView;
    TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveness_view);

        Log.i("new", "liveness on create");

        kLivenessView = findViewById(R.id.kliveview);
        faceStatusTextView =findViewById(R.id.facestatus);
        faceTypeStatusTextView =findViewById(R.id.facetypestatus);
        resultTextView=findViewById(R.id.result);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZXF1ZXN0X2lkIjoiMDNlODc3YTktNjhhNi00NjAxLTg4ZjgtZWY5ZDUwNjQ1MDdlIiwidXNlcl9pZCI6OTQ1MDgyLCJzY29wZSI6WyJsaXZlbmVzcyJdLCJlbnYiOiJ0ZXN0IiwiY2xpZW50X2lkIjoiSG9yYV9FZHVjYV9KRGVqbEMiLCJzdGFnZSI6InRlc3QiLCJ1c2VyX3R5cGUiOiJvcGVuIiwiZXhwaXJ5X3RpbWUiOiIxNy0xMC0yMDIzVDA2OjA3OjA0In0.5yPa0ZOxvFRDJE9fb-IV5-HZ7BcQT3hVBX0EwzVs_QI";
        needPermissions();
        kLivenessView.initialize(getSupportFragmentManager(), this, token, KEnvironment.DEV, null, CameraFacing.FRONT);

    }

    @Override
    public void needPermissions(@NonNull String... strings) {
        Log.i("a","needpermisson");
        ActivityCompat.requestPermissions(LivenessViewActivity.this, new String[] {Manifest.permission.CAMERA},1);
    }

    @Override
    public void showLoader() {
        Log.i("a","showloader");

    }

    @Override
    public void hideLoader() {
        Log.i("a","hideloader");

    }

    @Override
    public void onReceiveKLiveResult(KLiveStatus kLiveStatus, @Nullable KLiveResult kLiveResult) {
        Log.i("a","onrecieve");
        if(kLiveResult!=null){
            resultTextView.setText("klive status:  "+kLiveStatus+"  kliveresult:  "+String.valueOf(kLiveResult.getLivenessScore()));
        }

    }

    @Override
    public void faceStatus(FaceStatus faceStatus, @Nullable FaceTypeStatus faceTypeStatus) {
        Log.i("a","facestatus");
        if(faceStatus!=null){
            faceStatusTextView.setText("Face Status: "+faceStatus.toString());
        }

        if(faceTypeStatus!=null){
            faceTypeStatusTextView.setText("Face type status: "+faceTypeStatus.toString());
        }
    }

    @Override
    public void onError(String s) {
        Log.i("a","onerror");

    }


}