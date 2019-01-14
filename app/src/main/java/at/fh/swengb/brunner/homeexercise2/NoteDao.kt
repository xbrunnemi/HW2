package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update


@Dao
interface NoteDao{

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * FROM no_te")
    fun findAllNotes(): List<Note>
}