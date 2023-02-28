package com.mapboxpoc

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class MapboxNavigationModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {

    private val context: ReactApplicationContext = this.reactApplicationContext
    companion object {
        const val REACT_CLASS = "MapboxNavigationModule"
    }

    override fun getName() = REACT_CLASS

    @ReactMethod
    fun navigateToNative() {
        val navigationViewIntent = Intent(context, NavigationViewActivity::class.java)
        if(navigationViewIntent.resolveActivity(context.packageManager) != null) {
            navigationViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(navigationViewIntent)
        }
    }
}