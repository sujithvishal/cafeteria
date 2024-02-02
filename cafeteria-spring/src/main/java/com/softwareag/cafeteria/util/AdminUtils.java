package com.softwareag.cafeteria.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AdminUtils {

    public void checkAdmin(String role){
//        if(!role.contains("ADMIN"))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"This Route is Protected");

    }
}
