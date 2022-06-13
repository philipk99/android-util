package de.klostermeier.androidutil.data

sealed class OrderDirection {
    object Ascending: OrderDirection()
    object Descending: OrderDirection()
}