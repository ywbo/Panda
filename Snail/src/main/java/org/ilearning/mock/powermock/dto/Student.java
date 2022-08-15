
package org.ilearning.mock.powermock.dto;

import org.ilearning.mock.powermock.service.Person;

/**
 * 功能描述
 *
 * @author yuwenbo
 * @since 2022-08-15
 */
public class Student implements Person {
    private int age;

    public Student() {
    }

    public Student(int age) {
        this.age = age;
    }

    // 普通公共方法
    @Override
    public String getName() {
        return "student";
    }

    // 公共静态方法
    public static String getStaticMethod() {
        return "staticMethod";
    }

    // 公共 final 方法
    public final String getFinalMethod() {
        return "getFinalMethod";
    }

    // 普通私有方法
    private String getPrivateMethod() {
        return "getPrivateMethod";
    }

    public String callPrivateMethod() {
        return getPrivateMethod();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
