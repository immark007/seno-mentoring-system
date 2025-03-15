package mark.api.seno.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers(HttpMethod.POST, "users/students").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.POST, "users/teachers").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/users/promote").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/clients").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/users/createAdmin").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                })
                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
                .build();
    }

    //@Bean
    // PasswordEncoder passwordEncoder() {
     //   return new BCryptPasswordEncoder(10);
    //}

    //@Bean
    //public UserDetailsService userDetailsService(UserService userService) {
      //  return new CustomUserDetailsService(userService);
    //}

    // CONFIGURA O PREFIXO ROLE
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }


    //Configura no token jwt o prefixo scope
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthorityPrefix("ROLE_");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        RSAKey rsaKey = gerarChaveRSA();

        JWKSet jwkSet = new JWKSet();

        return new ImmutableJWKSet<>(jwkSet);
    }

    public RSAKey gerarChaveRSA() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey chavePublica = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateCrtKey chavePrivada = (RSAPrivateCrtKey) keyPair.getPrivate();


        return new RSAKey.Builder(chavePublica).privateKey(chavePrivada).keyID(UUID.randomUUID().toString()).build();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
}