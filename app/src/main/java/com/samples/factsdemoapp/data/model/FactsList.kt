package com.samples.factsdemoapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Facts list entity
 *
 * @author AkashG
 * @since 19/07/19.
 */
data class FactsList(@SerializedName("title") val title: String, @SerializedName("rows") val factsList: List<FactDetails>)