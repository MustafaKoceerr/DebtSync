package com.mustafakocer.harcamabolustur.presentation.navigation

import kotlinx.serialization.Serializable

// SPLASH
@Serializable
data object SplashRoute

// AUTH GRAPH
@Serializable
data object AuthGraph

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

// MAIN GRAPH
@Serializable
data object MainGraph

@Serializable
data object GroupsListRoute

@Serializable
data class GroupDetailRoute(
    val groupId: String
)

@Serializable
data class AddExpenseRoute(
    val groupId: String
)