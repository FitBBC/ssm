package design_pattern.template.son;

import design_pattern.template.father.GetTimeTemplate;

import java.io.*;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class FileCopyDemo extends GetTimeTemplate {
    protected void code() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/fitbbc/Downloads/spring-ioc源码流程");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            FileOutputStream fileOutputStream = new FileOutputStream("/Users/fitbbc/Downloads/spring-ioc源码流程2");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);


            byte[] bytes = new byte[256];
            int len = 0;

            while ((len = bufferedInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes, 0, len);
                bufferedOutputStream.flush();
            }


            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
