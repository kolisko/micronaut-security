
package io.micronaut.docs.jwtclaimsoverride;

import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import java.util.Collections;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Requires(property = "spec.name", value = "jwtclaimsoverride")
//tag::clazz[]
@Singleton
public class CustomAuthenticationProvider<T> implements AuthenticationProvider<T> {

    @Override
    public Publisher<AuthenticationResponse> authenticate(T httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            emitter.next(AuthenticationResponse.success("sherlock", Collections.singletonMap("email", "sherlock@micronaut.example")));
            emitter.complete();
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
//end::clazz[]
