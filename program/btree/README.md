## B*-treeプログラムを書くのに利用できるメソッドたち

### Databaseクラス
 * コンストラクタ Database(int order)
  * 実行される処理
    * ノードのorderが設定されます。（Node.ORDERというクラス変数として参照できます）
    * 最初のルートノード（LeafNode）が作られます。
 
 * Entry insertRecord(Record record) : recordを挿入します
  * 使用例：Order 2のbtreeを作ってレコード("Ayumi",32)を挿入する
 
  ```
   Database db = new Database(2);
   db.insertRecord(new Record("Ayumi",32));
  ```
 
 * void linkDecendantNodes(NonLeafNode N, NonLeafNode M) : 
  * Nの子孫の一番右のノードとMの子孫の一番左のノードをお互いに兄弟リンクを貼ります

 * Node getRootNode() : ルートノードを取得する

 * Node getNode(int p) : ノード番号pのノードを取得する
  * ポインタの値はノード番号ですので、これを引数に入れてノードを取得します

### Nodeクラス
 * int getType() : ノードのタイプを出力する
   * 戻り値：以下の定数のどちらかが返ってくる
     * Node.TYPENONLEAF : NonLeafNodeである
     * Node.TYPELEAF : LeafNodeである
   * 使い方
     * ノードNがNonLeafNodeならば…する

      ```
       if(N.getType()==Node.TYPENONLEAF){
         ...
       }
      ``` 
 * boolean isRootNode() : ノードがルートノードかどうか調べる
   * 戻り値：ルートノードならtrue, そうでなければfalse
   
 * boolean hasSpace() : エントリを挿入できる空きがある
   * 戻り値：空きがあればtrue, なければfalse

 * int getSize() : ノードのサイズを取得する
   * 戻り値：NonLeafNodeであればkey値の数、LeafNodeであればレコード数を返す

### NonLeafNodeクラス/LeafNodeクラス 共通
 * int SearchPosition(int q) : ki < q < ki+1となるiを求める
   * 戻り値：iの値


### NonLeafNodeクラス
 * Entry insertEntry(Entry ent) : NonLeafNodeにentを挿入する
   * 戻り値：
      * 分割が起きた時は分岐entryが返る
      * 分割が起きなかった場合はnullが返る

 * void moveKeys(int from, int until, NonLeafNode M) : from番目からuntil番目のキー値をMに移動
   * 注意：
      * 0番目から始まります

 * void movePointers(int from, int until, NonLeafNode M) : from番目からuntil番目のポインタをMに移動
   * 注意：
      * 0番目から始まります

### LeafNodeクラス
 * void insertRecord(Record record) : LeafNodeにrecordを挿入する

 * void moveRecords(int from, int until, LeafNode M) : from番目からuntil番目のレコードをMに移動
   * 注意：
     * 0番目から始まります

 * Record getRecord(pos) : pos番目のレコードを取得する
   * 注意：
     * 0番目から始まります

### Entryクラス
 * コンストラクタ Entry(int key, int pointer) : entryを作成します
 * コンストラクタ Entry(Record record) : recordを元にentryを作成します

### Recordクラス
 * コンストラクタ Record(String name, int age) : Recordを作成します
 * int getKeyValue() : キー値（age）を出力します

