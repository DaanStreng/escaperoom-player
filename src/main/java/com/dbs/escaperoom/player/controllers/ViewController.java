package com.dbs.escaperoom.player.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@RequestMapping(value = "/**",produces = MediaType.ALL_VALUE)
@RestController
public class ViewController {
    @RequestMapping(method = RequestMethod.GET)
    public byte[] getIndex(HttpServletRequest request){
        try {
            ClassPathResource resource = new ClassPathResource("/static"+request.getServletPath());
            InputStream sr;
            if (resource.exists()&&resource.isReadable()&&request.getServletPath().length()>1) {
                sr = (resource.getInputStream());
            }
            else{
                resource = new ClassPathResource("/static"+request.getServletPath()+".html");
                if (resource.exists()&&resource.isReadable()&&request.getServletPath().length()>1) {
                    sr = (resource.getInputStream());
                }
                else
                    sr = (new ClassPathResource("/static/index.html").getInputStream());
            }

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int read;
            byte[] input = new byte[4096];
            while ( -1 != ( read = sr.read( input ) ) ) {
                buffer.write( input, 0, read );
            }
            input = buffer.toByteArray();
            return input;
        }
        catch(Exception e){}
        return new byte[0];
    }
}
