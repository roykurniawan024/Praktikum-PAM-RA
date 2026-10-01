package com.example.praktikum2_124140024

import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class News(
    val id: Int,
    val title: String,
    val category: String
)

class NewsManager {
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    fun incrementReadCount() {
        _readCount.value++
    }
}

fun newsFlow(): Flow<News> = flow {
    var id = 1
    val categories = listOf("Tech", "Sports", "Politics")

    while (id <= 7) {
        delay(1500)
        val category = categories.random()
        emit(News(id, "Berita Terkini $id", category))
        id++
    }
}

suspend fun fetchNewsDetail(newsId: Int): String {
    delay(1000)
    return "Teks detail lengkap untuk berita ID-$newsId"
}

class NewsFeedTest {

    @Test
    fun runSimulator() = runBlocking {
        val newsManager = NewsManager()

        val monitor = launch {
            newsManager.readCount.collect { count ->
                println("Total Berita Dibaca: $count")
            }
        }

        newsFlow()
            .filter { it.category == "Tech" }
            .map { "[TECH] ${it.title}" to it }
            .catch { println("Error stream: ${it.message}") }
            .collect { (display, news) ->
                println(display)

                launch(Dispatchers.Default) {
                    val detailDeferred = async { fetchNewsDetail(news.id) }
                    val detail = detailDeferred.await()

                    println("Detail [${news.id}]: $detail")
                    newsManager.incrementReadCount()
                }
            }

        delay(2000)
        monitor.cancel()
    }
}