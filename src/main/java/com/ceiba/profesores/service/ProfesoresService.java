package com.ceiba.profesores.service;

import java.util.List;

import com.ceiba.profesores.model.Teacher;

public interface ProfesoresService {

	void saveTeacher(Teacher teacher);
	
    List<Teacher> findAllTeacher();
    
    void deleteTeacher(long id);
    
    void updateTeacher(Teacher teacher);
	
    Teacher findTeacherById(long id);
    
    Teacher findByName(String name);
}
