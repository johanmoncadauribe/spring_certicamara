package com.example.certicamara.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.certicamara.Utils;
import com.example.certicamara.Dto.GenderDTO;
import com.example.certicamara.Dto.ShoutDTO;
import com.example.certicamara.Dto.ShoutResponseDTO;
import com.example.certicamara.Model.modelGender;
import com.example.certicamara.Model.modelShoutcould;
import com.example.certicamara.Repository.RepoGenderize;
import com.example.certicamara.Repository.RepoShoutcould;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping(path="shouldCould")
public class ControllerShoulcould {


	@Autowired
	private RepoShoutcould reposhout;
	
			@PostMapping(path="/shoud")
			public @ResponseBody Map<String, Object> shoudcoult (@RequestBody ShoutDTO shou) {
				if(shou==null||shou.getEntrada()==null||shou.getEntrada().isBlank()) {
					return Utils.respuesta(false, "Datos ingresados son incorrectos",null);
				}
			try {
					modelShoutcould mod=new modelShoutcould();
					reposhout.save(mod);
			}catch(Exception e) {
				  return Utils.respuesta(false, "Error desconocido", null);
			}
				StringBuilder response = new StringBuilder();
				try {
					URL url = new URL ("http://API.SHOUTCLOUD.IO/V1/SHOUT");
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("Content-Type", "application/json");
					con.setRequestProperty("Accept", "application/json");
					con.setDoOutput(true);
					con.setDoInput(true);
					String jsonInputString = "{\"INPUT\": \""+shou.getEntrada()+"\"}";
					try(OutputStream os = con.getOutputStream()) {
					    byte[] input = jsonInputString.getBytes("utf-8");
					    os.write(input, 0, input.length);
					}
					try(BufferedReader br = new BufferedReader(
							  new InputStreamReader(con.getInputStream(), "utf-8"))) {						
							    String responseLine = null;
							    while ((responseLine = br.readLine()) != null) {
							       response.append(responseLine);  
							    }
					}
					
					JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
					ShoutResponseDTO respon=new ShoutResponseDTO();
					respon.setINPUT(jsonObject.get("INPUT").getAsString());
					respon.setOUTPUT(jsonObject.get("OUTPUT").getAsString());
					return Utils.respuesta(true, "succes", respon);
				}catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cause description here");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cause description here");
				}
			}
	
}
