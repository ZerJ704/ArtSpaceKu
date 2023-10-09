package id.utdi.jerymiannor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.utdi.jerymiannor.ui.theme.ArtSpaceKuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceKuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentIndex = 0
                    ArtSpaceApp(artItems[currentIndex])
                }
            }
        }
    }
}

data class ArtItem(val imageResId: Int, val name: String, val year: String)

val artItems = listOf( //TODO: membuat list item atau item yang ingin ditambahkan
    ArtItem(R.drawable.painting1, "Painting 1", "2006"),
    ArtItem(R.drawable.painting2, "Painting 2", "2013"),
    ArtItem(R.drawable.painting3, "Painting 3", "2009"),
)

@Composable
fun ArtSpaceApp(artItem: ArtItem) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val focusManager = LocalFocusManager.current

    //TODO: kolom untuk image atau gambar yang ditampilkan
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val currentItem = artItems[currentIndex]
        Image(
            painter = painterResource(id = currentItem.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
            contentScale = ContentScale.Crop
        )

        //TODO : kolom untuk teks yang tampil dengan alignment di tengah secara horizontal dan besar kceilnya headline
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentItem.name,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Year: ${currentItem.year}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = Color.Gray
            )

            //TODO: spacer untuk memberi spasi antar kolom text dengan baris button
            Spacer(modifier = Modifier.height(16.dp))

            //TODO: baris untuk membuat tombol next dan before agar dapat melihat gambar selanjutnya atau gambar sebelumnya
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        currentIndex = (currentIndex - 1).coerceAtLeast(0)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text("Previous")
                }
                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1).coerceAtMost(artItems.size - 1)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
@Preview
fun ArtSpaceAppPreview() {
    val artItems = listOf(
        ArtItem(R.drawable.painting1, "Painting 1", "2006"),
        ArtItem(R.drawable.painting2, "Painting 2", "2013"),
        ArtItem(R.drawable.painting3, "Painting 3", "2009"),
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    ArtSpaceApp(artItems[currentIndex])
}
