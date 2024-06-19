package com.ashfaque.demogeminijetpack

import android.annotation.SuppressLint
import com.google.ai.client.generativeai.GenerativeModel
import java.text.SimpleDateFormat
import java.util.Date

class Utils {

    @SuppressLint("SimpleDateFormat")
     fun getCurrentDateTime(format:String?):String
    {
        if(format.isNullOrBlank())
        {
            return ""
        }
        val dateFormat = SimpleDateFormat(format)
        val date = Date()
        return dateFormat.format(date)
    }


    val generativeModel = GenerativeModel(
        // The Gemini 1.5 models are versatile and work with both text-only and multimodal prompts
        modelName = "gemini-1.5-flash",
        apiKey = apiKey1+ apiKey2+ apiKey3
    )

}
