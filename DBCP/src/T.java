import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class T {
    @Test
    public void t1() throws SQLException {
        Connection conn = DBCPUtil.getConn();
        System.out.println(conn);
    }
    @Test
    public void t2(){
        for (int i = 0; i < 20; i++) {
            Connection conn = DBCPUtil.getConn();
            System.out.println(conn);
        }
    }
    @Test
    public void t3(){
        for (int i = 0; i < 15; i++) {
            Connection conn = DBCPUtil.getConn();
            System.out.println(conn+"-------"+i);
        }
    }
    @Test
    public void t4(){
        for (int i = 0; i < 20; i++) {
            Connection conn = DBCPUtil.getConn();
            System.out.println(conn+"------"+i);
        }
    }
}
