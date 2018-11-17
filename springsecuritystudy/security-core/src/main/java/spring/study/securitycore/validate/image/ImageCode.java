package spring.study.securitycore.validate.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import spring.study.securitycore.validate.sms.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ImageCode extends ValidateCode {
    private BufferedImage imge;

    public ImageCode(BufferedImage imge, String code, int expireIn) {
        super(code,expireIn);
        this.imge = imge;

    }
    public ImageCode(BufferedImage imge, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.imge = imge;

    }

}
