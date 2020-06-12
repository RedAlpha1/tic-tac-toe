package com.library.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){
private var player1 = ArrayList<Int>()
private var player2 = ArrayList<Int>()
    private var activePlayer=1;
    var playerWinsCount1=0
    var playerWinsCount2=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun funCLick(view: View)
    {
        var cell_id=0;
        val valueofbuttonId = view as Button
        when(valueofbuttonId.id)
        {
            R.id.btn_1->cell_id=1
            R.id.btn_2->cell_id=2
            R.id.btn_3->cell_id=3
            R.id.btn_4->cell_id=4
            R.id.btn_5->cell_id=5
            R.id.btn_6->cell_id=6
            R.id.btn_7->cell_id=7
            R.id.btn_8->cell_id=8
            R.id.btn_9->cell_id=9
        }
        playButton(cell_id,valueofbuttonId)
    }

    private fun playButton(cellId: Int, valueofbuttonId: Button) {
        if(activePlayer==1)
        {
            valueofbuttonId.text="X"
            valueofbuttonId.setBackgroundResource(R.color.colorPrimary)
            player1.add(cellId)
            activePlayer=2
            autoplayer()
        }else
        {
            valueofbuttonId.text="0"
            valueofbuttonId.setBackgroundResource(R.color.colorAccent)
            player2.add(cellId)
            activePlayer=1
        }
        valueofbuttonId.isEnabled=false

        checkWinner()
    }

    private fun autoplayer()
    {
       var emptyList = ArrayList<Int>()
        for(cellid in 1..9)
        {
            if(!player1.contains(cellid)||!player2.contains(cellid))
            {
                emptyList.add(cellid)
            }
        }

        var r = Random();
        var randomIndex = r.nextInt(emptyList.size)
        var cellId = emptyList[randomIndex]
        var buttonId : Button
        buttonId= when(cellId)
        {
            1-> btn_1
            2-> btn_2
            3-> btn_3
            4-> btn_4
            5-> btn_5
            6->btn_6
            7->btn_7
            8->btn_8
            9->btn_9
            else->{btn_1}
        }
        playButton(cellId,buttonId)
    }

    private fun checkWinner() {
        var winner=-1;
        playerWinsCount1=0
        playerWinsCount2=0
        //row 1
        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3))
        {
            winner=1
        }

        if(player2.contains(1)&&player2.contains(2)&&player2.contains(3))
        {
            winner=2
        }

        // row 2
        if(player1.contains(4)&&player1.contains(5)&&player1.contains(6))
        {
            winner=1
        }
        if(player2.contains(4)&&player2.contains(5)&&player2.contains(6))
        {
            winner=2
        }

        // row 3
        if(player1.contains(7)&&player1.contains(8)&&player1.contains(9))
        {
            winner=1
        }
        if(player2.contains(7)&&player2.contains(8)&&player2.contains(9))
        {
            winner=2
        }

        //col 1
        if(player1.contains(1)&& player1.contains(4)&&player1.contains(7))
        {
            winner=1
        }
        if(player2.contains(1)&& player2.contains(4)&&player2.contains(7))
        {
            winner=2
        }
        //col 2
        if(player1.contains(2)&& player1.contains(5)&&player1.contains(8))
        {
            winner=1
        }
        if(player2.contains(2)&& player2.contains(5)&&player2.contains(8))
        {
            winner=2
        }
        //col 3
        if(player1.contains(3)&& player1.contains(6)&&player1.contains(9))
        {
            winner=1
        }
        if(player2.contains(3)&& player2.contains(6)&&player2.contains(9))
        {
            winner=2
        }
        //Diagonal 1
        if(player1.contains(1)&& player1.contains(5)&&player1.contains(9))
        {
            winner=1
        }
        if(player2.contains(1)&& player2.contains(5)&&player2.contains(9))
        {
            winner=2
        }
        if(player1.contains(3)&& player1.contains(5)&&player1.contains(9))
        {
            winner=1
        }
        if(player2.contains(3)&& player2.contains(5)&&player2.contains(9))
        {
            winner=2
        }

        if(winner==1)
        {
            playerWinsCount1+=1
            Toast.makeText(this,"Player one is win",Toast.LENGTH_LONG).show()
            resetGame()
        }
        else if(winner==2) {
            playerWinsCount2 += 1
            Toast.makeText(this, "Player two is win", Toast.LENGTH_LONG).show()
              resetGame()
        }
    }

    private fun resetGame() {
       activePlayer=1;
        player1.clear()
        player2.clear()
        var buttonSelected:Button?
        for(cellid in 1..9)
        {
            buttonSelected= when(cellid)
            {
                1-> btn_1
                2-> btn_2
                3-> btn_3
                4-> btn_4
                5-> btn_5
                6->btn_6
                7->btn_7
                8->btn_8
                9->btn_9
                else->{btn_1}
            }
            buttonSelected.text="";
            buttonSelected.setBackgroundResource(R.color.defaultColor)
            buttonSelected.isEnabled=true

            Toast.makeText(this,"Player1 wins $playerWinsCount1 and Player2 wins $playerWinsCount2",Toast.LENGTH_LONG).show()
        }
    }
}
