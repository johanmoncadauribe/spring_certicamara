package com.example.certicamara.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.certicamara.Repository.RepoGenderize;

@RestController
@RequestMapping(path="gender")
public class ControllerGender {
	
	@Autowired
	private RepoGenderize repogenderize;

	
	//prueba
		@GetMapping(path="/apiGender/{name}")
		public @ResponseBody Map<String, Object> getGender (@PathVariable String name){
			
			return null;
		}
}
