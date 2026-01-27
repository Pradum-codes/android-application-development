package com.pradumcodes.notes.data

import com.pradumcodes.notes.domain.NoteRepository
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor (
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(title: String, content: String) {
        noteDao.insertNote(Note(title = title, content = content))
    }

    override suspend fun getAllNotes() = noteDao.getAllNotes()
}