package juniordev.com.estudando_spring_docker_aws.exception


import java.time.LocalDateTime

data class ExceptionDetails(

    val title:String,
    val timestamp: LocalDateTime,
    val status:Int,
    val exception: String,
    val details: MutableMap<String, String>

)
