package juniordev.com.estudando_spring_docker_aws.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder( "message", "_links")
data class MessageResponse(
    val message:String
):RepresentationModel<MessageResponse>()
