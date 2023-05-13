package com.practice.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MyController {
	
	@Autowired
	WebClient webClient;
	
	@GetMapping("/emp")
	public Flux<Employee> getEmployees(){
		return webClient.get().uri("employees").retrieve().bodyToFlux(Employee.class);
	}

	@GetMapping("/emp/{name}")
	public Employee getEmployee(@PathVariable("name") String firstName){
		return webClient.get().uri("/employee/"+ firstName).retrieve().bodyToMono(Employee.class).block();
	}

	@PostMapping("/save")
	public Mono<String> saveEmployee(@RequestBody Employee emp){
		return webClient.post().uri("/createEmp").syncBody(emp).retrieve().bodyToMono(String.class);
	}
	@DeleteMapping("/delete/{name}")
	public Mono<String> deleteRecord(@PathVariable("name") String firstName){
		return webClient.delete().uri("/employee/" + firstName).retrieve().bodyToMono(String.class);
	}
}
