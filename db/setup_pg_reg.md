## explain用サンプルDBを用意する

* この説明ではdockerのpostgresを使うことを想定しています。

1. データをダウンロードする
  * [サンプルデータのデプロイファイル](pg_reg.sql)をダウンロードして作業ディレクトリに置く
  * [サンプルDBの設定方法](../setup_sampledb_on_pg.md)に従ってシェルを起動するところまでやる
2. PostgreSQLを起動してデータベースを作成する
  
  ```
   # su - postgres
   $ createdb -T template0 regression
   $ exit
  ```

3. デプロイファイルを読み込ませる
 
  ```
   # psql -U postgres regression < pg_reg.sql
  ```

4. 以上！
