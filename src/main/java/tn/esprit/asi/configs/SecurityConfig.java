package tn.esprit.asi.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;
    
    // Or, if you want to match multiple paths, use the AntPathRequestMatcher array
    RequestMatcher[] requestMatchers = {
        new AntPathRequestMatcher("/login", "POST"),
        new AntPathRequestMatcher("/register", "POST"),
        // Add more path and method combinations as needed
    };

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }

}
