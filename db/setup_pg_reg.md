## explain用サンプルDBを用意する

* この説明ではdockerのpostgresを使うことを想定しています。

1. データをダウンロードする
  * [サンプルデータのデプロイファイル](pg_reg.sql)をダウンロードして作業ディレクトリに置く
  * [サンプルDBの設定方法](../setup_sampledb_on_pg.md)に従ってシェルを起動するところまでやる
  * pg_reg.sqlをtmp/dbの下に移動させる
　
  ```
  % mv pg_reg.sql tmp/db/.
  ```
  
2. PostgreSQLを起動してデータベースを作成する
  
  ```
   # su - postgres
   $ createdb -T template0 regression
   $ exit
  ```

3. デプロイファイルを読み込ませる
 
  ```
   # psql -U postgres regression < /var/lib/postgresql/data/pg_reg.sql
  ```

4. 以上！

## トラブルシューティング
Q1.pg_reg.sqlが/var/lib/postgresql/dataの下にないです

A1.どうやらdockerコンテナ上のディレクトリとホストPCとのディレクトリがマウントできていないようですね。Windowsだとあるケースのようです。その場合はこれを試して見てください。

  1. コンテナ上で端末を立ち上げる

    
    % docker exec -it <コンテナ名> bash
    

  2. 直接githubからpg_reg.sqlファイルを持って来る
  
    
    # apt-get update
    # apt-get install curl
    # curl https://raw.githubusercontent.com/OchaDB/dbms18/master/db/pg_reg.sql > /var/lib/postgresql/data/pg_reg.sql
    
