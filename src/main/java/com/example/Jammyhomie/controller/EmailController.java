package com.example.Jammyhomie.controller;

import com.example.Jammyhomie.service.JammyhomieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
   // @Autowired
    //JammyhomieService jammyhomieService;
   /* @PostMapping("/invite/{recipienteMail}/{hostMail}")
    public ResponseEntity<String> sendReferralCode(@PathVariable String recipienteMail, @PathVariable String hostMail) {
        // Validate the email address and perform any necessary checks

        // Call the email service to send the invitation email
        jammyhomieService.sendReferralCode(recipienteMail,hostMail);
        return ResponseEntity.ok("Invitation email sent successfully");
    }
*/
}
