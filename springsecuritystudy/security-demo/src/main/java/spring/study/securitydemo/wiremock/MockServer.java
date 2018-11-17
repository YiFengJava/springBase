package spring.study.securitydemo.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MockServer {

    public  static  void main(String[] arg){
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();
//        WireMock.stubFor(WireMock.get)
    }
}
