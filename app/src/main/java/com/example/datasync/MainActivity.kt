/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.datasync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datasync.ui.theme.DataSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataSyncTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Greeting("User")
                        ValueEnterBox()
                        SaveButton()
                        GetButton()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ValueEnterBox() {
    var value = remember { "" }
    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text(text = stringResource(id = R.string.enter_value)) }
    )
}

@Composable
fun SaveButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = stringResource(id = R.string.save))
    }
}

@Composable
fun GetButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = stringResource(id = R.string.get))
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DataSyncTheme {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Greeting("User")
            ValueEnterBox()
            SaveButton()
            GetButton()
        }
    }
}
