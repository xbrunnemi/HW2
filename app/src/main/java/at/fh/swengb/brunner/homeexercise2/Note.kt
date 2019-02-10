package at.fh.swengb.brunner.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "no_te",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])

class Note(val title: String, val content: String, val userId: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}