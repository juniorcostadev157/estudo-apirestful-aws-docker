package juniordev.com.estudando_spring_docker_aws.dto

import jakarta.validation.constraints.NotEmpty
import juniordev.com.estudando_spring_docker_aws.entity.User

data class AccountCredentialsDTO(
    @field: NotEmpty(message = "Invalid Input")val username: String? = null,
    @field: NotEmpty(message = "Invalid Input")val password:String? = null
)



