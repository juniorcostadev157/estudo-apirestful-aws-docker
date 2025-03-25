package juniordev.com.estudando_spring_docker_aws.config


import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenApi():OpenAPI{
        return OpenAPI()
            .info(
                Info().title("RESTful Api with Kotlin and Spring")
                    .version("v1")
                    .description("api do junior")
                    .termsOfService("https://politicaprividadeseventecnico.vercel.app/")
                    .license(
                        License().name("Apache 2.0").url("https://politicaprividadeseventecnico.vercel.app/")
                    )

            )
    }
}