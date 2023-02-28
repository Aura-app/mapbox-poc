package com.mapboxpoc

//class NavigationViewActivity : AppCompatActivity() {
//
//    private lateinit var binding: NavigationViewBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = NavigationViewBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // This allows to simulate your location
//        binding.navigationView.api.routeReplayEnabled(true)
//    }
//}

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import android.Manifest
import android.widget.Toast
import com.mapboxpoc.databinding.NavigationViewBinding

class NavigationViewActivity : AppCompatActivity(), PermissionsListener {
    private lateinit var binding: NavigationViewBinding
    private val permissionsManager = PermissionsManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This allows to simulate your location
        binding.navigationView.api.routeReplayEnabled(true)

        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            requestOptionalPermissions()
        } else {
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(
            this,
            "This app needs location and storage permissions in order to show its functionality.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            requestOptionalPermissions()
        } else {
            Toast.makeText(
                this,
                "You didn't grant the permissions required to use the app",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestOptionalPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        // starting from Android R leak canary writes to Download storage without the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                10
            )
        }
    }

}
