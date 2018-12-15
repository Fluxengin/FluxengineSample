# FluxengineSample
エンジンであるFluxengineを用いて、モデル駆動開発を実施することができます。
DSLやプラグイン(java)のサンプルソースが格納されています。

# Dependency
使用言語：java
ビルドツール：maven (のちにgradle対応)

# SetUp
データを永続化するためにGCPが必要となります。


1. 当プロジェクトをチェックアウト

2. コアとなるエンジンのjar(fluxengine-core.<version no>.jar)をダウンロードする

3. 2で取得したjarをチェックアウトしたプロジェクトのlibフォルダに格納する

4. GCPのサービスアカウントキーファイルをダウンロードする

5. チェックアウトしたプロジェクトのsrc/main/resourcesフォルダに4で取得したファイルを格納する

6. チェックアウトしたプロジェクトのsrc/main/resourcesフォルダにあるプロパティファイル群を修正する

  1. datastore.properties
  ```
  namespace=<GCPの名前空間を設定>
  kind=PERSISTER
  projectId=<GCPのプロジェクトIDを設定>
  keyFile=<GCPのサービスアカウントキーファイルへのパスを記述（同フォルダに配置した場合はファイル名のみ）>
  ```
  
7. ビルドを実施（現時点ではmavenプロジェクトなので、maven installを実施してください）

# Usage
サンプルＤＳＬの実行
  以下eclipseで動作させる前提での内容です
  
  1. /FluxengineAddOnSample/src/main/java/jp/co/fluxengine/apptest/DslTestExecutor.java の実行構成を開く (メニュー＞実行＞実行構成）
  
  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j.xmlパスを設定する
  
    例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\FluxengineSample\src\main\resources\log4j.xml"
    
  3. 環境タブの環境変数に以下を設定する
  
    <キー> / <値>
  
    CONF / C:\Users\xxx\git\FluxengineSample\src\main\resources\
    
    TEST / true
    
  4. DslTestExecutor.java に記載されているパスをチェックアウトした環境に合わせる
  
  5. DslTestExecutor.java を実行する

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
