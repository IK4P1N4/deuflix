package com.movie.dto

import com.movie.model.Profile

data class ProfileDto(
        val id: Int,
        val UId: Long,
        val pName: String
) {
    companion object {
        @JvmStatic
        fun convert(from: Profile): ProfileDto {
            return ProfileDto(
                    from.id,
                    from.UId,
                    from.pName
            )
        }
    }
}
