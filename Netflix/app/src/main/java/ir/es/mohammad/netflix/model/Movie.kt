package ir.es.mohammad.netflix.model
import android.graphics.drawable.Drawable
import java.text.DecimalFormat

data class Movie(val title: String, val image: String?){
    var isFavorite = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        return image?.hashCode() ?: 0
    }


}