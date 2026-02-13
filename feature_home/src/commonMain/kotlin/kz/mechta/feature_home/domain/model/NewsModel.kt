package kz.mechta.feature_home.domain.model

internal data class NewsModel(
    val id: Int,
    val name: String,
    val code: String,
    val imageSrc: String,
    val previewText: String,
    val brandLogo: String?,
    val activeFrom: String?,
    val activeTo: String?,
    val daysBeforeExpiration: Int?,
    val linkUrl: String?,
    val isExternalLink: Boolean
)
