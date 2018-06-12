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

* Dockerの設定ファイル [docker-compose.yml](db/docker-compose.yml) を作業用ディレクトリに保存する。
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
* サンプルデータのダンプファイル [data.sql](db/data.sql) をダウンロードして tmp/dbの下に置く。
* PostgreSQLのクライアントで以下のように実行するとサンプルDBが作られます。

```
 > \i /var/lib/postgresql/data/data.sql
```
* 中身を確認してみましょう

```
 > select count(*) from customer;
```
* 999999行のデータが入っています。

## 索引を作る
* 以下のSQL文を実行しましょう。

```
 create index idx_cust_purchase on customer(purchase);
```

## 問い合わせのプランを確認してみる
* SQLの前にexplainをつけるとプランを出してくれます。

```
 explain select count(*) from customer where purchase < 100;
```
## 特定のアルゴリズムを選ばないようにする
SQL文で以下のように設定すると、アルゴリズムを使うかどうかの
選択ができます。
```
 SET enable_<アルゴリズム> = [ON/OFF]
```
たとえば索引を使わないようにするには

```
SET enable_indexscan = OFF
```
で使わないようにすることができます。
ただ、これと合わせて

```
SET enable_bitmapscan = OFF
```

も実行しないと索引を使われるかもしれないです。
explainを見て確認をしましょう。

