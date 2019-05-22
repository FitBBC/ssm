package design_pattern.adapter;

import design_pattern.adapter.adapter.DBSocketAdapter;
import design_pattern.adapter.adapter.GBSocketAdapter;
import design_pattern.adapter.iface.DBSocketImpl;
import design_pattern.adapter.iface.GBSocketImpl;

import javax.crypto.spec.DESedeKeySpec;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class AdapterTest {
    public static void main(String[] args) {
        DeguoHotel deguoHotel = new DeguoHotel(new GBSocketAdapter(new GBSocketImpl()));
        deguoHotel.charge();


        DeguoHotel deguoHotel1 = new DeguoHotel(new DBSocketAdapter(new DBSocketImpl()));
        deguoHotel1.charge();
    }
}
