package com.users.users.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.users.users.models.userModel;
import com.users.users.services.userService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class userController {
    
    @Autowired
    @Qualifier("userService")
    userService _userService;

    //mostrar solo un usuario ()
    @GetMapping(path = "/{id}")
    public Optional <userModel> getUser(@PathVariable("id") Long userId) {
        Optional <userModel> opUserModel = null;
        if(userId != null){
            opUserModel = _userService.getUser(userId);
            if(opUserModel.isPresent()) return opUserModel;
        }
        return opUserModel;
    }

    //guardado de usuarios
    //se pretende guardar mas de 1 email ceparandolos por comas
    @PostMapping()
    public userModel saveUser (@ModelAttribute @Valid userModel user, @RequestParam("file") MultipartFile image) {
        if(!image.isEmpty()){

            Path imagePath =  Paths.get("src//main//resources//static/images");
            String absoluteRoute = imagePath.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = image.getBytes();
                Path allRoute = Paths.get(absoluteRoute + "//" + image.getOriginalFilename());
                Files.write(allRoute, bytesImg);
                user.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            user.setImage("-1");
        }
        return _userService.saveUser(user);
    }

    //editado de usuarios
    @PutMapping()
    public userModel updateUser (@ModelAttribute @Valid userModel user, @RequestParam("file") MultipartFile image) {
        if(!image.isEmpty()){

            Path imagePath =  Paths.get("src//main//resources//static/images");
            String absoluteRoute = imagePath.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = image.getBytes();
                Path allRoute = Paths.get(absoluteRoute + "//" + image.getOriginalFilename());
                Files.write(allRoute, bytesImg);
                user.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            user.setImage("-1");
        }
        return _userService.updateUser(user);
    }

    //eliminacion fisica

    @DeleteMapping( path = "/{id}")
    public String deleteUser (@PathVariable("id") Long id) {
        Boolean isDeleted = _userService.deleteUser(id);
        if(isDeleted) return "Se elimino correctamente";
        else return "Hubo un error :(";
    }

    //eliminacion lógica

    @DeleteMapping( path = "/logic/{id}")
    public userModel deleteUserLogic (@PathVariable("id") Long id) {
        userModel um = null;
        Optional <userModel> opUserModel = null;
        if(id != null){
            opUserModel = _userService.getUser(id);
            if(opUserModel.isPresent()) {
                um = opUserModel.get();
                um.setStatus("200");
                return _userService.saveUser(um);
            }
        }
        return um;
    }
}
