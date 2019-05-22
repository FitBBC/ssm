package design_pattern.adapter.adapter;

import design_pattern.adapter.iface.DBSocket;
import design_pattern.adapter.iface.GBSocket;
import design_pattern.adapter.iface.GuojiSocket;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class GBSocketAdapter implements GuojiSocket {

    private GBSocket gbSocket;

    public GBSocketAdapter(GBSocket gbSocket) {
        this.gbSocket = gbSocket;
    }

    public void method() {
        gbSocket.method();
    }
}
