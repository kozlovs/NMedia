package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.marginLeft
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий",
            content = "Привет! Это новая нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            likesCount.text = numberToShortFormat(post.like)
            shareCount.text = "${post.share}"
            viewCount.text = "${post.view}"

            likeButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    post.like++
                    likesCount.text = numberToShortFormat(post.like)
                    likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    post.like--
                    likesCount.text = numberToShortFormat(post.like)
                    likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }

            shareButton.setOnClickListener {
                post.share++
                shareCount.text = numberToShortFormat(post.share)
            }
        }
    }

    private fun numberToShortFormat(number: Int): String {
        return when {
            number in  1_000..1_099 -> "1K"
            number in  1_100..9_999 && number % 1000 == 0 -> "${number / 1000}K"
            number in  1_100..9_999 && number % 1000 != 0 -> ((number / 100).toDouble() / 10).toString() + "K"
            number in 10_000..999_999 -> "${number / 1000}K"
            number >= 1_000_000 && number % 1000000 == 0 -> "${number / 1000000}M"
            number >= 1_000_000 && number % 1000000 != 0 -> ((number / 100000).toDouble() / 10).toString() + "M"
            else -> number.toString()
        }
    }
}