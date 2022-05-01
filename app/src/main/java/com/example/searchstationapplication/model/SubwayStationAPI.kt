package com.example.searchstationapplication.model

import com.example.searchstationapplication.model.dto.SubWayStationDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface SubwayStationAPI {
   @GET("v1/filter/subway/version/1")
   suspend fun getSubWayStationData(): Response<SubWayStationDataResponse>
}