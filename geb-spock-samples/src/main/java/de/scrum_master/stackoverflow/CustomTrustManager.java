package de.scrum_master.stackoverflow;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * See https://stackoverflow.com/a/57622199/1082681
 */
public class CustomTrustManager {
  public static void main(String[] args) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
    InputStream stream;
    stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cacerts.jks");
    System.out.println(stream);
    if (stream == null) {
      stream = CustomTrustManager.class.getClassLoader().getResourceAsStream("cacerts.jks");
    }
    if(stream == null) {
      System.out.println("Unable to load cacerts.jks. This is needed to make HTTPS connections to internal servers.");
      throw new RuntimeException("Keystore not found");
    }

    KeyStore myTrustStore = KeyStore.getInstance("JKS");
    myTrustStore.load(stream, "testtest".toCharArray());
    System.out.println("Keystore loaded");
  }
}
