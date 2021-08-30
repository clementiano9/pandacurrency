package com.minimaxstudios.pandacurrency.data

import com.google.gson.JsonObject

open class BaseResponse(val success: Boolean ) {

}
data class CountriesResponse(
  val success: Boolean,
  val terms: String,
  val privacy: String,
  val currencies: JsonObject,
)