package com.testproj2;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class LivenessModule extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;
    LivenessModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Log.i("livenessModule const", "ran");
        this.reactContext=reactContext;
    }

    @Nonnull
    @Override
    public String getName() {
        return "LivenessModule";
    }

    @ReactMethod
    public void fun(Callback callback){
        Log.d("test","test");
        callback.invoke("data returned");
    }

    //runs on button pressed and sends data back
    @ReactMethod
    public void funPromise(Promise promise){
        try{
            promise.resolve("promise data returned");
            funEmitter(getReactApplicationContext(), "funEmitter", 1);
        } catch (Exception e){
            promise.reject("funPromise promise error");
        }
    }

    //emits changes to 'num' to react part
    private void funEmitter(ReactContext reactContext, String evenName, int num){
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(evenName, num);
    }

    @ReactMethod
    public void livenessViewFun(Promise promise){
        try{
            Log.i("livenessViewFun", "ran");
            Intent i = new Intent(reactContext, LivenessViewActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            reactContext.startActivity(i);
            promise.resolve(null);
        }   catch (Exception e){
            promise.reject("livenessViewFun promise error");
        }
    }
}
