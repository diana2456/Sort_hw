package com.example.sort_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.sort_hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private var hashtags = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        hashtags.add("")
        binding.etMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {
            }

            override fun afterTextChanged(a0: Editable?) {
                val matchingNumber = hashtags.find { it.contains(binding.etMessage.text.toString())}
                if (matchingNumber != null && hasHashtag(hashtags)) {
                        binding.tvHashtags.setText(matchingNumber)
                    binding.tvHashtags.setOnClickListener {
                        binding.etMessage.setText(binding.tvHashtags.text.toString())
                    }
                }
            }
        })
        binding.btnClick.setOnClickListener {
            if (binding.etMessage.text.isNotBlank()){
                hashtags.add (binding.etMessage.text.toString())
                binding.etMessage.text.clear()
            }
        }
    }
}
    fun hasHashtag(array: ArrayList<String>):Boolean{
        for (data in array){
            if (data.contains("#")){
                return true
            }
        }
        return false
    }
