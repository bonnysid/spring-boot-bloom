package com.bonnysid.bloom.services;

import com.bonnysid.bloom.bucket.BucketName;
import com.bonnysid.bloom.model.view.Me;
import com.bonnysid.bloom.model.view.UserView;
import com.bonnysid.bloom.model.view.UserListView;
import com.bonnysid.bloom.security.AuthInfo;
import com.bonnysid.bloom.storage.FileStore;
import com.bonnysid.bloom.model.*;
import com.bonnysid.bloom.respos.UserRepository;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileStore fileStore;
    private final SubscribesService subscribesService;
    private final AuthInfo authInfo;

    @Autowired
    public UserService(UserRepository userRepository, FileStore fileStore, SubscribesService subscribesService, AuthInfo authInfo) {
        this.userRepository = userRepository;
        this.fileStore = fileStore;
        this.subscribesService = subscribesService;
        this.authInfo = authInfo;
    }

    public List<UserListView> getUsers() {
        String username = authInfo.getAuthUsername();
        List<Long> followList = subscribesService.getAllSubscribes();

        return userRepository.findAll().stream()
                .filter(user -> !user.getEmail().equals(username))
                .map(user -> new UserListView(user, followList.contains(user.getId())))
                .collect(Collectors.toList());
    }

    public UserView getUser(Long id) {
        return new UserView(getUserOrElseThrow(id), subscribesService.checkSubscribe(id));
    }

    public UserView getUser(String usernameOfFollowed) {
        User user = userRepository.getUserByUsername(usernameOfFollowed).orElseThrow(() -> new IllegalStateException("User with username " + usernameOfFollowed + " doesn't exists!"));
        return new UserView(user, subscribesService.checkSubscribe(user.getId()));
    }

//    public void postUser(User user) {
//        Optional<User> userByEmail = userRepository.getUserByEmail(user.getEmail());
//        Optional<User> userByUsername = userRepository.getUserByUsername(user.getUsername());
//        if (userByEmail.isPresent()) throw new IllegalStateException("Email is taken");
//        if (userByUsername.isPresent()) throw new IllegalStateException("Username is taken");
//        user.setRole(new HashSet<Role>(new Role[]{new Role(Roles.USER)}));
//        user.setStatus(Status.ACTIVE);
//        userRepository.save(user);
//    }

    public void deleteUser(long id) {
        boolean userExists = userRepository.existsById(id);
        if (userExists) userRepository.deleteById(id);
        else throw new IllegalStateException("User with id " + id + " doesn't exists!");
    }

    @Transactional
    public void updateUser(long id, String name, String email) {
        User user = getUserOrElseThrow(id);

        if (name != null && !Objects.equals(user.getUsername(), name)) {
            if (userRepository.getUserByUsername(name).isPresent())
                throw new IllegalStateException("Username is taken");
            user.setUsername(name);
        }

        if (email != null && !Objects.equals(user.getEmail(), email)) {
            if (userRepository.getUserByEmail(email).isPresent()) throw new IllegalStateException("Email is taken");
            user.setEmail(email);
        }
    }

    @Transactional
    public void updateUser(long id, User u) {
        updateUser(id, u.getUsername(), u.getEmail());
    }

    public byte[] getPhoto(long id) {
        User u = getUserOrElseThrow(id);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getName(), u.getId());
        return u.getPhoto().map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public User getUserOrElseThrow(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " doesn't exists!"));
    }

    @Transactional
    public void putPhoto(long id, MultipartFile image) {
        if (image.isEmpty()) throw new IllegalStateException("Cannot upload empty image [" + image.getSize() + "]");
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(image.getContentType())) {
            throw new IllegalStateException("Cannot upload file with this type [" + image.getContentType() + "], allows only jpeg, png, gif.!");
        }

        User u = getUserOrElseThrow(id);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());
        metadata.put("Content-Length", String.valueOf(image.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getName(), u.getId());
        String filename = String.format("%s-%s", image.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), image.getInputStream());
            u.setPhoto(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Me getMe() {
        String username = authInfo.getAuthUsername();
        if (username == null) return new Me(null, null, null);
        return new Me(authInfo.getAuthId(), username, authInfo.getAuthUsername());
    }

    public List<UserListView> getFriends() {
        return getUsers().stream()
                .filter(UserListView::isFollowed)
                .collect(Collectors.toList());
    }
}
