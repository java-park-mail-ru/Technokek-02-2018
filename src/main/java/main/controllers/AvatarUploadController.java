package main.controllers;

import main.models.Message;
import main.service.avatars.AvatarNotFoundException;
import main.service.avatars.AvatarStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AvatarUploadController {

    private final AvatarStorageService avatarStorageService;

    @Autowired
    public AvatarUploadController(AvatarStorageService storageService) {
        this.avatarStorageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        final Resource file = avatarStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + '"').body(file);
    }

    @PostMapping("/upload")
    public Message<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        avatarStorageService.store(file);
        return new Message<String>(true, "AVATAR_SUCCESSFULLY_UPLOADED");
    }

    @ExceptionHandler(AvatarNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }
}
