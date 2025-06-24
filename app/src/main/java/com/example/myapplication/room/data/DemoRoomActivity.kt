package com.example.myapplication.room.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDemoRoomBinding
import com.example.myapplication.room.data.local.entity.userAndnote

class DemoRoomActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
       ActivityDemoRoomBinding.inflate(layoutInflater)
    }

    private val vm by viewModels<DemoRoomViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        vm.userAndNotesLiveData.observe(this) { userAndNotes: userAndnote? ->
            binding.textView.text = if (userAndNotes === null) {
                "No user"
            } else {
                val notesContent = userAndNotes.notes
                    .withIndex()
                    .joinToString(separator = "\n") { (index, note) ->
                        "    ${index + 1}: ${note.title}"
                    }

                // raw string """
                """
        | User: ${userAndNotes.user}
        | Notes count: ${userAndNotes.notes.size}
        | Notes content: $notesContent
        """.trimMargin()
            }
        }


        binding.buttonSave.setOnClickListener {
            vm.addNewData()
        }

        binding.buttonClear.setOnClickListener {
            vm.delete()
        }
    }
}