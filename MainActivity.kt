package com.example.bid
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "com.example.bid.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun endBid(){
        val message = "Noice!, the bidding has ended and the final bid is $target , Player ${keys.elementAt(i)} and his teammate ${keys.elementAt((i+2)%4)} have to achieve it"
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
        finish()

    }
    val player = mutableMapOf(1 to 0, 2 to 0, 3 to 0, 4 to 0)
    var keys = player.keys // mutableSet
    var bid = 16
    var i : Int = 0
    var target = 0
    var bidcount =0 // used to keep count of bids (not passes) so as to keep bid amt same for stay option
    var arr: MutableList<Int> = mutableListOf() //already bidded, so stay option should be visible

    fun announceScore(){
        findViewById<TextView>(R.id.textView).apply {
            text = "Current bid is $target"
        }

        val playerCall: String = "Player: ${keys.elementAt(i)}'s move"
        findViewById<TextView>(R.id.textView3).apply {
            text = playerCall
        }

        val bidamt: String = "BID : $bid"
        findViewById<Button>(R.id.button).apply {
            text = bidamt
        }
        if(target >= 16){
            findViewById<Button>(R.id.button6).apply {
                this.visibility = View.VISIBLE
                text = "PASS"

            }
        }
    }

    fun bidded(view: View) {
        if(keys.elementAt(i) in arr){              // To STAY or for the challenger to raise the bid
            player[keys.elementAt(i)] = target
            if(bidcount%2==0){bid+=1}
            else{target = bid}
            i = if(i==0) 1 else 0
            announceScore()
        }
        else{                                      // To BID
            target = bid
            if(bidcount%2==0){bid+=1}

            player[keys.elementAt(i)] = target
            arr.add(keys.elementAt(i))
            i = if(i==0) 1 else 0
            announceScore()
        }
        bidcount+=1

    }

    fun passed(view: View) {                      // To PASS
        keys.remove(keys.elementAt(i))
        if(keys.count()>1){
            i =  1
            announceScore()
        }
        else{
            // Start new Activity and display Target , bid over
            endBid()
        }
    }

}
