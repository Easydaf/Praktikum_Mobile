package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var rollbtn: Button
    private lateinit var dice: ImageView
    private lateinit var dice2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dice = findViewById(R.id.dice_image1)
        dice2 = findViewById(R.id.dice_image2)

        rollbtn = findViewById(R.id.roll_button)

        rollbtn.setOnClickListener {
            rollDice()
        }
    }
    private fun rollDice() {

        val randomInt1 = (1..6).random()
        val drawableResource1 = when (randomInt1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
        val randomInt2 = (1..6).random()
        val drawableResource2 = when (randomInt2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
        dice.setImageResource(drawableResource1)
        dice2.setImageResource(drawableResource2)

        if (randomInt1 == randomInt2) {
            Toast.makeText(this, "Selamat Kamu dapat Double!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Anda Kurang Beruntung", Toast.LENGTH_SHORT).show()
        }
    }}

