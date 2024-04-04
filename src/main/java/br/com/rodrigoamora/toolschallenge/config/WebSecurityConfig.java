package br.com.rodrigoamora.toolschallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true
)
public class WebSecurityConfig {

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())

        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(req -> {
        	req.requestMatchers(HttpMethod.GET, "/redoc").permitAll();
        	req.requestMatchers("/pagamento/**").permitAll();
            req.anyRequest().authenticated();
        });
        
        return http.build();
    }
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/ignore2", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v2/api-docs/**", "/h2-console/**");
	}
	
}
