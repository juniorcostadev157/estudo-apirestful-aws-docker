package juniordev.com.estudando_spring_docker_aws.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.contentType = "application/json;charset=UTF-8"
        response.status = HttpStatus.FORBIDDEN.value()

        val errorResponse = mapOf(
            "title" to "Acesso Negado",
            "timestamp" to LocalDateTime.now().toString(),
            "status" to HttpStatus.FORBIDDEN.value(),
            "exception" to accessDeniedException.javaClass.simpleName,
            "details" to mapOf("message" to (accessDeniedException.message ?: "Acesso negado ao recurso solicitado."))
        )

        ObjectMapper().writeValue(response.writer, errorResponse)
    }
}
