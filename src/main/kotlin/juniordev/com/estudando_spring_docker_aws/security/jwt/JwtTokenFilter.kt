package juniordev.com.estudando_spring_docker_aws.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import juniordev.com.estudando_spring_docker_aws.exception.InvalidJwtAuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.web.filter.GenericFilterBean

class JwtTokenFilter(
    private val tokenProvider: JwtTokenProvider,
    private val authenticationEntryPoint: AuthenticationEntryPoint
): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain) {
        try {
            val token = tokenProvider.resolveToken(request as HttpServletRequest)
            if (!token.isNullOrBlank() && tokenProvider.validateToken(token)) {
                val auth = tokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (ex: InvalidJwtAuthenticationException) {
            // Usa o entry point padrão do Spring Security
            authenticationEntryPoint.commence(
                request as HttpServletRequest,
                response as HttpServletResponse,
                ex
            )
            return
        }

        filterChain.doFilter(request, response)
    }
}
