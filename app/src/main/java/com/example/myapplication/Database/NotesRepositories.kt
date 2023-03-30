package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import com.example.myapplication.Models.Note

class NotesRepositories(private val noteDao: NoteDao) {

    var allNotes : LiveData<List<Note>> = noteDao.getNote()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note.id, note.title, note.note)
    }
}