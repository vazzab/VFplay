package com.vazzab.vfplay

import android.os.Bundle
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.vazzab.vfplay.ui.theme.VFplayTheme
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        return ComposeView(requireContext()).apply {
            setContent {
                VFplayTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        TrackList()
                    }
                }
            }
        }
    }
}

data class TrackItem(val id: Int, val posterRes: Int, val title: String, val subtitle: String)

@Composable
fun TrackList() {
    val context = LocalContext.current

    val posterList = listOf(
        R.drawable.poster1,
        R.drawable.poster2,
        R.drawable.poster3,
        R.drawable.poster4,
        R.drawable.poster5
    )

    var trackItems by remember { mutableStateOf(listOf<TrackItem>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ControlButtons(
            onAdd = {
                if (trackItems.size >= 5) {
                    Toast.makeText(context, "Больше 5 не могу", Toast.LENGTH_SHORT).show()
                } else {
                    val newIndex = trackItems.size
                    val newItem = TrackItem(
                        id = newIndex,
                        posterRes = posterList[newIndex],
                        title = "Poster ${newIndex + 1}",
                        subtitle = "Text ${newIndex + 1}"
                    )
                    trackItems = trackItems + newItem
                }
            },
            onRemove = {
                if (trackItems.isEmpty()) {
                    Toast.makeText(context, "Нечего убирать", Toast.LENGTH_SHORT).show()
                } else {
                    trackItems = trackItems.drop(1) // удаляем первый элемент
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            trackItems.forEach { item ->
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                ) {
                    Track(item)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun Track(item: TrackItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = item.posterRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ControlButtons(
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AnimatedElevatedButton(text = "Добавить", onClick = onAdd)
        AnimatedElevatedButton(text = "Убрать", onClick = onRemove)
    }
}

@Composable
fun AnimatedElevatedButton(text: String, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "scale"
    )

    Button(
        onClick = {
            pressed = true
            onClick()
        },
        modifier = Modifier
            .scale(scale)
    ) {
        Text(text)
    }

    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(150)
            pressed = false
        }
    }
}