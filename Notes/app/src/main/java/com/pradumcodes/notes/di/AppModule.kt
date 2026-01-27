package com.pradumcodes.notes.di

import android.content.Context
import androidx.room.Room
import com.pradumcodes.notes.data.NoteDao
import com.pradumcodes.notes.data.NoteRepositoryImpl
import com.pradumcodes.notes.data.NotesDatabase
import com.pradumcodes.notes.domain.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }

    @Provides
    fun providesNoteDao(
        db: NotesDatabase
    ) = db.noteDao()

    @Provides
    @Singleton
    fun providesNotesRpository(
        dao : NoteDao
    ) : NoteRepository = NoteRepositoryImpl(dao)
}