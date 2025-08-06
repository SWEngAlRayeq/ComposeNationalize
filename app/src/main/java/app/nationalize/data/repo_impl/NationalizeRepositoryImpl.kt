package app.nationalize.data.repo_impl

import app.nationalize.data.model.NationalizeResponse
import app.nationalize.data.remote.NationalizeApi
import app.nationalize.domain.repo.NationalizeRepository
import javax.inject.Inject

class NationalizeRepositoryImpl @Inject constructor(
    private val nationalizeApi: NationalizeApi
) : NationalizeRepository {
    override suspend fun getNationalities(name: String): NationalizeResponse {
        return nationalizeApi.predictNationality(name)
    }
}