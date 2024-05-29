package com.movie.model

import com.movie.dto.ProfileDto
import javax.persistence.*

@Entity
@Table(name = "profiles")
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(name = "u_id")
        val UId: Long,

        @Column(name = "p_name")
        val pName: String
) {
    companion object {
        @JvmStatic
        fun convertToDto(profile: Profile): ProfileDto {
            return ProfileDto(
                    profile.id,
                    profile.UId,
                    profile.pName
            )
        }
    }
}
