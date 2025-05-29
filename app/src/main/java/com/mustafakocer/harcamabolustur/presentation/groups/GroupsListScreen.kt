package com.mustafakocer.harcamabolustur.presentation.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.mustafakocer.harcamabolustur.domain.model.Group

@Composable
fun GroupsListScreen(
    onNavigateToGroupDetail: (String) -> Unit,
    viewModel: GroupsViewModel = hiltViewModel()
) {

    val groups = viewModel.groups.collectAsLazyPagingItems()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Test için grup oluştur
                    viewModel.createTestGroup()
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Group")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Your Groups (${groups.itemCount})",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (groups.itemCount == 0) {
                // Empty state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No groups yet",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Tap + to create your first group",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(count = groups.itemCount) { index ->
                        val group = groups[index]
                        group?.let {
                            GroupItem(
                                group = it,
                                onClick = { onNavigateToGroupDetail(it.id) }
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun GroupItem(
    group: Group,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${group.currency} • Code: ${group.inviteCode}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GroupsListScreenPreview() {
//    GroupsListScreen(
//        onNavigateToGroupDetail = {}
//    )
//}