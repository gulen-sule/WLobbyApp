package com.example.wlobbyapp.data.event

class MessageEvent() {
    private var value:Int=0
    fun getMessage(): Int {
        return value++
    }
}