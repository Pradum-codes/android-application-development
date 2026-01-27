package com.pradumcodes.notes.domain

import android.icu.text.CaseMap
import androidx.compose.runtime.MovableContent
import com.pradumcodes.notes.data.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    suspend fun insertNote(title: String, content: String)
    suspend fun getAllNotes(): Flow<List<Note>>
}