package org.example.demo.impl;

import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import org.example.demo.api.DemoService;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class DemoServiceImpl implements DemoService {

    private final MockingDemo mockingDemo;

    @Inject
    public DemoServiceImpl(MockingDemo mockingDemo) {
        this.mockingDemo = mockingDemo;
        //String value = mockingDemo.data;
    }

    @Override
    public ServiceCall<NotUsed, Pair<String, String>> demo(String id) {
        return request -> {
            String data = "Id_value";
            String res = mockingDemo.mockDemo("abc");
            String res1 = mockingDemo.mockDemo("ab");
            // String res = "abc";
            Pair<String, String> result = Pair.create(data + res, id);
            return CompletableFuture.completedFuture(result);
        };
    }

    @Override
    public HeaderServiceCall<String, Pair<String, String>> demoPost(String id, String name) {
        return (requestHeader, request) -> {
            Pair<String, String> result = Pair.create(id, name);
            //return CompletableFuture.completedFuture(Pair.create(requestHeader,"ghyhg"));

            return CompletableFuture.completedFuture(Pair.create(ResponseHeader.OK,
                    result));
        };
    }
}
