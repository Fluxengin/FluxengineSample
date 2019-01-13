<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet'
	type='text/css'>
<title>Hello Fluxengine Web Service</title>
</head>
<body>
	<h1>Hello Fluxengine Web Service -- GAE!</h1>

	<p>Fluxengine Web Serviceのサンプルです</p>

	<h2>Usage:</h2>
	<ol>
		<li><p>DSLをDataStoreに登録してください。</p></li>
		<li><p>
				POSTリクエストボディにEventのJSON文字列を設定し、次のURLを実行してください：<br />
				{Domain}/fluxengine-web/event
			</p></li>
		<li><p>["status":"SUCCEED"]がレスポンスに出力されたら成功です。</p></li>
	</ol>
</body>
</html>
