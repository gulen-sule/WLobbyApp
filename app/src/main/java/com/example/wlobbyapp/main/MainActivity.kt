package com.example.wlobbyapp.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.wlobbyapp.LobbyApp
import com.example.wlobbyapp.R
import com.example.wlobbyapp.databinding.MainActivityBinding
import com.google.android.material.tabs.TabItem

class MainActivity : AppCompatActivity() {
    private lateinit var itemSearch: TabItem
    private lateinit var itemCreate: TabItem
    private lateinit var itemLobby: TabItem
    private lateinit var itemProfıle: TabItem


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


    }
}