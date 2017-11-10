package com.dbs.escaperoom.player.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;

import javax.persistence.*;

@Entity
public class Player {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(128)")
    private String uuid;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    public Player(){};

    public boolean passwordMatches(String password){
        return encoder.matches(password,this.password);
    }

    public void setPassword(String password){
        this.password = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }
}
