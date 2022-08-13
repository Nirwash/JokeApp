package com.nirwashh.android.jokeapp

import io.realm.Realm

class BaseCacheDataSource(private val realm: Realm) : CacheDataSource {
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty()) {
                jokeCachedCallback.fail()
            }
            else {
                jokes.random().let { joke ->
                    jokeCachedCallback.provide(
                        Joke(
                            joke.text,
                            joke.id
                        )
                    )
                }
            }
        }
    }

    override fun addOrRemove(id: Int, joke: Joke): JokeUIModel {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = joke.toJokeRealm()
                it.executeTransactionAsync { transaction ->
                    transaction.insert(newJoke)
                }
                joke.toFavoriteJoke()
            } else {
                it.executeTransactionAsync {
                    jokeRealm.deleteFromRealm()
                }
                joke.toBaseJoke()
            }
        }
    }
}