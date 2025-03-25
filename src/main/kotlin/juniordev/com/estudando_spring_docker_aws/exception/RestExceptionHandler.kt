package juniordev.com.estudando_spring_docker_aws.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun genericException(ex: Exception):ResponseEntity<ExceptionDetails>{
        val details = ExceptionDetails(
            title = "Erro Interno do Servidor",
            timestamp =  LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            exception = ex.javaClass.name,
            details = mutableMapOf("message" to (ex.message ?: "Erro desconhecido"))

        )
        return ResponseEntity(details, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleSeacherException(ex:BusinessException): ResponseEntity<ExceptionDetails>{
        val details = ExceptionDetails(
            title = "Erro na solicitação da busca",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.name,
            details = mutableMapOf("message" to  (ex.message ?: "Erro desconhecido"))
        )
        return ResponseEntity(details, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidJwtAuthenticationException::class)
    fun handleAuthJwtExcpetion(ex:InvalidJwtAuthenticationException): ResponseEntity<ExceptionDetails>{
        val details = ExceptionDetails(
            title = "Erro de autenticação",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            exception = ex.javaClass.name,
            details = mutableMapOf("message" to  (ex.message ?: "Erro desconhecido"))
        )
        return ResponseEntity(details, HttpStatus.FORBIDDEN)
    }
}