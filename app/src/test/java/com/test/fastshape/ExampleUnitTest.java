package com.test.fastshape;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void sfd() throws Exception {
        int i = Integer.MAX_VALUE >> 2;
        System.out.println(i+"===========");
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        TestA a=new TestA();
        a.b();
//        a.a="aaaaa";
//        U.testA(a);
        System.out.println(a.a);
    }

    public class TestA{
        protected String a;
        public void b(){
            a="bb";
            U.testA(this);
            a="bb2";
        }
    }
    public static class U{
        public static void testA(TestA testA){
            testA.a="void2";
        }
    }
}