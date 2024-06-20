package com.daffadev.selfmeter

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class InputData(val input_data: List<Int>)
data class PredictedResponse(
    val createdAt: String,
    val id: Int,
    val predicted_label: String,
    val predicted_percentages: Map<String, Double>
)

interface ApiService {
    @POST("predict")
    fun getPrediction(@Body inputData: InputData): Call<PredictedResponse>
}
