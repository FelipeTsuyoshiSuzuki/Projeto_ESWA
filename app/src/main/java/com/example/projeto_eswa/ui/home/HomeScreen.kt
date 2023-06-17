package com.example.projeto_eswa.ui.home

import android.Manifest
import android.app.Activity
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.projeto_eswa.ui.home.usageList.HomeListItem
import com.example.projeto_eswa.utils.FinishAcitivity
import com.example.projeto_eswa.utils.SKYBLUE
import com.example.projeto_eswa.utils.VIVIDBLUE
import com.example.projeto_eswa.utils.checkPermission
import com.example.projeto_eswa.utils.checkUsageStatsPermission
import com.example.projeto_eswa.utils.openUsageDataAccessSettings
import com.example.projeto_eswa.utils.toTime
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun HomeScreen(
    finishAcitivity: FinishAcitivity,
    pm: PackageManager
) {
    val context = LocalContext.current
    val viewModel = HomeViewModel()
    val isUsageStatsPermissionGranted = checkUsageStatsPermission(context)
    setStatusBarColor(context = context)
    if (!isUsageStatsPermissionGranted){
        openUsageDataAccessSettings(context)
        finishAcitivity.finishAcitivity()
    } else {
        getStatus(context, viewModel)
        getAppList(context, viewModel)
        viewModel.setItemList()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = SKYBLUE),
        ) {
            LazyColumn {
                item{
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp),
                        text = "Tempo de uso dos apps"
                    )
                }
                itemsIndexed(viewModel.itemList) { _, item ->
                    item.packageInfo?.let {
                        HomeListItem(
                            icon = it.loadIcon(pm),
                            appName = pm.getApplicationLabel(it) as String,
                            textTime = item.usageInfo.totalTimeVisible.toTime()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun setStatusBarColor(context: Context) {
    val window = (context as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    SideEffect {
        window.statusBarColor = VIVIDBLUE.toArgb()
    }
}

private fun getStatus(context: Context, viewModel: HomeViewModel) {
    val startTime: Long = (System.currentTimeMillis() - 86400000)
    val endTime: Long = System.currentTimeMillis()
    Log.d("Start Time", "$startTime")
    Log.d("End Time", "$endTime")
    Log.d("Time", "${System.currentTimeMillis()}")
    val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    viewModel.usageList = usageStatsManager.queryUsageStats(
        UsageStatsManager.INTERVAL_BEST,
        startTime,
        endTime
    )
}

private fun getAppList(context: Context, viewModel: HomeViewModel) {
    val pm = context.packageManager
    viewModel.appList = pm.getInstalledApplications(PackageManager.GET_META_DATA)
}
//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    val pm = LocalContext.current.packageManager
//    MaterialTheme {
//        HomeScreen(pm)
//    }
//}
