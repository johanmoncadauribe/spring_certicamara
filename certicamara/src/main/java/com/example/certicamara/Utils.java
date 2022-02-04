package com.example.certicamara;

import java.util.HashMap;
import java.util.Map;

public class Utils {
   
    public static Map<String, Object> respuesta(boolean estado, String mensaje, Object info) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", mensaje);
        respuesta.put("info", info);
        respuesta.put("status", estado);
        return respuesta;
    }

  }
