package de.scrum_master.stackoverflow.q74575745;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

class AuthRequestInterceptor implements Interceptor {
  @Override
  public Response intercept(Interceptor.Chain chain) throws IOException {
    Request original = chain.request();

    // Request customization: add request headers
    Request.Builder requestBuilder = original.newBuilder()
      .header("Authorization", "auth-value");

    Request request = requestBuilder.build();
    return chain.proceed(request);
  }
}
