/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.datasync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datasync.ui.theme.DataSyncTheme
import com.example.model.BreathCheck
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<MainViewModel>()
            val breathChecks by viewModel.breathCheckFlow.collectAsState(initial = emptyList())
            var enteredValue by remember { mutableStateOf("") }

            DataSyncTheme {
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
                        Spacer(modifier = Modifier.height(8.dp))
                        ValueEnterBox(enteredValue) { enteredValue = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        SaveButton { viewModel.saveBreathCheck(enteredValue.toDouble()) }
                        Spacer(modifier = Modifier.height(8.dp))
                        GetButton { viewModel.getBreathChecks() }
                        Spacer(modifier = Modifier.height(8.dp))
                        BreathCheckList(checks = breathChecks)
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
fun ValueEnterBox(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.enter_value)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Text(text = stringResource(id = R.string.save))
    }
}

@Composable
fun GetButton(onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Text(text = stringResource(id = R.string.get))
    }
}

@Composable
fun BreathCheckList(checks: List<BreathCheck>) {
    LazyColumn(
        modifier = Modifier
            .heightIn(0.dp, 200.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(checks) { check ->
            Text(text = "Breath Check: ${check.value} ${check.timestamp}")
        }
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
            ValueEnterBox("") {}
            SaveButton {}
            GetButton {}
            BreathCheckList(checks = listOf(BreathCheck(1, 3.5, Date())))
        }
    }
}
