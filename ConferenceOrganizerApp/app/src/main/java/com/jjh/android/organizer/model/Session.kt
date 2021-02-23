package com.jjh.android.organizer.model

import androidx.room.*

@Entity(tableName = "sessions")
data class Session(@PrimaryKey(autoGenerate = true) val id: Int,
                   val title: String,
                   val description: String,
                   val presenter: String,
                   @ColumnInfo(name = "track_id")val trackId: Int,
                   @ColumnInfo(name = "start_time") val startTime: String,
                   @ColumnInfo(name = "end_time")val endTime: String)

@Entity(tableName = "tracks")
data class Track(@PrimaryKey(autoGenerate = true) val id: Int,
                 val name: String)

data class TrackWithSessions(
    @Embedded val track: Track,
    @Relation(
        parentColumn = "id",
        entityColumn = "track_id"
    )
    val sessions: List<Session>
)
