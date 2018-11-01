package springmvc.mvcinterceptor;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        // 新建一个我们自定义的媒体类型application/x-wisely
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        // 表明本HttpMessageConverter 只处理DemoObj 这个类。
        return DemoObj.class.isAssignableFrom(aClass);
    }
    /**
     * 重写readlntenal 方法，处理请求的数据。代码表明我们处理由“四”隔开的数据，
     * 并转成 DemoObj 的对象。
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] split = temp.split("-");
        return new DemoObj(new Long(split[0]), split[1]);
    }

    /**
     * 重写writeInternal ，处理如何输出数据到response。此例中，我们在原样输出前面加上"hello ："。
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello: " + obj.getId() + "-" + obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
