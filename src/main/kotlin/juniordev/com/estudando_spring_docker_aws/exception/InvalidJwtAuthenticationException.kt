package juniordev.com.estudando_spring_docker_aws.exception

import org.springframework.security.core.AuthenticationException

data class InvalidJwtAuthenticationException(override val message:String?): AuthenticationException(message)