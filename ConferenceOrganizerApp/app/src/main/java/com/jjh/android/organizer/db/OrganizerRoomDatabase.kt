package com.jjh.android.organizer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jjh.android.organizer.model.Track
import com.jjh.android.organizer.model.Session

@Database(entities = [Session::class, Track::class], version = 1)
abstract class OrganizerRoomDatabase : RoomDatabase() {

    abstract fun trackDao(): TrackDao
    abstract fun sessionDao(): SessionDao
    abstract fun tracksWithSessionsDao(): TracksWithSessionsDao

    companion object {
        private var singleton: OrganizerRoomDatabase? = null
        fun getDatabase(context: Context): OrganizerRoomDatabase {
            if (singleton == null) {
                synchronized(OrganizerRoomDatabase::class.java) {
                    if (singleton == null) {
                        singleton = Room.databaseBuilder(
                            context.applicationContext,
                            OrganizerRoomDatabase::class.java,
                            "conf_org_db"
                        ).build()
                    }
                }
            }
            return singleton!!
        }
    }
}