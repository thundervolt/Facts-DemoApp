package com.samples.factsdemoapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Fact details entity
 *
 * @author AkashG
 * @since 19/07/19.
 */
data class FactDetails(
    @SerializedName("title") val title: String, @SerializedName("description") val description: String, @SerializedName(
        "imageHref"
    ) val imagePath: String
)
