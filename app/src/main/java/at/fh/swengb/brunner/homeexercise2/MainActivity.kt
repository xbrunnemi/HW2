package at.fh.swengb.brunner.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myDb: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDb = NoteRoomDatabase.getDatabase(this)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("id", -1)

        if(id != -1){
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun openNoteList(v: View){
        if (editText_MName.text.isEmpty() or editText_MAge.text.isEmpty()){
            return
        } else {
            val name = editText_MName.text.toString()

            if (myDb.userDao.checkUser(name) == 0){
                val newUser = User(editText_MName.text.toString(), editText_MAge.text.toString().toInt())
                myDb.userDao.insert(newUser)
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("name", name).apply()
                sharedPreferences.edit().putInt("age", editText_MAge.text.toString().toInt()).apply()
                sharedPreferences.edit().putInt("id", getIdNumber(name).toInt()).apply()
            }else{
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("name", name).apply()
                sharedPreferences.edit().putInt("age", editText_MAge.text.toString().toInt()).apply()
                sharedPreferences.edit().putInt("id", getIdNumber(name).toInt()).apply()
            }


            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getIdNumber(name: String): Int{
        return myDb.userDao.getId(name)
    }
}
