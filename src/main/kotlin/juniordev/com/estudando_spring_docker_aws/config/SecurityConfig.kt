package juniordev.com.estudando_spring_docker_aws.config

import juniordev.com.estudando_spring_docker_aws.exception.InvalidJwtAuthenticationException
import juniordev.com.estudando_spring_docker_aws.security.CustomAuthenticationEntryPoint
import juniordev.com.estudando_spring_docker_aws.security.jwt.JwtTokenFilter
import juniordev.com.estudando_spring_docker_aws.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig{
    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var authenticationEntryPoint: CustomAuthenticationEntryPoint

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("https://meusite.com")  // Ajuste conforme necessário
        corsConfig.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        corsConfig.allowedHeaders = listOf("*")
        source.registerCorsConfiguration("/**", corsConfig)
        return source
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val customFilter = JwtTokenFilter(tokenProvider, authenticationEntryPoint)

        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { cors -> cors.configurationSource(corsConfigurationSource()) }
            .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { ex ->
                ex.authenticationEntryPoint(authenticationEntryPoint)
            }
            .authorizeHttpRequests {
                // Permitir o login e refresh sem autenticação
                it.requestMatchers(
                    "/auth/signin",
                    "/auth/refresh/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**"
                ).permitAll()

                // 🔥 Ajustar os endpoints que precisam de autenticação
                it.requestMatchers("/ordemservico/**", "/pessoas/**").authenticated()

                // 🔥 Ajustar as permissões por role
                it.requestMatchers("/users").hasRole("ADMIN")

                // 🔥 Bloquear qualquer outra requisição que não foi definida acima
                it.anyRequest().authenticated()
            }
            .build()
    }

}