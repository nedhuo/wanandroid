package com.nedhuo.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nedhuo.wanandroid.data.TodoBean
import com.nedhuo.wanandroid.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. 进入compose模式
            MyApplicationTheme { //2. 类似大括号都是lambda表达式类型
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    val todos = listOf(
                        TodoBean().apply {
                            name = "张三"
                            isCompleted = true
                        },
                        TodoBean().apply {
                            name = "李三"
                            isCompleted = false
                        },
                        TodoBean().apply {
                            name = "王三"
                            isCompleted = true
                        }
                    )
                    Todos(todos)
                }
            }
        }
    }
}

/**
 * remeber
 * 不让变量被反复执行 反复初始化
 * */
@Composable
fun HomePage() {
    var isInputting by remember { mutableStateOf(false) }
    val animatedFabScale by animateFloatAsState(
        if (isInputting) 1f else 0f
    )
    val animatedInputScale by animateFloatAsState(
        if (isInputting) 0f else 1f
    )

    val todos = listOf(
        TodoBean().apply {
            name = "张三"
            isCompleted = true
        },
        TodoBean().apply {
            name = "李三"
            isCompleted = false
        },
        TodoBean().apply {
            name = "王三"
            isCompleted = false
        }
    )

    Scaffold(
        topBar = {
            TopAppBar() {

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isInputting = true //显示输入框
                },
                Modifier.scale(animatedFabScale),
            ) {
                Icon(Icons.Filled.Add, contentDescription = "添加")
            }
        },
        bottomBar = {
            BottomAppBar {

            }
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            Todos(todos)
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .scale(animatedInputScale)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    Modifier.weight(1f),
                )
                Button(onClick = {
                    isInputting = false
                }) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "",
                    )
                }
            }
        }

    }
}

@Composable
fun Todos(todos: List<TodoBean>) {
    Surface(
        Modifier.padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        LazyColumn(Modifier.fillMaxWidth()) {
            items(todos) { todo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = todo.isCompleted,
                        onCheckedChange = { changed ->
                            todo.isCompleted = changed
                        },
                    )
                    Text(text = todo.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodosPreview() {
    HomePage()
}
