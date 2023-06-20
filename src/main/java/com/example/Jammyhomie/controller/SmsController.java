package com.example.Jammyhomie.controller;
import com.example.Jammyhomie.model.SmsRequest;
import com.example.Jammyhomie.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SmsController {
    private final TwilioService twilioService;
    @Autowired
    public SmsController(TwilioService twilioService) {
        this.twilioService= twilioService;
    }
    @PostMapping("/send-sms")
    public String sendSms(@RequestBody SmsRequest smsRequest) {
        return twilioService.sendSms(smsRequest.getTo(), smsRequest.getBody());
    }

}



/*
package com.example.Bank.Managaement.System.controller;
        import com.example.Bank.Managaement.System.model.SmsRequest;
        import com.example.Bank.Managaement.System.service.TwilioService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;
@RestController
public class SmsController {
    private final TwilioService twilioService;

    @Autowired
    public SmsController(TwilioService twilioService) {
        this.twilioService= twilioService;
    }

    @PostMapping("/send-sms")
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        twilioService.sendSms(smsRequest.getTo(), smsRequest.getBody());
    }
}
*/