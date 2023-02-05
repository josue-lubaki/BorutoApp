package ca.josuelubaki.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        context: Context,
        imageUrl : String,
    ) : Bitmap? {
        val loader = ImageLoader(context= context)

        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val imageResult = loader.execute(request)

        return if(imageResult is SuccessResult) {
            imageResult.drawable.toBitmap()
        } else {
            null
        }
    }

    fun extractColorFromBitmap(bitmap: Bitmap) : Map<String, String> {
        return mapOf(
            "vibrant" to Palette.from(bitmap).generate().vibrantSwatch.toHex(),
            "darkVibrant" to Palette.from(bitmap).generate().darkVibrantSwatch.toHex(),
            "onDarkVibrant" to Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor.toHex(),
        )
    }

    private fun Palette.Swatch?.toHex() : String {
        return if(this != null) {
            String.format("#%06X", 0xFFFFFF and this.rgb)
        } else {
            "#000000"
        }
    }

    private fun Int?.toHex() : String {
        return if(this != null) {
            String.format("#%06X", 0xFFFFFF and this)
        }
        else {
            "#FFFFFF"
        }
    }

}