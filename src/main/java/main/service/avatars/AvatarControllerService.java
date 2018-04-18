package main.service.avatars;

import main.dao.UserDao;
import main.domain.User;
import main.models.Message;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Service
public class AvatarControllerService {


    private UserDao userDao;

    public AvatarControllerService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static ResponseEntity<Resource> getAvatar(String avatar, AvatarStorageService avatarStorageService) {
        final Resource file = avatarStorageService.loadAvatarResource(avatar);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""  + file.getFilename() + '"').body(file);
    }

    public Message<String> setAvatar(MultipartFile file, AvatarStorageService avatarStorageService, HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        if (id == null) {
            return new Message<String>(false, "NOT_LOGINED");
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            return new Message<String>(false, "INVALID_SESSION_ID");
        }
        avatarStorageService.saveAvatar(file, curUser);
        return new Message<String>(true, "AVATAR_SUCCESSFULLY_UPLOADED");
    }
}
