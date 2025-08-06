package app.nationalize.domain.usecase

import app.nationalize.data.model.NationalizeResponse
import app.nationalize.domain.repo.NationalizeRepository
import javax.inject.Inject

class GetNationalityUseCase @Inject constructor(
    private val repository: NationalizeRepository
) {
    suspend operator fun invoke(name: String): NationalizeResponse =
        repository.getNationalities(name)
}