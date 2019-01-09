package jp.co.fluxengine.web.sample;

import javax.xml.ws.Endpoint;

import jp.co.fluxengine.web.FluxengineWeb;

/**
 *
 *  デプロイする際に、urlを適当に変更してください。
 *
 */
public class PublishService {

	public static void main(String[] args) {

		String url = "http://192.168.10.4:8989/WS_Server/Webservice";
		Endpoint.publish(url, new FluxengineWeb());
		System.out.println("webservice publish 成功しました!");
	}

}
