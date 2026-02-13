package kz.mechta.feature_home.domain.model

internal data class PublicationModel(
    val type: String,
    val image: String,
    val slug: String,
    val title: String,
    val previewText: String,
    val fromDate: String?,
    val toDate: String?
)