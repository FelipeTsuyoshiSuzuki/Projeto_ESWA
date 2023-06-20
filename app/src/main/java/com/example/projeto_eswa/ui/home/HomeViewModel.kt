package com.example.projeto_eswa.ui.home

import android.app.usage.UsageStats
import android.content.pm.ApplicationInfo
import androidx.lifecycle.ViewModel
import com.example.projeto_eswa.model.StatusModel

class HomeViewModel : ViewModel() {
    var usageList: List<UsageStats> = listOf()
    var appList: List<ApplicationInfo> = listOf()
    var itemList: MutableList<StatusModel> = mutableListOf()
        private set

    fun setItemList() {
        usageList.filter { it.totalTimeVisible.toInt() > 60000 }
            .forEach { usageInfo ->
                if (itemList.none { it.packageInfo?.packageName == usageInfo.packageName }) {
                    itemList.add(
                        StatusModel(
                            usageInfo = usageInfo,
                            packageInfo = appList.find { applicationInfo ->
                                applicationInfo.packageName == usageInfo.packageName
                            }
                        )
                    )
                }
            }
        itemList.sortByDescending {
            it.usageInfo.totalTimeVisible
        }
        sortList()
    }

    private fun sortList() = itemList.sortByDescending { it.usageInfo.totalTimeVisible }
}