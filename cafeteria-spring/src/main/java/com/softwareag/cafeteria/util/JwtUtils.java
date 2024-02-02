//package com.softwareag.cafeteria.util;
//
//
//import com.softwareag.cafeteria.model.Employee;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    private static String secret= "secret_key";
//
//    public String generateJwt(Employee employee){
//
//        long milliTime =System.currentTimeMillis();
//        long expiryTime =milliTime+60*60*1000;
//        Date issuedAt=new Date(milliTime);
//        Date expiryAt=new Date(expiryTime);
//
//        Claims claims = Jwts.claims()
//                .setIssuer(employee.getId())
//                .setIssuedAt(issuedAt)
//                .setExpiration(expiryAt);
//        claims.put("employeeName",employee.getName());
//        claims.put("employeeId",employee.getId());
//        claims.put("employeeBalance",employee.getBalance());
//        claims.put("employeeName",employee.getName());
//        claims.put("employeeRole",employee.getRole());
//        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,secret)
//                .compact();
//    }
//
//    public Claims verify(String authorization) throws Exception{
//        try{
//            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
//            return claims;
//        }catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Access token not found");
//        }
//    }
//
//
//}
