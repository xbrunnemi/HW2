package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.*


@Dao
interface NoteDao{

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * FROM no_te")
    fun findAllNotes(): List<Note>

    @Query("UPDATE no_te SET title = :newTitle, content = :newContent where id = :id")
    fun updateNote(id: Long, newTitle: String, newContent: String)

    @Query("SELECT title FROM no_te WHERE id = :id")
    fun getTitle(id: Int): String

    @Query("SELECT content FROM no_te WHERE id = :id")
    fun getContent(id: Int): String

    @Query("Select * From no_te Where userId = :id")
    fun findUserNotes(id: Int): List<Note>

    @Query ("Delete From no_te Where id = :noteId")
    fun deleteNote(noteId: Int)

    @Query("Select * From userTable where id = :userId")
    fun findAllUserAndNotesById(userId: Long): UserNote

    @Query("Select * From userTable")
    fun findAllUserAndNotes(): List<UserNote>

    @Query("Delete From no_te")
    fun deleteAllNotes()
}

class UserNote(){
    @Embedded
    lateinit var user: User

    @Relation(entity = Note::class, entityColumn = "userId", parentColumn = "id")
    lateinit var note: List<Note>
}