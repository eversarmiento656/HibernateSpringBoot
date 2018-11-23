package com.ceiba.profesores.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.profesores.dao.AbstractSession;
import com.ceiba.profesores.dao.TeacherDao;
import com.ceiba.profesores.model.Teacher;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {

	public void saveTeacher(Teacher teacher) {
		getSession().persist(teacher);
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeacher() {
		return getSession().createQuery("from Teacher").list();
	}

	public void deleteTeacher(long id) {
		if (findTeacherById(id) != null) {
			getSession().delete(findTeacherById(id));
		}
	}

	public void updateTeacher(Teacher teacher) {
		getSession().update(teacher);
	}

	public Teacher findTeacherById(long id) {
		return getSession().get(Teacher.class, id);
	}

	public Teacher findByName(String name) {
		return (Teacher) getSession().createQuery("from Teacher where name = :name").setParameter("name", name)
				.uniqueResult();
	}

}
