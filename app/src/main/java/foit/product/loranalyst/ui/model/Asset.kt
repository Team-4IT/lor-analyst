package foit.product.loranalyst.ui.model


import com.google.gson.annotations.SerializedName

data class Asset(
    @SerializedName("fullAbsolutePath")
    val fullAbsolutePath: String = "",
    @SerializedName("gameAbsolutePath")
    val gameAbsolutePath: String = ""
)