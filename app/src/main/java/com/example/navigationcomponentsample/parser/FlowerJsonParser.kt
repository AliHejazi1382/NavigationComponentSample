package com.example.navigationcomponentsample.parser

import android.util.JsonReader
import com.example.navigationcomponentsample.model.Flowers
import java.io.InputStream
import java.io.InputStreamReader

class FlowerJsonParser {

    fun parseJson(inputStream: InputStream): List<Flowers> {
        val reader = JsonReader(InputStreamReader(inputStream))

        return reader.use { reader ->
            readArray(reader)
        }
    }

    private fun readArray(reader: JsonReader): List<Flowers> {
        val flowerList = ArrayList<Flowers>()

        reader.beginArray()
        while (reader.hasNext()) {
            flowerList.add(readObject(reader))
        }
        reader.endArray()
        return flowerList
    }

    private fun readObject(reader: JsonReader): Flowers {
        var name: String? = null
        var category: String? = null
        var instructions: String? = null
        var productId = 0
        var price = 0.0
        var photo: String? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "category" -> category = reader.nextString()
                "instructions" -> instructions = reader.nextString()
                "name" -> name = reader.nextString()
                "price" -> price = reader.nextDouble()
                "productId" -> productId = reader.nextInt()
                "photo" -> photo = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return Flowers(productId, name, category, instructions, photo, price)
    }
}