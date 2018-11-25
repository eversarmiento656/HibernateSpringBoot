package com.ceiba.profesores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ceiba.profesores.model.Teacher;
import com.ceiba.profesores.service.ProfesoresService;

@Controller
@RequestMapping("/v1")
public class MainController {

	@Autowired
	ProfesoresService profesoresService;
	private static final String ERROR_DATOS = "Error al ingresar los datos";
	private static final String CORRECTO_CREAR = "Se creó correctamente";
	private static final String CORRECTO_ELIMINAR = "Se eliminó correctamente";
	private static final String CORRECTO_ACTUALIZAR = "Se actualizó correctamente";
	private static final String TEACHER_NO_ESTA = "El teacher no está en la base de datos";
	private static final String TEACHER_ESTA = "Ya se encuentra en la base de datos";

	@GetMapping(value = "/profesores", headers = "Accept=application/json")
	public ResponseEntity<List<Teacher>> getTeachers() {
		return new ResponseEntity<>(profesoresService.findAllTeacher(), HttpStatus.OK);
	}

	@GetMapping(value = "/profesores/{id}", headers = "Accept=application/json")
	public ResponseEntity<Teacher> getTeacher(@PathVariable("id") long id) {

		if (id == 0) {

			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		if (profesoresService.findTeacherById(id) != null) {

			return new ResponseEntity<>(profesoresService.findTeacherById(id), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/profesores", headers = "Accept=application/json")
	public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {

		if (teacher == null) {
			return new ResponseEntity<>(ERROR_DATOS, HttpStatus.NO_CONTENT);
		}

		if (profesoresService.findByName(teacher.getName()) != null) {
			return new ResponseEntity<>(TEACHER_ESTA, HttpStatus.NO_CONTENT);
		}

		profesoresService.saveTeacher(teacher);
		return new ResponseEntity<>(CORRECTO_CREAR, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<String> updateTeacher(@PathVariable("id") long id, @RequestBody Teacher teacher) {

		if (teacher == null || id == 0) {
			return new ResponseEntity<>(ERROR_DATOS, HttpStatus.NO_CONTENT);
		}

		if (profesoresService.findTeacherById(id) == null) {
			return new ResponseEntity<>(TEACHER_NO_ESTA, HttpStatus.NO_CONTENT);
		}
        
		Teacher t = profesoresService.findTeacherById(id);
		t.setAvatar(teacher.getAvatar());
		t.setName(teacher.getName());

		profesoresService.updateTeacher(t);

		return new ResponseEntity<>(CORRECTO_ACTUALIZAR, HttpStatus.OK);

	}

	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteTeacher(@PathVariable("id") long id) {

		if (id == 0) {
			return new ResponseEntity<>(ERROR_DATOS, HttpStatus.NO_CONTENT);
		}

		if (profesoresService.findTeacherById(id) == null) {
			return new ResponseEntity<>(TEACHER_NO_ESTA, HttpStatus.NO_CONTENT);
		}

		profesoresService.deleteTeacher(id);

		return new ResponseEntity<>(CORRECTO_ELIMINAR, HttpStatus.OK);

	}
}
