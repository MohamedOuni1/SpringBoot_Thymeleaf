package com.poly.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configuration de la page de connexion
        httpSecurity.formLogin(form -> form
                .permitAll()
                .defaultSuccessUrl("/defis/all", true) // Redirection après connexion réussie
        );

        // Gestion des erreurs d'accès
        httpSecurity.exceptionHandling(exception -> exception
                .accessDeniedPage("/erreur") // Page à afficher en cas d'accès refusé
        );

        // Autorisation des requêtes selon les rôles
        httpSecurity.authorizeHttpRequests(request -> request
                // Fonctions accessibles par les utilisateurs et les administrateurs
                .requestMatchers("/defis/all", "/defis/Ajouter_Defi", "/defis/save")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // Accessible à la fois pour les utilisateurs et les administrateurs

                // Fonctions supplémentaires accessibles uniquement par les administrateurs (ROLE_ADMIN)
                .requestMatchers("/defis/delete/**", "/defis/Update_Defi/**", "/defis/update"
                        ,"defis/details/**","/equipes/**","/resultats/**","/leaders/**","/themes/**")
                .hasAuthority("ROLE_ADMIN") // Accessible uniquement aux administrateurs

                .requestMatchers("/themes/save").hasAuthority("ROLE_ADMIN")  // Autoriser POST sur /themes/save uniquement pour ADMIN

                // Requêtes par défaut nécessitant une authentification
                .anyRequest().authenticated() // Authentification requise pour toutes les autres requêtes
        );

        // Déconnexion (optionnel)
        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/defis/all") // Redirection après la déconnexion
        );

        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                // Utilisateur avec le rôle USER
                User.withUsername("user")
                        .password(passwordEncoder().encode("1"))
                        .roles("USER") // Utilise le rôle 'USER'
                        .build(),

                // Administrateur avec le rôle ADMIN
                User.withUsername("admin")
                        .password(passwordEncoder().encode("1"))
                        .roles("ADMIN") // Utilise le rôle 'ADMIN'
                        .build()
        );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
