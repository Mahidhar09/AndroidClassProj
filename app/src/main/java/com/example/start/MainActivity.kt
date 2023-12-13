package com.example.start

import CallPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.start.ui.theme.StartTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import com.example.start.SongsPage
import com.example.start.MessagePage
import com.example.start.ImagesPage
import com.example.start.WelcomePage


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartTheme {
                val item = listOf(
                    NavigationDrawerItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unSelectedIcon = Icons.Outlined.Home,
                        badgeCount = null
                    ),
                    NavigationDrawerItem(
                        title = "Call",
                        selectedIcon = Icons.Filled.Call,
                        unSelectedIcon = Icons.Outlined.Call,
                        badgeCount = 25
                    ),
                    NavigationDrawerItem(
                        title = "Message",
                        selectedIcon = Icons.Filled.Email,
                        unSelectedIcon = Icons.Outlined.Email,
                        badgeCount = 35
                    ),
                    NavigationDrawerItem(
                        title = "Songs",
                        selectedIcon = Icons.Filled.PlayArrow,
                        unSelectedIcon = Icons.Outlined.PlayArrow,
                        badgeCount = null
                    ),
                    NavigationDrawerItem(
                        title = "Images",
                        selectedIcon = Icons.Filled.Favorite,
                        unSelectedIcon = Icons.Outlined.Favorite,
                        badgeCount = null
                    ),
                )
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DismissibleNavigationDrawer(
                        drawerContent = {
                            DismissibleDrawerSheet {
                                Spacer(modifier = Modifier.padding(16.dp))
                                item.forEachIndexed { index, navigationDrawerItem ->

                                    NavigationDrawerItem(
                                        label = {
                                            Text(navigationDrawerItem.title)
                                        },
                                        onClick = {
                                            selectedItemIndex = index
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        },
                                        selected = selectedItemIndex == index,
                                        icon = {
                                            Icon(
                                                imageVector = if (selectedItemIndex == index) {
                                                    navigationDrawerItem.selectedIcon
                                                } else navigationDrawerItem.unSelectedIcon,
                                                contentDescription = navigationDrawerItem.title
                                            )
                                        },
                                        badge = {
                                            navigationDrawerItem.badgeCount?.let {
                                                Text(navigationDrawerItem.badgeCount.toString())
                                            }
                                        },
                                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                    )
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text(if (selectedItemIndex == 0) "WELCOME!" else item[selectedItemIndex].title)
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }) {
                                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                                        }
                                    }
                                )
                            },
                            content = {
                                when (selectedItemIndex) {
                                    0 -> WelcomePage()
                                    1 -> CallPage()
                                    2 -> MessagePage()
                                    3 -> SongsPage()
                                    4 -> ImagesPage()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}





