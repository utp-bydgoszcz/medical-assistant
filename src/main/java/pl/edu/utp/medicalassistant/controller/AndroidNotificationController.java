package pl.edu.utp.medicalassistant.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.service.impl.AndroidPushNotificationService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/android/notification")
public class AndroidNotificationController {

    private final String TOPIC = "test";

    private final AndroidPushNotificationService androidPushNotificationService;

    @Autowired
    public AndroidNotificationController(AndroidPushNotificationService androidPushNotificationService) {
        this.androidPushNotificationService = androidPushNotificationService;
    }

    @PostMapping(value = "/send",produces = "application/json")
    public ResponseEntity<String> send(@RequestBody HashMap<String,Object> sendData,
                                       @RequestParam String notificationTitle,@RequestParam String notificationData) throws JSONException {

        return new ResponseEntity<>(androidPushNotificationService.sendPushNotification(notificationTitle,notificationData,sendData),HttpStatus.OK);
    }
}
