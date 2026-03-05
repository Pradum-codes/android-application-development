package com.pradumcodes.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Palette ──────────────────────────────────────────────────────────────────
private val BgDark      = Color(0xFF0D0F14)
private val Surface1    = Color(0xFF161920)
private val Surface2    = Color(0xFF1E2230)
private val AccentCyan  = Color(0xFF00E5FF)
private val AccentViolet= Color(0xFF7C4DFF)
private val AccentPink  = Color(0xFFFF4081)
private val TextPrimary = Color(0xFFECEFF1)
private val TextMuted   = Color(0xFF607D8B)

private val GradientVertical   = Brush.linearGradient(listOf(Color(0xFF00E5FF), Color(0xFF00BFA5)))
private val GradientHorizontal = Brush.linearGradient(listOf(Color(0xFF7C4DFF), Color(0xFFE040FB)))
private val GradientGrid       = Brush.linearGradient(listOf(Color(0xFFFF4081), Color(0xFFFF6D00)))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { ListApp() }
    }
}

enum class LayoutType { VERTICAL, HORIZONTAL, GRID }

@Composable
fun ListApp() {
    var input      by remember { mutableStateOf("") }
    val items      = remember { mutableStateListOf("Apple", "Banana") }
    var layoutType by remember { mutableStateOf(LayoutType.VERTICAL) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        // Subtle radial glow in the background
        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = (-40).dp)
                .background(
                    Brush.radialGradient(
                        listOf(AccentViolet.copy(alpha = 0.15f), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            // ── Header ──────────────────────────────────────────────────────
            HeaderSection()

            Spacer(Modifier.height(28.dp))

            // ── Input ───────────────────────────────────────────────────────
            InputSection(
                input = input,
                onInputChange = { input = it },
                onAddClick = {
                    if (input.isNotBlank()) {
                        items.add(0, input.trim())
                        input = ""
                    }
                }
            )

            Spacer(Modifier.height(20.dp))

            // ── Layout Selector ─────────────────────────────────────────────
            LayoutSelector(layoutType) { layoutType = it }

            Spacer(Modifier.height(20.dp))

            // ── Count chip ──────────────────────────────────────────────────
            Text(
                text = "${items.size} item${if (items.size != 1) "s" else ""}",
                color = TextMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // ── List Display ─────────────────────────────────────────────────
            ItemDisplay(items, layoutType)
        }
    }
}

// ── Header ────────────────────────────────────────────────────────────────────
@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "LIST",
            fontSize = 11.sp,
            letterSpacing = 4.sp,
            fontWeight = FontWeight.SemiBold,
            color = AccentCyan
        )
        Text(
            text = "Playground",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextPrimary
        )
    }
}

// ── Input ─────────────────────────────────────────────────────────────────────
@Composable
fun InputSection(
    input: String,
    onInputChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Surface2)
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            placeholder = { Text("Add a new item…", color = TextMuted, fontSize = 14.sp) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor   = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor     = TextPrimary,
                unfocusedTextColor   = TextPrimary,
                cursorColor          = AccentCyan,
                focusedContainerColor   = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            modifier = Modifier.weight(1f)
        )

        // Gradient FAB-style Add button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(listOf(AccentCyan, AccentViolet))
                )
                .then(
                    Modifier.clickableNoRipple { onAddClick() }
                )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

// ── Layout Selector ───────────────────────────────────────────────────────────
@Composable
fun LayoutSelector(
    selected: LayoutType,
    onSelected: (LayoutType) -> Unit
) {
    data class Tab(val type: LayoutType, val label: String, val icon: ImageVector)

    val tabs = listOf(
        Tab(LayoutType.VERTICAL,   "Vertical",   Icons.AutoMirrored.Default.KeyboardArrowRight),
        Tab(LayoutType.HORIZONTAL, "Horizontal", Icons.Default.ArrowDropDown),
        Tab(LayoutType.GRID,       "Grid",        Icons.Default.Add),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Surface2)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = selected == tab.type
            val gradient   = when (tab.type) {
                LayoutType.VERTICAL   -> GradientVertical
                LayoutType.HORIZONTAL -> GradientHorizontal
                LayoutType.GRID       -> GradientGrid
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isSelected) gradient else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent)))
                    .then(Modifier.clickableNoRipple { onSelected(tab.type) })
                    .padding(vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = if (isSelected) Color.White else TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = tab.label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.White else TextMuted
                    )
                }
            }
        }
    }
}

// ── Item Display ──────────────────────────────────────────────────────────────
@Composable
fun ItemDisplay(items: List<String>, layoutType: LayoutType) {

    AnimatedContent(
        targetState = layoutType,
        transitionSpec = {
            fadeIn(tween(200)) togetherWith fadeOut(tween(200))
        },
        label = "layout_switch"
    ) { type ->
        when (type) {
            LayoutType.VERTICAL -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    ItemCard(item, GradientVertical, accent = AccentCyan)
                }
            }

            LayoutType.HORIZONTAL -> LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items) { item ->
                    ItemCard(
                        item, GradientHorizontal, accent = AccentViolet,
                        modifier = Modifier.width(150.dp)
                    )
                }
            }

            LayoutType.GRID -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    ItemCard(item, GradientGrid, accent = AccentPink)
                }
            }
        }
    }
}

// ── Item Card ─────────────────────────────────────────────────────────────────
@Composable
fun ItemCard(
    text: String,
    gradient: Brush,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Surface1),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(listOf(accent.copy(alpha = 0.5f), Color.Transparent)),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Accent dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(gradient)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = text,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
            }
        }
    }
}

@Composable
private fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier =
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )

// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D0F14)
@Composable
fun DefaultPreview() { ListApp() }