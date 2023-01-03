package com.oraclecompany.bbanggle.api.health.controller;

import com.oraclecompany.bbanggle.api.health.dto.HealthCheckResponseDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "01. 서버상태 체크", tags = "01. 서버상태 체크")
public class HealthCheckApiController {

	private final Environment environment;

	@GetMapping("/health")
	public ResponseEntity<HealthCheckResponseDto> healthCheck() {
		HealthCheckResponseDto healthCheckResponseDto = HealthCheckResponseDto.builder()
			.health("ok")
			.activeProfiles(Arrays.asList(environment.getActiveProfiles()))
			.build();

		return ResponseEntity.ok(healthCheckResponseDto);
	}
}