package com.nedhuo.wanandroid.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


/**
 * 数据类前面可以加一个data 现在不要加 留一个知识点待了解
 * */
class TodoBean {
    var name: String by mutableStateOf("")// 类似于 var name1: MutableState<String> = mutableStateOf("")
    var isCompleted: Boolean by mutableStateOf(false)
}