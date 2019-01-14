package at.fh.swengb.brunner.homeexercise2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun openNoteList(v: View){
        if (editText_MName.text.isEmpty() or editText_MAge.text.isEmpty()){
            return
        } else {
            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("name", editText_MName.text.toString()).apply()
            sharedPreferences.edit().putInt("age", editText_MAge.text.toString().toInt()).apply()


            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)

        }

    }
}
