package com.example.marvelcomicappbykotlin.network

data class ComicProperty(
//    var code: Int,
//    var status: String,
    var data: ComicData
)

data class ComicData(
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: List<Comic>
)

data class Comic (
    var id: Int,
    var title: String,
    var description: String?,
    var thumbnail: Thumbnail,
    var creators: Creators
)

data class Thumbnail(
    var path: String, //@Json(name = "img_src")
    var extension: String
)

data class Creators(
    var available: Int,
    var items: List<Creator>
)

data class Creator(
    var name: String,
    var role: String
)