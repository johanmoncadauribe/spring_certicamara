package com.example.certicamara.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.certicamara.Utils;
import com.example.certicamara.Dto.GenderDTO;
import com.example.certicamara.Model.modelGender;
import com.example.certicamara.Repository.RepoGenderize;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping(path="gender")
public class ControllerGender {
	
	@Autowired
	private RepoGenderize repogenderize;

	
	//prueba
		@GetMapping(path="/apiGender/{name}")
		public @ResponseBody Map<String, Object> getGender (@PathVariable String name){
			if(name.isBlank()||name.isEmpty()||name==null||name.equalsIgnoreCase("null")) {
				return Utils.respuesta(false, "Nombre incorrecto", null);
			}
			StringBuilder response = new StringBuilder();
			try {
					modelGender mod=new modelGender();
					repogenderize.save(mod);
			}catch(Exception e) {
				  return Utils.respuesta(false, "Error desconocido", null);
			}
			try {
				URL url = new URL ("https://api.genderize.io?name="+name);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.getRequestMethod();
				con.setRequestProperty("Accept", "application/json");
				try(BufferedReader br = new BufferedReader(
						  new InputStreamReader(con.getInputStream(), "utf-8"))) {
						    String responseLine = null;
						    while ((responseLine = br.readLine()) != null) {
						       response.append(responseLine);
						    }
				}
				JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
				GenderDTO gen=new GenderDTO();
				gen.setName(jsonObject.get("name").getAsString());
				gen.setGender(jsonObject.get("gender").getAsString());
				gen.setProbability(jsonObject.get("probability").getAsString());
				gen.setCount(jsonObject.get("count").getAsString());
				return Utils.respuesta(true, "succes", gen);
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cause description here");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cause description here");
			}
		}
}
