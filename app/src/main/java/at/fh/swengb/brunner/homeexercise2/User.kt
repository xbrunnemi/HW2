package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userTable")
class User (val username: String, val age: Int){

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    
}