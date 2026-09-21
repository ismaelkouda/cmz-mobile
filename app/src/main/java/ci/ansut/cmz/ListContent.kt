package ci.ansut.cmz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ci.ansut.cmz.utils.Discussion

@Composable
fun ListContent(
    discussions: List<Discussion>,
    navigateToDetails: (Discussion) -> Unit
) {
    @OptIn(ExperimentalMaterial3Api::class)
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(
            @OptIn(ExperimentalMaterial3Api::class)
            scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                modifier = Modifier,
                expandedHeight = 80.dp,
                windowInsets = TopAppBarDefaults.windowInsets,
                scrollBehavior = scrollBehavior,
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.menu_24px),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                },
                title = {
                    TextField(
                        value = "",
                        onValueChange = {  },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Rechercher")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(28.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.outline,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                        )
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.notifications_24px),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.account_circle_24px),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = contentColorFor(containerColor),
                tonalElevation = BottomAppBarDefaults.ContainerElevation,
                contentPadding = BottomAppBarDefaults.ContentPadding,
                windowInsets = BottomAppBarDefaults.windowInsets,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = SnackbarHostState()
            )
        },
        floatingActionButton = {

        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            items(discussions) { discussion ->
                ListItem(
                    headlineContent = {
                        Text(discussion.participants.second)
                    },
                    leadingContent = {
                        Box(
                            modifier = Modifier.size(40.dp).clip(CircleShape).background(
                                MaterialTheme.colorScheme.surfaceContainerHighest),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(discussion.participants.second.first().uppercase())
                        }
                    },
                    trailingContent = {
                        Icon(
                            painter = painterResource(R.drawable.keyboard_arrow_right_24px),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    },
                    modifier = Modifier.clickable(onClick = {navigateToDetails(discussion)})
                )
            }
        }
    }
}