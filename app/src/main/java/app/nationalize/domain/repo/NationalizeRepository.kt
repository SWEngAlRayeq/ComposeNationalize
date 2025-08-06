package app.nationalize.domain.repo

import app.nationalize.data.model.NationalizeResponse

interface NationalizeRepository {
    suspend fun getNationalities(name: String): NationalizeResponse
}