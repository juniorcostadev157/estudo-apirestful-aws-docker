package juniordev.com.estudando_spring_docker_aws.exception

data class BusinessException(override val message:String?): RuntimeException(message)