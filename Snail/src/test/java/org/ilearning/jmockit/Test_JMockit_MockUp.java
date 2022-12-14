package org.ilearning.jmockit;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import org.ilearning.mock.jmockit.Calculator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JMockit : 使用 MockUp mock 函数行为
 *
 * @author yuwenbo
 * @date 2022/8/10 23:13
 **/
public class Test_JMockit_MockUp {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(Test_JMockit_MockUp.class);

    /**
     * mock非静态函数的返回值
     */
    @Test
    public void test_add_01() {
        logger.info("** test_add_01 **");
        Calculator calculator = new Calculator();
        logger.info("mock 之前......");
        logger.info("add={}", calculator.add(1, 2));

        // mock
        MockUp<Calculator> mock = new MockUp<Calculator>() {
            @Mock
            public int add(int a, int b) {
                return -1;
            }
        };
        logger.info("mock 之后......");
        logger.info("add={}", calculator.add(1, 2));
    }

    @Test
    public void test_add_02() {
        logger.info("** test_add_02 **");
        Calculator calculator = new Calculator();
        logger.info("add={}", calculator.add(1, 2));
    }

    /**
     * mock 静态函数
     */
    @Test
    public void staticAdd_01() {
        logger.info("** test_staticAdd_02 **");
        logger.info("mock 之前......");
        logger.info("add={}", Calculator.staticAdd(1, 2));
        // mock
        new MockUp<Calculator>() {
            @Mock
            public int staticAdd(int a, int b) {
                return -1;
            }
        };
        logger.info("mock 之后......");
        logger.info("add={}", Calculator.staticAdd(1, 2));
    }

    @Test
    public void test_staticAdd_02() {
        logger.info("** test_staticAdd_02 **");
        logger.info("add={}", Calculator.staticAdd(1, 2));
    }

    /**
     * 一个单测内多次 mock
     */
    @Test
    public void test_more_add() {
        logger.info("** test_more_add **");
        Calculator calculator = new Calculator();
        logger.info("mock 之前......");
        logger.info("add={}", calculator.add(1, 2));
        // mock
        new MockUp<Calculator>() {
            @Mock
            public int add(int a, int b) {
                return -1;
            }
        };
        logger.info("mock 之后，add={}", calculator.add(1, 2));

        // 再次 mock
        new MockUp<Calculator>() {
            @Mock
            public int add(int a, int b) {
                return -2;
            }
        };
        logger.info("再次 mock 之后，add={}", calculator.add(1, 2));

        // 三次mock
        new MockUp<Calculator>() {
            @Mock
            public int staticAdd(int a, int b) {
                return -100;
            }
        };
        logger.info("三次mock之后，add = {}", Calculator.staticAdd(1, 2));
    }

    /**
     * mock 无参函数
     */
    @Test
    public void test_noop_01() {
        logger.info("** test_noop_01 **");
        logger.info("mock 之前......");
        Calculator calculator = new Calculator();
        calculator.noop();

        // mock
        new MockUp<Calculator>() {
            @Mock
            public void noop() {
                logger.info("被 mock 了...");
            }
        };
        logger.info("mock 之后...");
        calculator.noop();
    }

    @Test
    public void test_noop_02() {
        logger.info("** test_noop_02 **");
        Calculator calculator = new Calculator();
        calculator.noop();
    }

    /**
     * mock 抛出异常
     */
    @Test
    public void test_exception_noop_01() {
        logger.info("** test_noop_01 **");
        logger.info("mock 之前......");
        Calculator calculator = new Calculator();
        calculator.noop();

        //mock
        new MockUp<Calculator>() {
            @Mock
            public void noop() {
                throw new RuntimeException("just test mock exception");
            }
        };
        logger.info("mock 之后...");
        calculator.noop();
    }

    /**
     * MockUp中使用Invocation调用原始逻辑
     * </p>
     * 在特定场景下，调用原始逻辑
     */
    @Test
    public void test_specific_scene() {
        Calculator calculator = new Calculator();

        // mock
        new MockUp<Calculator>() {
            @Mock
            public int add(Invocation invocation, int a, int b) {
                // 当 a == 1时，调用原始逻辑
                if (a == 1) {
                    return invocation.proceed(a, b);
                }
                return 1;
            }
        };
        logger.info("add_01 = {}", calculator.add(1, 2));
        logger.info("add_02 = {}", calculator.add(2, 1));
    }

    /**
     * MockUp中使用Invocation调用原始逻辑
     * </p>
     * 在特定场景下，多次调用原始逻辑
     */
    @Test
    public void test_specific_scene_more() {
        Calculator calculator = new Calculator();

        // MockUp 中多次 mock
        new MockUp<Calculator>() {
            @Mock
            public int add(Invocation invocation, int a, int b) {
                // 当 a == 1时，调用原始逻辑
                if (a == 1) {
                    return invocation.proceed(a, b);
                }
                return 1;
            }

            @Mock
            public void noop(Invocation invocation) {
                System.out.println("调用原始逻辑");
                invocation.proceed();
            }
        };
        logger.info("add_01 = {}", calculator.add(1, 2));
        logger.info("add_02 = {}", calculator.add(2, 1));

        calculator.noop();
    }

    /**
     * MockUp中使用mock私有化函数
     */
    @Test
    public void test_privateMethod_01() {
        Calculator calculator = new Calculator();
        logger.info("--- mock 之前 ---");
        calculator.noop();

        // mock
        new MockUp<Calculator>() {
            @Mock
            private void privateMethod() {
                logger.info("私有函数被 mock 了...");
            }
        };
        logger.info("--- mock 之后 ---");
        calculator.noop();
    }

    /**
     * 无参构造函数初始化
     */
    @Test
    public void test_noparameter_01() {
        logger.info("--- mock 之前 ---");
        new Calculator();

        // mock
        new MockUp<Calculator>() {
            @Mock
            public void init() {
                logger.info("无参构造函数被 mock 了");
            }

            @Mock
            public void clinit() {
                logger.info("静态代码块被 mock 了");
            }
        };

        logger.info("--- mock 之后 ---");
        new Calculator();
    }

    /**
     * 为什么 mock 之后，没有输出 静态代码块被 mock 了？因为类的静态代码块只会执行一次。
     * </p>
     * 将 mock 之前的新建对象注释掉
     */
    @Test
    public void test_noparameter_02() {
        // logger.info("--- mock 之前 ---");
        // new Calculator();

        // mock
        new MockUp<Calculator>() {
            @Mock
            public void init() {
                logger.info("无参构造函数被 mock 了");
            }

            @Mock
            public void clinit() {
                logger.info("静态代码块被 mock 了");
            }
        };

        logger.info("--- mock 之后 ---");
        new Calculator();
    }
}
