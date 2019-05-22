package design_pattern.template.son;

import design_pattern.template.father.GetTimeTemplate;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class ForDemo extends GetTimeTemplate {
    protected void code() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
}
