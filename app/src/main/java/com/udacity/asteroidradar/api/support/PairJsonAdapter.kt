package com.udacity.asteroidradar.api.support

import com.squareup.moshi.*
import com.udacity.asteroidradar.api.data.NearEarthObject

class PairJsonAdapter: JsonAdapter<Pair<String, Any?>>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Pair<String, Any?> {
        return Pair(reader.nextName() as String, reader.readJsonValue())
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Pair<String, Any?>?) {
        writer.name(value?.first as String).value(value?.second as String)
    }
}