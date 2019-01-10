# SampleLocalProject
エンジンであるFluxengineを用いて、モデル駆動開発を実施することができます。
DSLやプラグイン(java)のサンプルソースが格納されています。

# Dependency
使用言語：java
ビルドツール：maven (のちにgradle対応)

# Version
Fluxengine 1.0.0

# SetUp
プロジェクトを実行するためにエンジンのjarが必要となります。


1. 当プロジェクトをチェックアウト

2. エンジンのjar(fluxengine-xxx-.<version no>.jar)をダウンロードする

3. 2で取得したjarをチェックアウトしたプロジェクトのlibフォルダに格納する

4. . ビルドを実施（現時点ではmavenプロジェクトなので、maven installを実施してください）

# Usage
サンプルＤＳＬの実行
  以下eclipseで動作させる前提での内容です

  1. /SampleLocalProject/src/main/java/jp/co/fluxengine/example/apptest/DslTestExecutor.java の実行構成を開く (メニュー＞実行＞実行構成）

  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j.xmlパスを設定する

    例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\SampleLocalProject\conf\log4j2.xml"

  3. 環境タブの環境変数に以下を設定する

    <キー> / <値>

    CONF / C:\Users\xxx\git\SampleLocalProject\conf\


  4. DslTestExecutor.java に記載されているパスをチェックアウトした環境に合わせる

  5. DslTestExecutor.java を実行する

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
