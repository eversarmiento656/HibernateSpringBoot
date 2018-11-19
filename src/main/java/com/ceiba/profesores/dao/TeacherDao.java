package com.ceiba.profesores.dao;

import java.util.List;

import com.ceiba.profesores.model.Teacher;

public interface TeacherDao {

	void saveTeacher(Teacher teacher);
	
    List<Teacher> findAllTeacher();
    
    void deleteTeacher(long id);
    
    void updateTeacher(Teacher teacher);
	
    Teacher findTeacherById(long id);
    
    Teacher findByName(String name);
}
