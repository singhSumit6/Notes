package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
 class Note(@ColumnInfo(name = "text_note") var text: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}