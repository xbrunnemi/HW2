package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "no_te")
class Note(val title: String, val content: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}