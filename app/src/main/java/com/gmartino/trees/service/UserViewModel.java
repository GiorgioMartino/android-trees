package com.gmartino.trees.service;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gmartino.trees.entity.User;
import com.gmartino.trees.repository.UserRepository;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class UserViewModel extends AndroidViewModel {

    private static final String LOG_TAG = UserViewModel.class.getSimpleName();

    private final UserRepository userRepository;

    private User userSavedCredentials;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public User saveUser(String username, String email, String password) {
        Log.d(LOG_TAG, String.format("Saving User %s", email));

        User user = new User(username, email, password);
        encryptPassword(user);
        userRepository.insert(user);
        return user;
    }

    public User findUser(String email, String password) {
        Log.d(LOG_TAG, String.format("Searching User %s", email));
        User user = userRepository.findByMail(email);

        if (isUserPasswordValid(user, password))
            return user;
        else
            return null;
    }

    private boolean isUserPasswordValid(User user, String password) {
        Log.d(LOG_TAG, "Checking User password");
        try {
            byte[] decodedKey = Base64.getDecoder().decode(user.getKey());
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            byte[] decodedIv = Base64.getDecoder().decode(user.getIv());
            IvParameterSpec iv = new IvParameterSpec(decodedIv);

            String decrypted =
                    EncryptionService.decrypt(user.getPassword(), secretKey, iv);

            return decrypted.equals(password);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error decrypting password");
            return false;
        }
    }

    public boolean checkEmailExist(String mail) {
        return userRepository.findByMail(mail) != null;
    }

    public void encryptPassword(User user) {
        Log.d(LOG_TAG, "Encrypting User password");
        try {
            SecretKey secretKey = EncryptionService.generateKey();
            IvParameterSpec iv = EncryptionService.generateIv();
            String password = EncryptionService.encrypt(user.getPassword(), secretKey, iv);

            user.setIv(Base64.getEncoder().encodeToString(iv.getIV()));
            user.setKey(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
            user.setPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserSavedCredentials() {
        return userSavedCredentials;
    }

    public void setUserSavedCredentials(User userSavedCredentials) {
        this.userSavedCredentials = userSavedCredentials;
    }
}
