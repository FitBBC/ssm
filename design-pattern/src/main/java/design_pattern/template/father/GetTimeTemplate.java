package design_pattern.template.father;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public abstract class GetTimeTemplate {

    public long getTime(){
        long t1 = System.currentTimeMillis();


        code();


        long t2 = System.currentTimeMillis();
        return t2- t1;
    }

    protected abstract void code();
}
