package app.nationalize.data.model

data class NationalizeResponse(
    val name: String,
    val country: List<CountryPrediction>
)

data class CountryPrediction(
    val country_id: String,
    val probability: Double
)