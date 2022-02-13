package com.bayu.instagramhomepage.ui.utils

object Data {

    val dummyDataPosts: List<Post> = listOf(
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x300",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x301",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x302",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x303",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x304",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x305",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x306",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x307",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x308",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x309",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x310",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x311",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x300",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x301",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x302",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x303",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x304",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x305",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x306",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x307",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x308",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x309",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x310",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x311",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x300",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x301",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x302",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x303",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x304",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x305",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x306",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x307",
        ),
        Post(
            TypePost.Shopping,
            "https://source.unsplash.com/random/300x308",
        ),
        Post(
            TypePost.Video,
            "https://source.unsplash.com/random/300x309",
        ),
        Post(
            TypePost.Collections,
            "https://source.unsplash.com/random/300x310",
        ),
        Post(
            TypePost.Default,
            "https://source.unsplash.com/random/300x311",
        ),
    )

    val dummyDataReels: List<Reel> = listOf(
        Reel(
            user = User(
                profile = "https://source.unsplash.com/random/100x100",
                username = "John Dow",
                follow = false,
            ),
            description = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            tags = listOf("#Music", "#Comedy", "#Morning"),
            music = Music(
                name = "Fly Me to The Moon",
                author = "",
                isOriginalAudio = true,
                profile = "https://source.unsplash.com/random/100x101",
            ),
            likes = "11K",
            comment = "23",
            video = "sd_1.mp4",
        ),
        Reel(
            user = User(
                profile = "https://source.unsplash.com/random/100x102",
                username = "kye_fry",
                follow = true,
            ),
            description = "My Happy Baby",
            tags = listOf("#babies", "#happy", "#smile", "#laugh"),
            music = Music(
                name = "kye_fry",
                author = "",
                isOriginalAudio = true,
                profile = "https://source.unsplash.com/random/100x103",
            ),
            likes = "19.8K",
            comment = "106",
            video = "sd_3.mp4",
        ),
        Reel(
            user = User(
                profile = "https://source.unsplash.com/random/100x104",
                username = "duniaindah",
                follow = false,
            ),
            description = "Beautiful Night is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            tags = listOf("#night", "#indonesia", "#malam", "#tidur"),
            music = Music(
                name = "WG Genk",
                author = "Genk Kotok",
                isOriginalAudio = false,
                profile = "https://source.unsplash.com/random/100x105",
            ),
            likes = "134K",
            comment = "1,512",
            video = "sd_2.mp4",
        ),
    )
}

data class Post(
    val typePost: TypePost,
    val image: String,
)

data class Reel(
    val user: User,
    val description: String,
    val tags: List<String>,
    val music: Music,
    val likes: String,
    val comment: String,
    val video: String
) {
    fun getUriVideo(): String = "asset:///${video}"
}

data class User(
    val profile: String,
    val username: String,
    val follow: Boolean,
)

data class Music(
    val name: String,
    val author: String,
    val isOriginalAudio: Boolean,
    val profile: String,
)