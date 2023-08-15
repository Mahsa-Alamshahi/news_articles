package org.newsapi.newsarticles.data.data_source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news_article")
data class NewsArticleEntity(

    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "published_at") var publishedAt: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "image_url") var imageUrl: String? = null,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
