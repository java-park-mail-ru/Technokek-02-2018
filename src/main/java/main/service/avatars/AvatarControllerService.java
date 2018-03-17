package main.service.avatars;

import main.data.UserList;
import main.models.Message;
import main.models.User;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public class AvatarControllerService {

    public static ResponseEntity<Resource> dropAvatar(String avatar, AvatarStorageService avatarStorageService) {
        final Resource file = avatarStorageService.loadAvatarResource(avatar);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + '"').body(file);
    }

    public static Message<String> setAvatar(MultipartFile file, AvatarStorageService avatarStorageService, HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        if (id == null) {
            return new Message<String>(false, "NOT_LOGINED");
        }
        final User curUser = UserList.getById(id);
        if (curUser == null) {
            return new Message<String>(false, "INVALID_SESSION_ID");
        }
        avatarStorageService.saveAvatar(file, curUser);
        return new Message<String>(true, "AVATAR_SUCCESSFULLY_UPLOADED");
    }
}
