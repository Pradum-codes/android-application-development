package com.pradumcodes.list.progressbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateLinearProgress() {
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth().padding(16.dp).statusBarsPadding()
    )
}