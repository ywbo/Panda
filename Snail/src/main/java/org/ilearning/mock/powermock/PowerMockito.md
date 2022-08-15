# MOCK简介

## 一、Mockito和PowerMock的简介

Mockito和PowerMock是什么东西呢？他们有什么作用呢？Mocktio和PowerMock都是Mock的工具类，主要是Java的类库，Mock就是伪装的意思。他们适用于单元测试中，对于单元测试来说，我们不希望依赖于第三方的组件，比如数据库、Webservice等。在写单元测试的时候，我们如果遇到了这些需要依赖第三方的情况，我们可以使用mock的技术，伪造出来我们自己想要的结果。对于Java而言，mock的对象主要是Java方法和Java类。

### 1. 下面我就介绍一下怎么使用Mockito和PowerMockito去进行Mock。

#### Mockito和PowerMock的区别

在我看来，PowerMock是Mockito的一种增强。PowerMock可以调用Mockito的方法，但是对于Mocktio不能Mock的对象或者方法，我们可以使用PowerMock来实现。比如Mockito不能用于static Method, final method, 枚举类，private method，这些我们都可以用Powermock来实现，当Powermock和Mockito结合使用的时候，我们需要考虑兼容性的问题。主要表现在两者的版本需要兼容。

参考https://github.com/powermock/powermock/wiki/Mockito

#### Mockito和PowerMock的用法

------------ Person.java -----------

```java
package com.mock;

public interface Person {
    String getName();
}
```

------------ Student.java-----------

```java
package com.mock;

public class Student implements Person {

    private int age;

    public Student() {

    }

    public Student(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    // public method
    @Override
    public String getName() {
        return "student";
    }
    
    // public static method
    public static String getStaticMethod() {
        return "staticMethod";
    }

    // public final method
    public final String getFinalMethod() {
        return "getFinalMethod";
    }

    // private method
    private String getPrivateMethod() {
        return "getPrivateMethod";
    }

    public String callPrivateMethod() {
        return getPrivateMethod();
    }

}
```

--------------- PersonType.java -----------------

```java
package com.mock;

/**
 * Enum
 */
public enum PersonType {
    S("student"), N("normal");

    private String type;

    PersonType(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

}
```

上面是需要用到的三个源文件，下面就是各个mock实例

#### 1. Mock Interface，这是正常的Mock，mock()函数可以调用mockito的，也可以调用powermock的。

```
@Test public void testMockInterface() { Person person = mock(Person.class); String mockPersonGetName = "
mockPersonGetName"; when(person.getName()).thenReturn(mockPersonGetName);

     assertThat(person.getName(), is(mockPersonGetName));

}
```

#### 2. Mock Normal Class, 这也是正常的mock, 可以使用mockito或者是powermock的mock方法。

```java
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
```

#### 3. Mock Final Class，这种Mock就必须使用powermock了，而且使用powermock，需要在测试类上面加两个注解。@PrepareForTest里面的类，就是你要mock的方法所在的类。

```java
@RunWith(PowerMockRunner.class)
@PrepareForTest({Student.class,PersonType.class})
// ------- 
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
```

#### 4.Mock Private Method，这种Mock也必须使用powermock，我在下面演示的代码使用了spy， 这是因为spy之后是部分mock，这里我只想mock getPrivateMethod(), 而不想Mock callPrivateMethod。但是mock是会把类里面的所有的方法都重新构造，这样就达不到测试private method的目的了。

```java
@Test 
public void testMockPrivateMethod()throws Exception{
    Student student=PowerMockito.spy(new Student());
    String mockPrivateMethod="mockPrivateMethod";

    PowerMockito.when(student,"getPrivateMethod").thenReturn(mockPrivateMethod);

    assertThat(student.callPrivateMethod(),is(mockPrivateMethod));

}

@Test 
public void testMockPrivateMethodWithStub()throws Exception{
    Student student=new Student();String
    mockPrivateMethod="mockPrivateMethod";
    PowerMockito.stub(MemberMatcher.method(Student.class,"getPrivateMethod")).toReturn(mockPrivateMethod);

    assertThat(student.callPrivateMethod(),is(mockPrivateMethod));

}
```

##### 第二种是使用stub的方式去Mock private的方法，这里就不详细阐述这种方式了，我将在后面的文章里面详细介绍这种用法。 

#### 5.Mock Static Method，这种方式也必须使用powermock.

```java
@Test
public void testMockStaticMethod(){
    mockStatic(Student.class);
    String mockStaticMethod="mockStaticMethod";
    when(Student.getStaticMethod()).thenReturn(mockStaticMethod);

    assertThat(Student.getStaticMethod(),is(mockStaticMethod));

}
```

#### 6.Mock Constructor，这种方式也需要依赖powermock.

```java
@Test 
public void testMockConstructMethod()throws Exception{
    Student student1000=new Student(1000);
    PowerMockito.whenNew(Student.class).withArguments(10).thenReturn(student1000);

    Student student10=new Student(10);

    assertThat(student10.getAge(),is(1000));

}
```

#### 7.Mock Enum，这种方式也需要依赖powermock

```java
@Test
public void testMockEnum(){
    PersonType personType=PowerMockito.mock(PersonType.class);
   	Whitebox.setInternalState(PersonType.class,"N",personType);
    when(personType.getType()).thenReturn("mockN");

	assertThat(PersonType.N.getType(),is("mockN"));
    assertThat(PersonType.S.getType(),is("student"));

}
```

- ##### 这里需要注意一点Whitebox.setInternalState

```java
Whitebox.setInternalState(PersonType.class,"N",personType);
```

- ##### 第二个参数是枚举实例的名字，这里有两个枚举实例,N和S，我这里只需要mock实例N，所以这里就制定了实例N。而实例S并不会受到影响。


```java
assertThat(PersonType.S.getType(),is("student"));
```

总结 关于mockito和powermock就介绍到这里，上面的方式可以覆盖我们我们工作中的大部分了，对于一些嵌套很多，中间变量很多的这些情况，我们就需要使用他们的高级特性，下面的文章我将要介绍stub和powermock的高级特性。
