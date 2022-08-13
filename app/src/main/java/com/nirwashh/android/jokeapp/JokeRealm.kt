package com.nirwashh.android.jokeapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm : RealmObject() {
    var text: String = ""
    @PrimaryKey
    var id: Int = -1
}