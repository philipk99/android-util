package de.klostermeier.androidutil.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

context(scope: CoroutineScope)
fun <T> Iterable<T>.forEachAsync(action: suspend (T) -> Unit) {
    this.forEach {
        scope.launch {
            action(it)
        }
    }
}

context(scope: CoroutineScope)
suspend fun <T, R> Iterable<T>.mapAsync(action: suspend (T) -> R): Iterable<R> {
    return this.map {
        scope.async {
            action(it)
        }
    }.awaitAll()
}