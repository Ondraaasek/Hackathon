package com.nechcemeobojek.hackathonarcha

import android.os.Bundle
import android.service.autofill.OnClickAction
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nechcemeobojek.hackathonarcha.ui.theme.HackathonArchaTheme

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
fun Slot_machine_image_cooker(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.archa_logo_black),
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
    )
}
@Composable
fun Slot_machine(username: String, modifier: Modifier = Modifier) {
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
                modifier = Modifier
            )

            
            Button(onClick = {  }) {
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