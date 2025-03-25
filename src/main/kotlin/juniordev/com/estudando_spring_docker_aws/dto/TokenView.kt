package juniordev.com.estudando_spring_docker_aws.dto

import java.util.Date

data class TokenView(
    val username:String? = null,
    val authenticated:Boolean? = null,
    val created:Date? = null,
    val expiration: Date? = null,
    val accessToken:String? = null,
    val refreshToken: String?  = null,

)
