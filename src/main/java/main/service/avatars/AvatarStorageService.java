package main.service.avatars;

import main.domain.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface AvatarStorageService {

    void init();

    void saveAvatar(MultipartFile file, User curUser);

    Path getPath(String filename);

    Resource loadAvatarResource(String filename);

}