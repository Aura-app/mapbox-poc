package com.mapboxpoc

import android.content.pm.PackageManager
import android.view.LayoutInflater
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.mapbox.navigation.dropin.NavigationView

class MapboxNavigationView(private val callerContext: ReactApplicationContext) : SimpleViewManager<NavigationView>() {
    private var accessToken: String? = null

    init {
        callerContext.runOnUiQueueThread {
            try {
                val app = callerContext.packageManager.getApplicationInfo(callerContext.packageName, PackageManager.GET_META_DATA)
                val bundle = app.metaData
                val accessToken = bundle.getString("MAPBOX_ACCESS_TOKEN")
                this.accessToken = accessToken
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val REACT_CLASS = "MapboxNavigationView"
    }

    override fun getName() = REACT_CLASS

    override fun createViewInstance(reactContext: ThemedReactContext): NavigationView {
        val mapboxNavigationView = LayoutInflater.from(reactContext).inflate(
            R.layout.mapbox_activity_navigation_view,
            null
        ) as NavigationView

//        mapboxNavigationView.api.routeReplayEnabled(true);
        mapboxNavigationView.api.startFreeDrive();
        return mapboxNavigationView
    }

//    @ReactProp(name = "origin")
//    fun setOrigin(view: NavigationView, sources: ReadableArray?) {
//        if (sources == null) {
//            view.setOrigin(null)
//            return
//        }
//        view.setOrigin(Point.fromLngLat(sources.getDouble(0), sources.getDouble(1)))
//    }

//    override fun onDropViewInstance(view: NavigationView) {
//        view.onDropViewInstance()
//        super.onDropViewInstance(view)
//    }


}