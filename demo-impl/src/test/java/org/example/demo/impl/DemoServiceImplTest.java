package org.example.demo.impl;

import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
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
    public void demoTest() throws Exception {
        new Expectations() {
            {
                mockingDemo.data = "found the data";
                mockingDemo.mockDemo("abc");
                result = "abc";
                mockingDemo.mockDemo("ab");
                result = "11";
                //   times = 2;
            }
        };

        Pair<String, String> actualResult = demoServiceImpl.demo("id")
                .invoke().toCompletableFuture().get();
        String expectedFirstValue = "Id_valueabc";
        String expectedSecondValue = "id";
        assertEquals("First value not matched", actualResult.first(), expectedFirstValue);
        assertEquals("Second value not matched", actualResult.second(), expectedSecondValue);
        assertEquals("Not matched Properly", mockingDemo.data, "found the data");

    }

    @Test
    public void demoPostTest() throws Exception {
        Pair<ResponseHeader, Pair<String, String>> actualResult = demoServiceImpl.demoPost("id", "name")
                .invokeWithHeaders(RequestHeader.DEFAULT, "anything").toCompletableFuture().get();
        Pair expectedResult = Pair.create("id", "name");
//        System.out.println(actualResult);
//        System.out.println(expectedResult);
        assertEquals("Output Matched", expectedResult, actualResult.second());
    }
}
