package foit.product.loranalyst.ui.model


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("artistName")
    val artistName: String = "",
    @SerializedName("assets")
    val assets: List<Asset> = listOf(),
    @SerializedName("associatedCardRefs")
    val associatedCardRefs: List<Any> = listOf(),
    @SerializedName("associatedCards")
    val associatedCards: List<Any> = listOf(),
    @SerializedName("attack")
    val attack: Int = 0,
    @SerializedName("cardCode")
    val cardCode: String = "",
    @SerializedName("collectible")
    val collectible: Boolean = false,
    @SerializedName("cost")
    val cost: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("descriptionRaw")
    val descriptionRaw: String = "",
    @SerializedName("flavorText")
    val flavorText: String = "",
    @SerializedName("health")
    val health: Int = 0,
    @SerializedName("keywordRefs")
    val keywordRefs: List<String> = listOf(),
    @SerializedName("keywords")
    val keywords: List<String> = listOf(),
    @SerializedName("levelupDescription")
    val levelupDescription: String = "",
    @SerializedName("levelupDescriptionRaw")
    val levelupDescriptionRaw: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("rarity")
    val rarity: String = "",
    @SerializedName("rarityRef")
    val rarityRef: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("regionRef")
    val regionRef: String = "",
    @SerializedName("set")
    val `set`: String = "",
    @SerializedName("spellSpeed")
    val spellSpeed: String = "",
    @SerializedName("spellSpeedRef")
    val spellSpeedRef: String = "",
    @SerializedName("subtype")
    val subtype: String = "",
    @SerializedName("subtypes")
    val subtypes: List<Any> = listOf(),
    @SerializedName("supertype")
    val supertype: String = "",
    @SerializedName("type")
    val type: String = ""
)