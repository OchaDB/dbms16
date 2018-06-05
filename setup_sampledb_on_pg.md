# サンプルDBの設定方法

## 構成
* Dockerを使ってローカルPCの上にPostgreSQLの初期設定をした仮想イメージをおく
* ローカルPCから仮想イメージに入ってsqlを実行する
* 一緒にDBのGUIツールも設定して http://localhost:8080 でアクセスできるようにする

## 手順(設定編)
* Dockerをインストールする（説明は省略）
* Dockerを起動する（説明は省略）
* 適当な作業用ディレクトリを用意する

```
 % mkdir dbms18
 % cd dbms18
```

* Dockerの設定ファイル (docker-compose.yml)[db/docker-compose.yml] を作業用ディレクトリに保存する。
* 設定ファイルを元にPostgreSQLとGUIツールの設置をする

```
 % docker-compose up
```

* 設定はこれでおしまい。

## 手順（SQLを実行できるようになるまで）
* PostgreSQLのイメージ名を取得する

```
 % docker-compose ps
         Name                      Command               State            Ports
---------------------------------------------------------------------------------------
postgresql_adminer_1   entrypoint.sh docker-php-e ...   Up      0.0.0.0:8080->8080/tcp
postgresql_db_1        docker-entrypoint.sh postgres    Up      0.0.0.0:32768->5432/tcp
```

* 上記の出力で「postgresql_db_1」がイメージ名となる
* 対象イメージにアクセスしてシェルを起動する

```
 % docker exec -it postgresql_db_1 bash
```

* PostgreSQLのクライアントを起動する

```
 # psql -U postgres
```

* 抜けるときは

```
 > \q
```

## 手順（サンプルデータベースを用意する）
* サンプルデータのダンプファイル (data.sql)[db/data.sql] をダウンロードして tmp/dbの下に置く。
* PostgreSQLのクライアントで以下のように実行するとサンプルDBが作られます。

```
 > \i /var/lib/postgresql/data/data.sql
```
* 中身を確認してみましょう

```
 > select count(*) from customer;
````
* 999999行のデータが入っています。


