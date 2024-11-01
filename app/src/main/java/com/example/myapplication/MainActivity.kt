package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    val targetText = "123123"
    var userText by remember { mutableStateOf("") }
    var isGameOver by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0) }

    // перезагрузка
    fun resetGame() {
        userText = ""
        isGameOver = false
        elapsedTime = 5
    }

    //Тумер
    LaunchedEffect(key1 = isGameOver) {
        while (!isGameOver) {
            delay(1000)
            elapsedTime++

            }
        }

    LaunchedEffect(key1 = true) {
        delay(10000)
        isGameOver = true
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Время: $elapsedTime сек", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = targetText)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userText,
            onValueChange = { userText = it },
            label = { Text(text = "Введите текст") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isGameOver) {

            if (userText == targetText) {
                Text(text = "Вы успели за $elapsedTime секунд!")
            } else {
                Text(text = "Вы проиграли!")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { resetGame() }) {
                Text(text = "Перезагрузить игру")
            }
        }
    }
}
