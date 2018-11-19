package com.ceiba.profesores.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
	
	private static final String RUTA_MODEL = "com.ceiba.profesores.model";
	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static final String RUTA_BASEDATOS = "jdbc:mysql://localhost:3306/profesores";
	private static final String USERNAME = "profesores";
	private static final String CONTRASENA = "Ev3r1221";
	private static final String DIALECT_CLAVE = "hibernate.dialect";
	private static final String DIALECT_VALOR = "org.hibernate.dialect.MySQLDialect";
	private static final String SHOW_CLAVE = "show_sql";
	private static final String SHOW_VALOR = "true";
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(RUTA_MODEL);
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(DRIVER_MYSQL);
		data.setUrl(RUTA_BASEDATOS);
		data.setUsername(USERNAME);
		data.setPassword(CONTRASENA);
		return data;
	}
	
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(DIALECT_CLAVE, DIALECT_VALOR);
		properties.put(SHOW_CLAVE, SHOW_VALOR);
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
}
