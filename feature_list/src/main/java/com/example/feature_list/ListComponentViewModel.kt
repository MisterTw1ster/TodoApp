package com.example.feature_list

import androidx.lifecycle.ViewModel
import com.example.feature_list.di.DaggerListComponent
import com.example.feature_list.di.ListDepsProvider

internal class ListComponentViewModel: ViewModel() {
    val listComponent = DaggerListComponent.factory().create(ListDepsProvider.deps)
}