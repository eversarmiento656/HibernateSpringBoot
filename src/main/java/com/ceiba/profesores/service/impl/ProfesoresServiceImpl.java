package com.ceiba.profesores.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.profesores.dao.TeacherDao;
import com.ceiba.profesores.model.Teacher;
import com.ceiba.profesores.service.ProfesoresService;

@Service("ProfesoresService")
@Transactional
public class ProfesoresServiceImpl implements ProfesoresService {

	@Autowired
	TeacherDao teacherDao;
	
	@Override
	public void saveTeacher(Teacher teacher) {
		teacherDao.saveTeacher(teacher);
	}

	@Override
	public List<Teacher> findAllTeacher() {

		return teacherDao.findAllTeacher();
	}

	@Override
	public void deleteTeacher(long id) {

		teacherDao.deleteTeacher(id);
	}

	@Override
	public void updateTeacher(Teacher teacher) {

		teacherDao.updateTeacher(teacher);
	}

	@Override
	public Teacher findTeacherById(long id) {

		return teacherDao.findTeacherById(id);
	}

	@Override
	public Teacher findByName(String name) {

		return teacherDao.findByName(name);
	}

}
