package com.april.test;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class Test {
    public static void main(String[] args) throws Exception {
        /* 打印参数className名的方法 */
        printMethods("com.april.test.JavaSsistTest");
        /* 针对参数className， 增加newMethod参数方法 */
        CtClass ctClass = classAddNewMethod("com.april.test.JavaSsistTest",
                "public int getNum(){System.out.println(\"我是新增的方法 \");return this.num;}");
        /* 针对参数方法，在方法前后insert语句 */
        doInsert4Method("com.april.test.JavaSsistTest", "getNum");
        /* 再次打印所有方法 */
        System.out.println("after add getNum() method");
        printMethods("com.april.test.JavaSsistTest");
        System.out.println("调用方法。。。。");
        /* 调用新增的方法 */
        invokeNewMethod(ctClass, "getNum");
    }
    /**
     * 打印参数className名的方法
     */
    private static void printMethods(String className) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(className);
        /* 打印所有方法 */
        CtMethod[] methods = clazz.getMethods();
        System.out.println("######## printMethods() begin ########");
        for (CtMethod method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("######## printMethods() end ########");
    }

    /**
     * 针对参数className， 增加newMethod参数方法
     */
    private static CtClass classAddNewMethod(String className, String newMethod) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(className);

        /* 新增getNum方法 */
        CtMethod method = CtNewMethod.make(newMethod, clazz);
        clazz.addMethod(method);
        clazz.writeFile();
        clazz.writeFile("D:\\");
        return clazz;
    }

    /**
     * 在className的insertMethod方法中，insert代码
     */
    private static void doInsert4Method(String className, String insertMethod) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(className);
        /* 由于前期已进行编辑，则此时是冻结状态，需先调用解冻方法，才能对方法进行再次编辑 */
        clazz.defrost();
        CtMethod method = clazz.getDeclaredMethod(insertMethod);
        /* 方法前执行 */
        method.insertBefore("System.out.println(\"前 ~~\");");
        /* 方法后执行 */
        method.insertAfter("System.out.println(\"后 ~~\");");
        clazz.writeFile();
    }

    /**
     * 调用新增的方法
     */
    private static void invokeNewMethod(CtClass ctClass, String newMethodName) throws Exception {
        //反射原理调用方法
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod(newMethodName, null);
        System.out.println(method.invoke(obj, null));
    }
}

class JavaSsistTest {
    private int num;
    public void setNum(Integer num) {
        this.num = num;
    }
}