package main.avatar;

import java.nio.file.Paths;
import java.util.stream.Stream;

import main.domain.User;
import main.service.avatars.AvatarGeneralException;
import main.service.avatars.AvatarStorageService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AvatarStorageService storageService;

    private final User testUser = new User((long)1,"test", "e.mail@mail.ru", "test", "sdfsd",0,0);


    @Test
    public void shouldListAllFiles() throws Exception {
        given(this.storageService.getAllAvatars())
                .willReturn(Stream.of(Paths.get("first.png"), Paths.get("second.png")));

        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("files",
                        Matchers.contains("http://localhost:8080/avatars/second.png",
                                "http://localhost:8080/avatars/second.png")));
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        final MockMultipartFile multipartFile = new MockMultipartFile("file", "test.png",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(fileUpload("/upload/avatar").file(multipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/upload/avatar"));

        then(this.storageService).should().saveAvatar(multipartFile,testUser);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should404WhenMissingFile() throws Exception {
        given(this.storageService.loadAvatarResource("test.png"))
                .willThrow(AvatarGeneralException.class);

        this.mvc.perform(get("/avatars/test.png")).andExpect(status().isNotFound());
    }

}
