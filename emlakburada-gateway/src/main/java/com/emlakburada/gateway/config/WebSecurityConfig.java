package com.emlakburada.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.emlakburada.gateway.filter.JwtFilter;

@Configuration
public class WebSecurityConfig{

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		//// @formatter:off
		http
		.httpBasic().disable()
		.formLogin().disable()
		.csrf().disable();
		
		return http.build();
		// @formatter:on
	}

	@Autowired
	private JwtFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
		//// @formatter:off
				.route("auth", 
						r -> r.path("/auth/**")
							  .uri("http://localhost:8082"))
				.route("emlakburada-user", 
						r -> r.method(HttpMethod.PUT)
						.and()
						.path("/users/**")
						.filters(f -> f.filter(filter))
						.uri("lb://emlakburada-user"))
				.route("emlakburada-advert",
						r -> r.path("/adverts/**")
						.and()
						.method(HttpMethod.PUT, HttpMethod.POST)
						.filters(f -> f.filter(filter))
						.uri("lb://emlakburada-advert"))
				.route("emlakburada-advert",
						r -> r.path("/adverts/active","/adverts/passive")
						.and()
						.method(HttpMethod.GET)
						.filters(f -> f.filter(filter))
						.uri("lb://emlakburada-advert"))
				.route("emlakburada-purchase",
						r -> r.path("/purchase/**")
						.and()
						.method(HttpMethod.POST)
						.filters(f -> f.filter(filter))
						.uri("lb://emlakburada-purchase"))
				.build();
		// @formatter:on
	}

}
