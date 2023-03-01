package com.mapboxpoc

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class MapboxNavigationModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {

    private val context: ReactApplicationContext = this.reactApplicationContext
    companion object {
        const val REACT_CLASS = "MapboxNavigationModule"
    }

    override fun getName() = REACT_CLASS

//    @ReactMethod
//    fun navigateToNative() {
//        val navigationViewIntent = Intent(context, NavigationViewActivity::class.java)
//        if(navigationViewIntent.resolveActivity(context.packageManager) != null) {
//            navigationViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(navigationViewIntent)
//        }
//    }

    @ReactMethod
    fun navigateToNavigationView() {
        val navigationViewIntent = Intent(context, NavigationViewActivity::class.java)
        if(navigationViewIntent.resolveActivity(context.packageManager) != null) {
            navigationViewIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(navigationViewIntent)
        }
    }

    @ReactMethod
    fun navigateToTurnByTurn() {
        val turnByTurnIntent = Intent(context, TurnByTurnExperienceActivity::class.java)
        if(turnByTurnIntent.resolveActivity(context.packageManager) != null) {
            turnByTurnIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(turnByTurnIntent)
        }
    }
}