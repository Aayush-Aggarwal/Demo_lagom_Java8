package org.example.demo.api;

import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.named;

public interface DemoService extends Service {

    ServiceCall<NotUsed , Pair<String,String>> demo(String id);

    ServiceCall<String ,  Pair<String,String>> demoPost(String id, String name);

    @Override
    default Descriptor descriptor(){
        // @formatter:off
        return named("demo").withCalls(
                Service.restCall(Method.POST,"/api/demoPath/:name", this::demoPost),
               Service.pathCall( "/api/demo/:id/:name" , this::demo)
        ).withAutoAcl(true);
        // @formatter:on
    }
}
