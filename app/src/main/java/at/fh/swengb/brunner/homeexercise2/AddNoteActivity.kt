package at.fh.swengb.brunner.homeexercise2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity(){

    private lateinit var myDb: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        myDb = NoteRoomDatabase.getDatabase(this)
    }

    fun openNoteList(v: View){

        val titleText = editText_ANTitle.text.toString()
        val contentText = editText_ANContent.text.toString()

        if (titleText.isEmpty() || contentText.isEmpty()){
            return
        }

        val newNote = Note(titleText, contentText)
        myDb.noteDao.insert(newNote)
        myDb.noteDao.findAllNotes()

        finish()
    }

}
