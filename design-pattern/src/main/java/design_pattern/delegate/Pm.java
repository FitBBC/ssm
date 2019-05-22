package design_pattern.delegate;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Pm {
    private Dev delegate;

    public Dev getDelegate() {
        return delegate;
    }

    public void setDelegate(Dev delegate) {
        this.delegate = delegate;
    }

    public void doSomething(){
        delegate.dev();
    }
}
