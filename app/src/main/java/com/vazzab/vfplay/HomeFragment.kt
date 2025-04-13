package com.vazzab.vfplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                VFplayTheme {
                    ContentList()
                }
            }
        }
    }
}

@Composable
fun ContentList() {
    val context = LocalContext.current
    var contentItems by remember { mutableStateOf(listOf<Int>()) }

    val posters = listOf(
        R.drawable.poster1,
        R.drawable.poster2,
        R.drawable.poster3,
        R.drawable.poster4,
        R.drawable.poster5
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (contentItems.size >= 5) {
                    Toast.makeText(context, "Больше 5 не могу", Toast.LENGTH_SHORT).show()
                } else {
                    val next = contentItems.size
                    contentItems = contentItems + next
                }
            }) {
                Text("Добавить")
            }

            Button(onClick = {
                if (contentItems.isEmpty()) {
                    Toast.makeText(context, "Нечего убирать", Toast.LENGTH_SHORT).show()
                } else {
                    // удаляем первый элемент (Track1)
                    contentItems = contentItems.drop(1)
                }
            }) {
                Text("Убрать")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            contentItems.forEachIndexed { index, i ->
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                ) {
                    TrackItem(
                        posterId = posters[i],
                        title = "poster${i + 1}",
                        subtitle = "text${i + 1}"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun TrackItem(posterId: Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = posterId),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
