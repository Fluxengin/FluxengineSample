package jp.co.fluxengine.web.sample;

import javax.xml.ws.Endpoint;

import jp.co.fluxengine.web.FluxengineWeb;

public class PublishService {

	public static void main(String[] args) {

		String address = "http://192.168.10.4:8989/WS_Server/Webservice";
		Endpoint.publish(address, new FluxengineWeb());
		System.out.println("webservice publish 成功しました!");
	}

}
