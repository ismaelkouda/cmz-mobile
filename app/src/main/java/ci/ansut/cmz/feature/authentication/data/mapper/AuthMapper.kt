package ci.ansut.cmz.feature.authentication.data.mapper

import ci.ansut.cmz.feature.authentication.data.remote.dto.UserDto
import ci.ansut.cmz.feature.authentication.domain.model.User

object AuthMapper {

    fun toDomain(
        dto: UserDto,
    ): User {
        return User(
            id = dto.id,
            email = dto.email,
            displayName = dto.displayName,
            photoUrl = dto.photoUrl,
        )
    }
}