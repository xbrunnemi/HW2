package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM userTable")
    fun findAllUser(): List<User>

    @Query("SELECT count(*) FROM userTable WHERE username = :name")
    fun checkUser(name: String): Int

    @Query("SELECT id FROM userTable WHERE username = :name")
    fun getId(name: String): Int

}