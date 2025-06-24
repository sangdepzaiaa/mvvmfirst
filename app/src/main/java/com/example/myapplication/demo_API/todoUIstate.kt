package com.example.myapplication.demo_API

sealed interface todoUIstate{
    data object loading: todoUIstate
    data class susecc(val todoRespond: TodoResponse): todoUIstate
    data class suseccList(val list: List<User>): todoUIstate
    data class suseccListResponce(val user: PostResponce): todoUIstate
    data class  error(val throwable: Throwable): todoUIstate
}