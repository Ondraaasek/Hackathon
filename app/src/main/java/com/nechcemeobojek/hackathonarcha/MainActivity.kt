package com.nechcemeobojek.hackathonarcha

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
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
                        username = "testu",
                        points_arg = 195
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
fun Slot_machine(username: String, points_arg: Int, modifier: Modifier = Modifier) {
    var archaslot by remember { mutableStateOf(R.drawable.archa_logo_universal ) }
    var coins_box by remember { mutableStateOf(R.drawable.coins_light ) }
    var wol_text by remember { mutableStateOf("" ) }
    var points = points_arg
    var points_text by remember { mutableStateOf(points.toString()) }


    if (isSystemInDarkTheme()) {
        coins_box = R.drawable.coins_dark
    }
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
                text = stringResource(R.string.double_or_nothing),
                fontSize = 35.sp
            )
            Box(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = coins_box),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
                Text(
                    text = points_text,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .padding(start = 44.dp)
                )
            }
            Slot_machine_image_cooker(
                archaslot = archaslot,
                modifier = Modifier
            )
            var green_or_orange by remember { mutableStateOf(false) }
            Row(modifier = Modifier) {
                Text(
                    text = stringResource(R.string.green),
                    modifier = Modifier.padding(top = 21.dp)
                )
            Switch(
                checked = green_or_orange,
                onCheckedChange = { green_or_orange = it },
                colors = androidx.compose.material3.SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFFF4C00),
                    uncheckedThumbColor = Color.Black,
                    uncheckedTrackColor = Color(0xFFB8FF00)
                ),
                modifier = Modifier.padding(10.dp)
            )
                Text(
                    text = stringResource(R.string.orange),
                    modifier = Modifier.padding(top = 21.dp)
                )
            }
            val context = LocalContext.current
            Button(onClick = {
                    val scope = CoroutineScope(Dispatchers.Default)
                    var index = Random.nextInt(10, 12);
                    var delay_time = 100L;

                
                val sharedPref = context.getSharedPreferences(
                    "my_prefs", Context.MODE_PRIVATE
                )

                // Get the current integer value (or a default value)
                val currentIntValue = sharedPref.getInt("my_int_key", 0)

                // Increment the integer value
                val epoch_of_last_flip = currentIntValue

                // Write the new integer value to SharedPreferences
                val editor = sharedPref.edit()
                if (epoch_of_last_flip == 0 || (System.currentTimeMillis() / 1000 - epoch_of_last_flip) >= 86400) {
                    val editor = sharedPref.edit()
                    editor.putInt("my_int_key", (System.currentTimeMillis() / 1000).toInt())
                    editor.apply()
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
                        if (!green_or_orange) {
                            points*=2
                            wol_text = context.getString(R.string.you_win_you_point_are_doubled)
                            points_text = points.toString()
                        } else {
                            wol_text = context.getString(R.string.you_lose)
                            points = 0
                            points_text = points.toString()
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
                        if (green_or_orange) {
                            points = points * 2
                            wol_text = context.getString(R.string.you_win_you_point_are_doubled)
                            points_text = points.toString()
                        } else {
                            wol_text = context.getString(R.string.you_lose)
                            points = 0
                            points_text = points.toString()
                        }
                    }
            }} else {
                    wol_text = context.getString(R.string.you_may_only_spin_once_per_day)
                }
            }) {
                Text(stringResource(R.string.Coin_flip_button_text))
            }
            Text(
                text = wol_text,
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonArchaTheme {
        Slot_machine("testuser",
            points_arg = 195
        )
    }
}