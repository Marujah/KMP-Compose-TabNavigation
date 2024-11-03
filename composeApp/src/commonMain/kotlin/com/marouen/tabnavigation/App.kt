package com.marouen.tabnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.marouen.tabnavigation.tab.FavoritesTab
import com.marouen.tabnavigation.tab.HomeTab
import com.marouen.tabnavigation.tab.ProfileTab
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(HomeScreen)
    }
}


object HomeScreen : Screen {
    @Composable
    override fun Content() {
        // Get the navigator
        val navigator = LocalNavigator.current


        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Home") })
            },
            content = { contentPadding ->
                Column(modifier = Modifier.padding(contentPadding)) {
                    navigator?.push(DetailScreen(index = 0))
                }
            }
        )
    }
}

data class DetailScreen(val index: Int) : Screen {
    @Composable
    override fun Content() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            TabNavigator(HomeTab) {
                Scaffold(
                    content = { paddingValues ->
                        Column(modifier = Modifier.padding(paddingValues)) {
                            CurrentTab()
                        }
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.Transparent,
                        ) {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(FavoritesTab)
                            TabNavigationItem(ProfileTab)
                        }
                    }
                )
            }
        }

    }
}


