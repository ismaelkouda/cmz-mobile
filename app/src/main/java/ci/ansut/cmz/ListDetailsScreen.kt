package ci.ansut.cmz

import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import ci.ansut.cmz.utils.Data
import ci.ansut.cmz.utils.Discussion
import kotlinx.coroutines.launch

@Composable
fun ListDetailsScreen() {
    val scope = rememberCoroutineScope()
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    val navigator = rememberListDetailPaneScaffoldNavigator<Discussion>()

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane() {
                ListContent(
                    Data.discussions,
                    navigateToDetails = { discussion ->
                        scope.launch {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = discussion
                            )
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane() {
                val currentDiscussion = navigator.currentDestination?.contentKey;
                if (currentDiscussion == null) {
                    Text(text = "No data")
                }
                currentDiscussion?.participants?.second?.let { Text(text = it) }
            }
        }
    )
}

@Preview()
@Composable
fun ListDetailsScreenPreview() {
    ListDetailsScreen()
}