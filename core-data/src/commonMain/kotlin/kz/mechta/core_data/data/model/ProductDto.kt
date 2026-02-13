package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
    @SerialName("slug") val slug: String?,
    @SerialName("code") val code: String?,
    @SerialName("numericId") val numericId: Int?,
    @SerialName("images") val images: List<String>?,
    @SerialName("preview") val preview: String?,
    @SerialName("availability") val availability: String?,
    @SerialName("preorder") val preorder: PreorderDto?,
    @SerialName("prices") val prices: PricesDto?,
    @SerialName("stickers") val stickers: List<StickerDto>?,
    @SerialName("mainProperties") val mainProperties: List<PropertyDto>?,
    @SerialName("categories") val categories: List<CategoryDto>?,
    @SerialName("rating") val rating: RatingDto?,
    @SerialName("shipment") val shipment: ShipmentDto?,
    @SerialName("propertyGroups") val propertyGroups: List<PropertyGroupDto>?,
    @SerialName("metrics") val metrics: MetricsDto?,
    @SerialName("onlyShopwindow") val isShowcase: Boolean?
)