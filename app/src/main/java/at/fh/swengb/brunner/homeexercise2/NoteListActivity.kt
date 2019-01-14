package at.fh.swengb.brunner.homeexercise2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var myDb: NoteRoomDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)


        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("name", null)
        val savedInt = sharedPreferences.getInt("age", -1)
        textView_ShowInfo.text = "Notes for ${savedString}, ${savedInt}"

        myDb = NoteRoomDatabase.getDatabase(applicationContext)

        note_recycler_View.layoutManager = LinearLayoutManager(this)

        noteAdapter = NoteAdapter()
        note_recycler_View.adapter = noteAdapter

        val dbItems = myDb.noteDao.findAllNotes()
        noteAdapter.updateData(dbItems)

    }



    override fun onResume(){
        super.onResume()
        val dbItems = myDb.noteDao.findAllNotes()
        noteAdapter.updateData(dbItems)
    }



    fun openNoteAdd(v: View){
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

}
