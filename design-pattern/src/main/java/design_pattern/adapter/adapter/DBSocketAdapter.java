package design_pattern.adapter.adapter;

import design_pattern.adapter.iface.DBSocket;
import design_pattern.adapter.iface.GuojiSocket;

/**
 * 适配器模式
 *
 * 		主要作用：将一个类的接口转换成另外一个客户希望的接口
 *
 * 这个类就相当于实际案例中的电源转换头
 * @author fitbbc
 * @date 2019/05/22
 */
public class DBSocketAdapter implements GuojiSocket {

    private DBSocket dbSocket;

    public DBSocketAdapter(DBSocket dbSocket) {
        this.dbSocket = dbSocket;
    }

    public void method() {
        dbSocket.method();
    }
}
