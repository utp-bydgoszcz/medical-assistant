package pl.edu.utp.medicalassistant.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.service.AndroidPushNotificationService;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/android/notification")
public class AndroidNotificationController {

    private final String TOPIC = "test";

    private final AndroidPushNotificationService androidPushNotificationService;

    @Autowired
    public AndroidNotificationController(AndroidPushNotificationService androidPushNotificationService) {
        this.androidPushNotificationService = androidPushNotificationService;
    }

    @GetMapping(value = "/send",produces = "application/json")
    public ResponseEntity<String> send(@RequestBody HashMap<String,Object> sendData,
                                       @RequestParam String notificationTitle,@RequestParam String notificationData) throws JSONException {

        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + TOPIC);
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", notificationTitle);
        notification.put("body", notificationData);

        JSONObject data = new JSONObject();
        data.put("data", sendData);


        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = androidPushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();

            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
