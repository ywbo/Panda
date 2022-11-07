
package org.ilearning.mock.powermock;

import org.ilearning.mock.powermock.dao.OrderDao;

/**
 * 功能描述
 *
 * @author yuwenbo
 * @since 2022-08-15
 */
public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * 普通私有方法
     *
     * @return
     */
    private int update_01() {
        try {
            orderDao.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("normal private method: update_01");
        return 1;
    }

    /**
     * 静态私有方法
     *
     * @return
     */
    private static void updateById() {
        System.out.println("static method with no args: updateById");
    }

    /**
     * 静态带参私有方法
     *
     * @return
     */
    private static void updateById(String param) {
        System.out.println("static method with one args: updateById");
    }

    /**
     * 静态带参和返回值的私有方法
     *
     * @return
     */
    private static int updateByIdWithParam(String param) {
        System.out.println("static method with one args: updateByIdWithParam");
        return 0;
    }

}
