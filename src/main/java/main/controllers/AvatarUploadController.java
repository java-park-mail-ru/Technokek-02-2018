package main.controllers;

import main.models.Message;
import main.service.avatars.AvatarControllerService;
import main.service.avatars.AvatarGeneralException;
import main.service.avatars.AvatarStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class AvatarUploadController {

    private final AvatarStorageService avatarStorageService;
    private final AvatarControllerService avatarControllerService;


    public AvatarUploadController(AvatarStorageService storageService, AvatarControllerService avatarControllerService) {
        this.avatarStorageService = storageService;
        this.avatarControllerService = avatarControllerService;
    }

    @GetMapping(value = "/avatars/{avatar:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String avatar) {
        return AvatarControllerService.dropAvatar(avatar, avatarStorageService);
    }

    @PostMapping("/upload/avatar/")
    public Message<String> handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
        return avatarControllerService.setAvatar(file, avatarStorageService, session);
    }

    @ExceptionHandler(AvatarGeneralException.class)
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }
}
