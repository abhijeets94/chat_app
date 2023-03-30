package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.Models.Note
import com.example.myapplication.databinding.ActivityAddNotesBinding
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class AddNotes : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding

    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etName.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.checkIcon.setOnClickListener {
            val title = binding.etName.text.toString()
            val note_desc = binding.etNote.text.toString()
            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE, d MMM YYYY HH:mm a")
                if (isUpdate) {
                    note = Note(
                        old_note.id, title, note_desc, formatter.format(Date())
                    )

                } else {
                    note = Note(
                        null, title, note_desc, formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddNotes, "Please enter the values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.backArrow.setOnClickListener{
            onBackPressed()
        }
    }
}