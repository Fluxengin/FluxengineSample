# SampleWebServiceProject
fluxengine-web-serviceとプラグインを用いて、Web Serviceをデプロイします。

# Dependency
使用言語：java
ビルドツール：maven (のちにgradle対応)

# Version
Fluxengine 1.0.1

# Usage

  以下eclipseで動作させる前提での内容です

  1.Webサーバを起動する。

  2.必要なjarファイルを　WEB-INF/libの下に置いてください。

  3.PublishServiceの
  　String url = "http://192.168.10.4:8989/WS_Server/Webservice";
  　を適当に修正して、mainメソッドを実行する。

  　
   4.webservice publish 成功しました!　がコンソールに出力されたら、デプロイが成功になります。

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
