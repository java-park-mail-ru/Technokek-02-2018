package main.service.avatars;

import main.domain.User;
import main.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class AvatarStorageSystem implements AvatarStorageService {

    private final Path rootLocation;

    @Autowired
    public AvatarStorageSystem(FileStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation() + "user_api_files/avatars/");
    }

    @Override
    public void saveAvatar(MultipartFile file, User curUser) {
        final String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new AvatarGeneralException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new AvatarGeneralException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            curUser.setAvatar(filename);
        } catch (IOException e) {
            throw new AvatarGeneralException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> getAllAvatars() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new AvatarGeneralException("Failed to read stored files", e);
        }

    }

    @Override
    public Path getPath(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAvatarResource(String filename) {
        try {
            final Path file = getPath(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new AvatarGeneralException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new AvatarGeneralException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new AvatarGeneralException("Could not initialize storage", e);
        }
    }

}
