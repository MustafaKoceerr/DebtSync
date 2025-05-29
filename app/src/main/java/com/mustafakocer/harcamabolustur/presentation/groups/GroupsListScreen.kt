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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GroupsListScreen(
    onNavigateToGroupDetail: (String) -> Unit
) {
    // TODO: ViewModel'den gelecek
    val sampleGroups = listOf(
        "Ev Arkadaşları",
        "Tatil Grubu",
        "Ofis Yemekleri"
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Create Group Dialog/Screen
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
                text = "Your Groups",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (sampleGroups.isEmpty()) {
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
                        text = "Create your first group to start tracking expenses",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sampleGroups) { groupName ->
                        GroupItem(
                            groupName = groupName,
                            onClick = { onNavigateToGroupDetail("fake_id") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GroupItem(
    groupName: String,
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
                text = groupName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "3 members • 5 expenses", // TODO: Real data
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupsListScreenPreview() {
    GroupsListScreen(
        onNavigateToGroupDetail = {}
    )
}