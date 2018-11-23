package com.ceiba.profesores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ceiba.profesores.model.Teacher;
import com.ceiba.profesores.service.ProfesoresService;

@Controller
@RequestMapping("/v1")
public class MainController {

	@Autowired
	ProfesoresService profesoresService;
	
	@RequestMapping(value="/obtenerTodos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Teacher>> getTeachers() {
		return new ResponseEntity<List<Teacher>>(profesoresService.findAllTeacher(), HttpStatus.OK);
	}
}
