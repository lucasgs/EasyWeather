package com.dendron.easyweather.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.dendron.easyweather.domain.location.LocationData
import com.dendron.easyweather.domain.location.LocationProvider
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine


@OptIn(ExperimentalCoroutinesApi::class)
class DefaultLocationProvider(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application,
) : LocationProvider {
    override suspend fun getCurrentLocation(): LocationData? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasAccessFineLocationPermission || !hasAccessCoarseLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        result?.let {
                            cont.resume(it.toDomain(), onCancellation = null)
                        }
                    } else {
                        cont.resume(null, onCancellation = null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    result?.let {
                        cont.resume(it.toDomain(), onCancellation = null)
                    }
                }
                addOnFailureListener {
                    cont.resume(null, onCancellation = null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}

fun Location.toDomain() = LocationData(
    latitude = latitude,
    longitude = longitude,
)