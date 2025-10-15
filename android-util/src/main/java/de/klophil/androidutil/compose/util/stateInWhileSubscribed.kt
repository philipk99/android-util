package de.klophil.androidutil.compose.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

context(viewModel: ViewModel)
fun <T> Flow<T>.stateInWhileSubscribed(initialValue: T, stopTimeoutMillis : Long = 5_000L): StateFlow<T> {
    return this.stateIn(
        viewModel.viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis),
        initialValue
    )
}