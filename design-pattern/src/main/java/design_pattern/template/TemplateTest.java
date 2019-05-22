package design_pattern.template;

import design_pattern.template.father.GetTimeTemplate;
import design_pattern.template.son.FileCopyDemo;
import design_pattern.template.son.ForDemo;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class TemplateTest {

    public static void main(String[] args) {
        GetTimeTemplate getTimeTemplate = new FileCopyDemo();
        System.out.println("耗时"+getTimeTemplate.getTime());


        GetTimeTemplate getTimeTemplate2 = new ForDemo();
        System.out.println("耗时"+getTimeTemplate2.getTime());
    }
}
