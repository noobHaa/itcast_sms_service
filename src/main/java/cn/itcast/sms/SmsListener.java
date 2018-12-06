package cn.itcast.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author ll
 * @Date 2018/12/3 10:17
 */
@Component
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    @JmsListener(destination = "sms")
    public void sendSms(Map<String, String> map) {

        try {
            SendSmsResponse sendSmsResponse = smsUtil.sendSms(map.get("mobile"),
                    map.get("template_code"),
                    map.get("sign_name"),
                    map.get("param"));

            System.out.println("Code=" + sendSmsResponse.getCode());
            System.out.println("Message=" + sendSmsResponse.getMessage());
            System.out.println("RequestId=" + sendSmsResponse.getRequestId());
            System.out.println("BizId=" + sendSmsResponse.getBizId());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
