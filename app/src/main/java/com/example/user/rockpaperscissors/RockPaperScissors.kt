package com.example.user.rockpaperscissors

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_rock_paper_scissors.*
import java.util.Random

class RockPaperScissors : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

    }

    fun <E> List<E>.getRandomElement() = this[Random().nextInt(this.size)]


    fun <E> List<E>.getRandomElements(numberOfElements: Int): List<E>? {
        if (numberOfElements > this.size) {
            return null
        }
        return this.shuffled().take(numberOfElements)
    }
    fun gameon(view:View) {
        PC.visibility = View.VISIBLE
        Result.visibility = View.VISIBLE
        val list = listOf("Rock","Paper","Scissors")
        val button = view as Button
        val pcChoice = list.getRandomElement()
        val yourChoice = button.text.toString()
        if (pcChoice == yourChoice) {
            Result.text = "Draw"
        } else if (pcChoice == "Rock") {
            if (yourChoice == "Paper") {
                Result.text = "Win"
            } else {
                Result.text = "Lose"
            }
        } else if (pcChoice == "Paper") {
            if (yourChoice == "Rock") {
                Result.text = "Lost"
            } else {
                Result.text = "win"
            }
        } else if (pcChoice =="Scissors") {
            if (yourChoice == "Rock") {
                Result.text = "win"
            } else {
                Result.text = "Lose"
            }
        }
    }
}



