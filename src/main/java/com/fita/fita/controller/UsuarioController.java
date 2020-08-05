package com.fita.fita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fita.fita.model.UsuarioModel;
import com.fita.fita.repository.UsuarioRepository;

@RequestMapping("/usuario")
@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	public UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAllUsuario (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findByIDUsuario (@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModel>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<UsuarioModel> postUsuario (@RequestBody UsuarioModel usuario){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	@PutMapping
	public ResponseEntity<UsuarioModel> putUsuario  (@RequestBody UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	@DeleteMapping("/{id}")
	public void deleteUsuario (@PathVariable long id) {
		repository.deleteById(id);
	}
}
