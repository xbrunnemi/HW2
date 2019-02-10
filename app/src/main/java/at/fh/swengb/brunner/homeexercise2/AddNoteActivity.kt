package at.fh.swengb.brunner.homeexercise2

import android.content.Context
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

        val noteId = intent.getIntExtra("NoteId", -1)

        myDb = NoteRoomDatabase.getDatabase(this)

        if( noteId != -1){
            val title = getTitle(noteId)
            val content = getContent(noteId)

            editText_ANTitle.setText(title)
            editText_ANContent.setText(content)
        }
    }

    private fun getTitle(id: Int): String{
        return myDb.noteDao.getTitle(id)
    }

    private fun getContent(id: Int): String{
        return myDb.noteDao.getContent(id)
    }

    fun saveNote(view: View){
        val noteId = intent.getIntExtra("NoteId", -1)
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("id", -1)

        if(noteId == -1){
            val newNote = Note(editText_ANTitle.text.toString(), editText_ANContent.text.toString(), userId.toLong())
            myDb.noteDao.insert(newNote)
        } else {
            myDb.noteDao.updateNote(noteId.toLong(), editText_ANTitle.text.toString(), editText_ANContent.text.toString())
        }
        finish()
    }

    fun shareNote(view: View){
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT,
            "Title: ${editText_ANTitle.text.toString()} Content: ${editText_ANContent.text.toString()}")
        intent.type = "text/plain"
        val chooserIntent = Intent.createChooser(intent, "Please select an App")
        startActivity(chooserIntent)
    }

}
