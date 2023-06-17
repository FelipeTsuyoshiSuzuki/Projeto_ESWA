package com.example.projeto_eswa.model

import android.app.usage.UsageStats
import android.content.pm.ApplicationInfo

data class StatusModel (
    val packageInfo: ApplicationInfo?,
    val usageInfo: UsageStats
)