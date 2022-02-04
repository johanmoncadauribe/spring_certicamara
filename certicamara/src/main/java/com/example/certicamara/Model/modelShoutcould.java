package com.example.certicamara.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class modelShoutcould {

	@Id
	@Column(name = "count")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Count;
	
	@Column(name = "fecha_consulta", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Timestamp fechaConsulta;

	public modelShoutcould() {
		super();
	}

	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public Timestamp getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Timestamp fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
}
