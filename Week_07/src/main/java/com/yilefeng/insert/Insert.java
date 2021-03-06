package com.yilefeng.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yilefeng on 2021/3/7.
 */
public class Insert {
    private static final String URL = "jdbc:mysql://localhost:3306/test_db?serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "deepwise";


    /**
     * 线程数
     */
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static final String sql =
            "INSERT INTO `order`(order_id, product_id,user_id, `count`, unit_price, real_price,discount_price) VALUES('order_01',2,3,4,5,6,7)";


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Mac 8G 耗时152s
//        batchInsert(1000000);
        //4个线程 8G 耗时
        threadInsert(1000000);
    }

    /**
     * @throws SQLException
     */
    public static void batchInsert(int rowcount) throws SQLException {
        long startMs = System.currentTimeMillis();
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < rowcount; i++) {
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();

        }
        System.out.print("Take time" + (System.currentTimeMillis() - startMs));
    }


    public static void threadInsert(int rowcount) throws InterruptedException {
        long startMs = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                try {
                    batchInsert(rowcount / THREAD_COUNT);
                    countDownLatch.countDown();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }, String.format("插入线程%d", i));
            thread.start();
        }
        countDownLatch.await();
        System.out.print("Take time" + (System.currentTimeMillis() - startMs));
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
