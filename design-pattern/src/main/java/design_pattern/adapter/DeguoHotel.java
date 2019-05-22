package design_pattern.adapter;

import design_pattern.adapter.iface.GuojiSocket;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class DeguoHotel {

    private GuojiSocket guojiSocket;

    public DeguoHotel(GuojiSocket guojiSocket) {
        this.guojiSocket = guojiSocket;
    }

    public void charge(){
       guojiSocket.method();
    }
}
