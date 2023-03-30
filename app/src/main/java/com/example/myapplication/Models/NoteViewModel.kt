package com.example.myapplication.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.NoteDatabase
import com.example.myapplication.Database.NotesRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val repository : NotesRepositories

    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepositories(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO ) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch ( Dispatchers.IO ) {
        repository.insert(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch ( Dispatchers.IO ) {
        repository.update(note)
    }

}