package com.mustafakocer.harcamabolustur.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mustafakocer.harcamabolustur.presentation.auth.LoginScreen
import com.mustafakocer.harcamabolustur.presentation.expense.AddExpenseScreen
import com.mustafakocer.harcamabolustur.presentation.groups.GroupDetailScreen
import com.mustafakocer.harcamabolustur.presentation.groups.GroupsListScreen
import com.mustafakocer.harcamabolustur.presentation.splash.SplashScreen

@Composable
fun DebtSyncNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        // SPLASH SCREEN
        composable<SplashRoute> {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate(AuthGraph) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(MainGraph) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

        // AUTH GRAPH
        navigation<AuthGraph>(
            startDestination = LoginRoute
        ) {
            composable<LoginRoute> {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate(RegisterRoute) },
                    onNavigateToMain = {
                        navController.navigate(MainGraph) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    }
                )
            }

            composable<RegisterRoute> {
                // TODO: RegisterScreen implementation
                // RegisterScreen(
                //     onNavigateToLogin = { navController.navigate(LoginRoute) },
                //     onNavigateToMain = { navController.navigate(MainGraph) }
                // )
            }
        }

        // MAIN GRAPH
        navigation<MainGraph>(
            startDestination = GroupsListRoute
        ) {
            composable<GroupsListRoute> {
                GroupsListScreen(
                    onNavigateToGroupDetail = { groupId ->
                        navController.navigate(GroupDetailRoute(groupId))
                    }
                )
            }

            composable<GroupDetailRoute> { navBackStackEntry ->
                val args = navBackStackEntry.toRoute<GroupDetailRoute>()
                GroupDetailScreen(
                    groupId = args.groupId,
                    onNavigateToAddExpense = { groupId ->
                        navController.navigate(AddExpenseRoute(groupId))
                    },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable<AddExpenseRoute> { navBackStackEntry ->
                val args = navBackStackEntry.toRoute<AddExpenseRoute>()
                AddExpenseScreen(
                    groupId = args.groupId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}