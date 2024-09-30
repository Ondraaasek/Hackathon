package com.nechcemeobojek.hackathonarcha

import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nechcemeobojek.hackathonarcha.ui.theme.HackathonArchaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackathonArchaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Slot_machine(
                        modifier = Modifier,
                        username = "testu"
                    )

                }
                }
            }
        }
    }

@Composable
fun Slot_machine_image_cooker(archaslot: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = archaslot),
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
    )
}
@Composable
fun Slot_machine(username: String, modifier: Modifier = Modifier) {
    var archaslot by remember { mutableStateOf(R.drawable.archa_logo_universal ) }
    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
        ) {
            Text(
                text = "Double or nothing",
                fontSize = 35.sp
            )
            Slot_machine_image_cooker(
                archaslot = archaslot,
                modifier = Modifier
            )
            var green_or_orange by remember { mutableStateOf(true) }
            Switch(
                checked = green_or_orange,
                onCheckedChange = { green_or_orange = it },
                checkedThumbColor = Color.Red,
                uncheckedThumbColor = Color.Green,
                checkedTrackColor = Color.Blue,
                uncheckedTrackColor = Color.Yellow,
                modifier = Modifier.padding(10.dp)
            )
            Button(onClick = {
                    val scope = CoroutineScope(Dispatchers.Default)
                    var index = Random.nextInt(10, 12);
                    var delay_time = 100L;
                scope.launch {
                    if (Random.nextInt(0, 2) == 0) {
                        // green at the end
                        while (index != 0) {
                            archaslot = R.drawable.archa_logo_dark;
                            delay(delay_time);
                            archaslot = R.drawable.archa_logo_light;
                            delay(delay_time);
                            index--;
                            delay_time += 50L;
                        }
                    } else {
                        // orange at the end
                        while (index != 0) {
                            archaslot = R.drawable.archa_logo_light;
                            delay(delay_time);
                            archaslot = R.drawable.archa_logo_dark;
                            delay(delay_time);
                            index--;
                            delay_time += 100L;
                        }
                    }
            }}) {
                Text(stringResource(R.string.Coin_flip_button_text))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonArchaTheme {
        Slot_machine("testuser")
    }
}