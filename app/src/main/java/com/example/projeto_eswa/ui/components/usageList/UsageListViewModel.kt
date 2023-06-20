package com.example.projeto_eswa.ui.components.usageList

import androidx.lifecycle.ViewModel
import com.example.projeto_eswa.model.DateOption
import com.example.projeto_eswa.model.StatusModel

class UsageListViewModel : ViewModel() {

}

data class DiscoverViewState(
    val list: List<StatusModel> = emptyList(),
    val selectedCategory: DateOption = DateOption.TODAY
)