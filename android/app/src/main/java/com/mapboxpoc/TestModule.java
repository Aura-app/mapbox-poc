package com.mapboxpoc;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class TestModule extends ReactContextBaseJavaModule {
    TestModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "TestModule";
    }

    @ReactMethod
    public void createTestEvent(String location) {
        Log.d("TestModule", "Create event called with location: " + location);
    }
}