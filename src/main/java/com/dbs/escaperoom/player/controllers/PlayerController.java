package com.dbs.escaperoom.player.controllers;

import com.dbs.escaperoom.player.models.Player;
import com.dbs.escaperoom.player.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(method = RequestMethod.POST,path="/register")
    public Player registerPlayer(@RequestParam String username, @RequestParam String password){
        Player check = playerRepository.getPlayerByUsername(username);
        if(check!=null){
            throw new DuplicateKeyException("Username already taken");
        }
        Player player = new Player();
        player.setPassword(password);
        player.setUsername(username);
        playerRepository.save(player);
        return player;
    }

    @RequestMapping(method = RequestMethod.POST,path="/login")
    public Player login(@RequestParam String username, @RequestParam String password){
        Player check = playerRepository.getPlayerByUsername(username);
        if(check==null){
            throw new NotFoundException("Username not found");
        }
        if (check.passwordMatches(password))
            return check;
        throw new NotFoundException("Password does not match");
    }

    @RequestMapping(method = RequestMethod.POST,path="/getinfo/{uuid}")
    public Player getPlayer(@PathVariable String uuid){
        Player check = playerRepository.getPlayer(uuid);
        if(check==null){
            throw new NotFoundException("Player not found");
        }
        return check;
    }

}
