package com.example.sort_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.sort_hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { Adapter(this::setText) }
    private val savedTags = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClicker()
    }

    private fun initClicker() {
        initAdapter()
        saveTag()
        showText()
    }

    private fun initAdapter() {
        binding.rvHints.adapter = adapter
    }

    private fun saveTag() {
        binding.btnSend.setOnClickListener {
            savedTags.addAll(findTag(binding.etText.text.toString()))
            binding.etText.text.clear()
        }
    }

    private fun showText() {
        binding.etText.addTextChangedListener {
            adapter.addData(findInSaved(binding.etText.text.toString()))
        }
    }


    private fun findTag(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val message = text.split(" ")
        for (i in message) {
            if (i.startsWith("#")) {
                result.add(i)
            }
        }
        return result
    }

    private fun findInSaved(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val onlineTag = findTag(text)
        for (tag in onlineTag) {      // 2. find onlineTag in savedTags ->
            for (savedTag in savedTags) {
                if (savedTag.contains(tag) && savedTag != tag)
                    result.add(savedTag)
            }
        }
        return result
    }

    private fun setText(newTag: String) {
        val message = binding.etText.text.toString()
        val splitMessage = message.split(" ").toMutableList()
        val tagIndex = findIndex(splitMessage, message)
        splitMessage[tagIndex] = newTag
        binding.etText.setText(splitMessage.joinToString(" "))
        binding.etText.setSelection(binding.etText.text.length)
    }

    private fun findIndex(splitMessage: MutableList<String>, message: String): Int {
        for (i in findTag(message)) {
            if (!savedTags.contains(i))
                return splitMessage.indexOf(i)
        }
        return 0
    }
}