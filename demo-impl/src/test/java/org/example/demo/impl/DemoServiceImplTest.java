package org.example.demo.impl;

import akka.japi.Pair;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import org.junit.Test;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import static org.junit.Assert.assertEquals;


public class DemoServiceImplTest {

    @Tested
    private DemoServiceImpl demoServiceImpl;

    @Injectable
    private MockingDemo mockingDemo;

    @Test
    public void demoTest() throws Exception{
        new Expectations(){
            {

            mockingDemo.mockDemo("abc");
            result = "abc";
            mockingDemo.mockDemo("ab");
            result = "ab";
         //   times = 2;
            }
        };

        Pair<String,String> actualResult = demoServiceImpl.demo("id")
                .invoke().toCompletableFuture().get();
        String expectedFirstValue = "Id_valueabc";
        String expectedSecondValue = "id";
        assertEquals ("hh",actualResult.first(),expectedFirstValue);
        assertEquals ("",actualResult.second(),expectedSecondValue);

    }

    @Test
    public void demoPostTest() throws Exception{
       HeaderServiceCall<String, Pair<String, String>> actualResult = demoServiceImpl.demoPost("id","name");

    }
}
