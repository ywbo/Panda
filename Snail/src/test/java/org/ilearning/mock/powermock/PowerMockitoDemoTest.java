
package org.ilearning.mock.powermock;

import org.ilearning.mock.powermock.dto.Student;
import org.ilearning.mock.powermock.type.PersonType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * PowerMockitoDemoTest
 *
 * @author yuwenbo
 * @since 2022-08-15
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Student.class, PersonType.class})
public class PowerMockitoDemoTest {
    /**
     * 测试正常方法
     */
    @Test
    public void test_MockNormalMethod() {
        Student student = PowerMockito.mock(Student.class);
        String mockStudentGetName = "mockStudentGetName";
        PowerMockito.when(student.getName()).thenReturn(mockStudentGetName);
        Assert.assertEquals(student.getName(), mockStudentGetName);
    }

    /**
     * 测试 final 方法
     */
    @Test
    public void test_MockFinalMethod() {
        Student student = PowerMockito.mock(Student.class);
        String mockFinalMethod = "mockFinalMethod";
        PowerMockito.when(student.getFinalMethod()).thenReturn(mockFinalMethod);
        Assert.assertEquals(student.getFinalMethod(), mockFinalMethod);
    }

    /**
     * 测试 private 方法
     */
    @Test
    public void test_MockPrivateMethod() throws Exception {
        Student student = PowerMockito.spy(new Student());
        String mockPrivateMethod = "mockPrivateMethod";
        PowerMockito.when(student, "getPrivateMethod").thenReturn(mockPrivateMethod);
        Assert.assertEquals(student.callPrivateMethod(), mockPrivateMethod);
    }

}
