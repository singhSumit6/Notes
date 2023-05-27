package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INoteRVAdapter {

    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(list)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val text = findViewById<EditText>(R.id.input)
        var input = text.text.toString()
        text.setText(" ")
        if(input.isNotEmpty()){
            viewModel.insertNote(Note(input))
            Toast.makeText(this, "$input inserted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Please add your task", Toast.LENGTH_SHORT).show()
        }
    }
}