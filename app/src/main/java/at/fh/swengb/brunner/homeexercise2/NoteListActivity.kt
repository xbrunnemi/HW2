package at.fh.swengb.brunner.homeexercise2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
        val id = sharedPreferences.getInt("id", -1)
        textView_ShowInfo.text = "Notes for ${savedString}, ${savedInt}"

        noteAdapter = NoteAdapter({
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra("NoteId", it.id)
            startActivity(intent)
        }, {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Delete this Note")
            dialogBuilder.setMessage("Are you sure you want to delete this Note?")
            dialogBuilder.setPositiveButton("Yes") {
                    _,_ -> myDb.noteDao.deleteNote(it.id)
                    val dbItems = getUserNote(id)
                    noteAdapter.updateData(dbItems)
            }
            dialogBuilder.setNegativeButton("No", null)
            dialogBuilder.show()
        })

        myDb = NoteRoomDatabase.getDatabase(this)
        note_recycler_View.adapter = noteAdapter
        note_recycler_View.layoutManager = LinearLayoutManager(this)

        val dbItems = getUserNote(id)
        noteAdapter.updateData(dbItems)

    }

    private fun getUserNote(id: Int): List<Note>{
        return myDb.noteDao.findUserNotes(id)
    }

    override fun onResume(){
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("id", -1)
        val dbItems = getUserNote(id)
        noteAdapter.updateData(dbItems)
        super.onResume()
    }

    fun openNoteAdd(v: View){
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    fun userLogout(view: View){
        getSharedPreferences(packageName, Context.MODE_PRIVATE).edit().clear().apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
