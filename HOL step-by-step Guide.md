![Microsoft Cloud Workshop](images/ms-cloud-workshop.png)

Azure Container Hands-on lab  
Mar. 2023

<br />

### Contents

## 開発環境の準備 

<img src="images/exercise-1.png" />

### Task 1: リポジトリのフォーク

- Web ブラウザを起動し、"<a href="https://github.com/kohei3110/AzureContainerApps-Hands-on-Lab-1" target="_blank">ワークショップのリポジトリ</a>" へ移動

- 画面右上の Fork をクリック

  <img src="images/github-fork-01.png" />

- 自身のアカウントにリポジトリが複製されていることを確認

<br />

### Task 2: 開発環境へのリポジトリのクローン

- Visual Studio Code を起動

- "**Terminal**" - "**New Terminal**" を選択し、ターミナルを表示

  <img src="images/git-config-01.png" />

- Git の初期設定を実行

  - ユーザー名の設定

    ```
    git config --global user.name "User Name"
    ```

    ※ User Name を自身の名前に変更

  - Email アドレスの設定

    ```
    git config --global user.email "Email@Address"
    ```

    ※ {Email Address} を使用するメール アドレスに変更

  - 設定値の確認
  
    ```
    git config --list --global
    ```

    ※ 設定したユーザー名・メール アドレスが出力されたら OK

    ```
    git remote -v
    ```

    ※ クローン先の GitHub URL が出力されたら OK

<br />

- Web ブラウザで Fork したリポジトリの "**Code**" をクリック

  表示されるツール チップよりリポジトリの URL をコピー

  <img src="images/git-clone-01.png" />

- Visual Studio Code のサイドバーから Explorer を選択し "\*\*Clone Repository" をクリック

  <img src="images/git-clone-02.png" />

- リポジトリの URL の入力を求められるためコピーした URL を貼り付け Enter キーを押下

  <img src="images/git-clone-03.png" />

- 複製先となるローカル ディレクトリ (Documents) を選択

  GitHub の認証情報が求められる場合は、アカウント名、パスワードを入力し認証を実施

- 複製されたリポジトリを開くかどうかのメッセージが表示されるので "**Open**" をクリック

- Explorer に複製したリポジトリのディレクトリ、ファイルが表示

<br />

## Exercise 1: Docker イメージの構築と実行

### Task 1: ローカルでのアプリケーションの実行

<details>
  <summary>C#</summary>

  - Visual Studio Code で "**Terminal**" - "**New Terminal**" を選択

  - ASP.NET Core プロジェクトのディレクトリへ移動

    ```
    cd src/CS/Web
    ```

  - アプリケーションを実行

    ```
    dotnet run
    ```

  - ターミナルに表示されるアプリケーションの URL を Ctrl キーを押しながらクリック

    <img src="images/dotnet-run-01.png" />

  - Web ブラウザが起動し、アプリケーションのトップ画面が表示

    <img src="images/dotnet-run-02.png" />

  - ターミナルで Ctrl + C を押下し、アプリケーションの実行を終了
</details>

<details>
  <summary>Java</summary>

</details>

<br />

### Task 2: 公開用ビルドのファイル セットの発行

<details>
  <summary>C#</summary>

  - ターミナルで展開のためのファイル セットをディレクトリへ発行

    ```
    dotnet publish -c Release -o ./bin/Publish
    ```

  - bin フォルダ内に Publish フォルダが生成され、アプリケーションとその依存関係が発行されていることを確認

    <img src="images/dotnet-publish.png" />

</details>

<details>
  <summary>Java</summary>

</details>

<br />

### Task 3: Docker ファイルの作成

<details>
  <summary>C#</summary>

  - Visual Studio Code の Explorer で "**.docker**" - "**CS**" を展開し "**dockerfile** を選択

    <img src="images/update-dockerfile-01.png" />

  - エディタ画面で編集

    9 行目に先の手順で発行したファイル セットをコピーする操作を追加

    ```
    COPY ../../src/CS/Web/bin/Publish .
    ```

  - ファイルを保存

</details>

<details>
  <summary>Java</summary>

</details>

<br />

### Task 4: Docker イメージの構築

- デスクトップ上の "**Uuntu**" ショートカットをダブルクリック

- 操作用のプロンプトが起動

- WSL で Windows 側のマウントされたディレクトリへ移動

  ```
  cd /mnt/c/Users/AzureUser/Documents/AzureContainerApps-Hands-on-Lab-1
  ```

- docker build コマンドを実行しイメージを構築

  <details>
    <summary>C#</summary>

    ```
    docker build -t app:v1 -f .docker/CS/dockerfile .
    ```

    ※ コマンドのオプション

    - **-t**: 名前とタグを **名前:タグ** の形式で指定

    - **-f**: dockerfile のパスを指定

    <img src="images/docker-build-01.png" />
  </details>

  <details>
    <summary>Java</summary>
    
  </details>

- docker images コマンドを実行し、構築されたイメージが表示されることを確認

  ```
  docker images
  ```

  <img src="images/docker-build-02.png" />
    
<br />

### Task 5: イメージからコンテナーを起動

<details>
  <summary>C#</summary>

  - docker run コマンドを実行し、作成したイメージからコンテナーを起動

    ```
    docker run -p 8080:80 app:v1
    ```

    ※ コマンドのオプション

      - **-p**: ポート マッピング（コンテナーの 80 番ポートを 8080 番ポートへマッピング）

    <img src="images/cs-docker-run-01.png" />

  - Web ブラウザを起動し http://localhost:8080 へアクセス

    <img src="images/cs-docker-run-02.png" />

  - 操作用のプロンプトで Ctrl + P, Ctrl + Q キーを押下し、アプリケーションを終了

</details>

<details>
  <summary>Java</summary>

</details>

- コンテナー一覧を表示するコマンドを実行

  ```
  docker ps -a
  ```

  ※ コマンドのオプション

    - **-a**: 起動中・停止中を含め、すべてのコンテナを表示

  <img src="images/docker-run-03.png" />

- 再度、イメージからコンテナーを起動

  ```
  docker run -d --rm -p 8080:80 app:v1
  ```

  ※コマンドのオプション

    - **-d**: デタッチド モードでコンテナを起動

    - **--rm**: コンテナ終了時にコンテナを削除

    - **-p**: ポート マッピング（コンテナの 80 番ポートを 8080 番ポートへマッピング）

  <img src="images/docker-run-04.png" />

- 起動中のコンテナーを確認

  ```
  docker ps
  ```
  <img src="images/docker-run-05.png" />

  ※ CONTAINER ID を確認

- Web ブラウザを起動し http://localhost:8080 へアクセス

- コンテナーを停止 (docker ps コマンドで確認した CONTAINER ID を指定)

  ```
  docker stop <CONTAINER ID>
  ```

- コンテナー一覧を表示するコマンドを実行

  ```
  docker ps -a
  ```

  ※ 2 度目に実行したコンテナーが表示されないことを確認

- 停止中のコンテナーを削除 (1 度目に実行したコンテナを削除)

  ```
  docker rm <CONTAINER ID> -f
  ```

  <img src="images/docker-run-06.png" />

- コンテナー一覧を表示するコマンドを実行

  ```
  docker ps -a
  ```

  ※ コンテナーが表示されないことを確認

<br />

## Exercise 2: Azure Container Registry の作成とイメージのプッシュ

### Task 1: Azure Container Registry の作成

- Web ブラウザで [Azure ポータル](#https://portal.azure.com)へアクセス

- ポータルのトップ画面で「**+ リソースの作成**」をクリック

  <img src="images/create-azure-resources.png" />

- 左側のメニューで「**コンテナー**」を選択し、「**Container Registry**」の「**作成**」をクリック

  <img src="images/create-azure-container-registry-01.png" />

- コンテナー レジストリの作成

  - 「**基本**」

    - **プロジェクト詳細**

      - **サブスクリプション**: ワークショップで使用するサブスクリプション

      - **リソース グループ**: ワークショップで使用するリソース グループ

    - **インスタンスの詳細**

      - **レジストリ名**: 任意 (英大文字小文字、数字のみで 5 から 50 文字)

      - **場所**: 任意

      - **SKU**: Standard

      <img src="images/create-azure-container-registry-02.png" />

  - 「**ネットワーク**」

    - **接続の構成**: パブリック アクセス (すべてのネットワーク)

      <img src="images/create-azure-container-registry-03.png" />

      ※ Premium SKU 選択時のみ構成可

  - 「**暗号化**」

    - **カスタマー マネージド キー**: 無効

      <img src="images/create-azure-container-registry-04.png" />

      ※ Premium SKU 選択時のみ構成可

- 「**確認および作成**」をクリックし、指定した内容を確認後、「**作成**」をクリック

  <img src="images/create-azure-container-registry-05.png" />

<br />

### Task 2: イメージをレジストリへプッシュ

- Azure ポータルで作成したコンテナー レジストリの管理ブレードへアクセス

- 左側のメニューから「**アクセス キー**」を選択

- 「**管理者ユーザー**」を有効化

  <img src="images/azure-container-registry-key.png" />

- 操作用のプロンプトでレジストリへログイン

  ```
  docker login yourregistry.azurecr.io
  ```

  ※ youreregistry.azurecr.io を作成したコンテナー レジストリのログイン サーバーに変更

  ※ コンテナー レジストリのログイン サーバー名は管理ブレードのアクセス キーから確認可

- Username, Password を指定し、ログインを実行

  ※ コンテナー レジストリの管理ブレードのアクセス キーから取得できるユーザー名とパスワードを使用

- レジストリへの完全修飾パスを使用して、イメージのエイリアスを作成

  ```
  docker tag app:v1 yourregistry.azurecr.io/app:v1
  ```

  ※ youreregistry.azurecr.io を作成したコンテナー レジストリのログイン サーバーに変更

- エイリアスを付与したイメージが表示されることを確認

  ```
  docker images
  ```

  <img src="images/docker-push-01.png" />

- docker push を使用してレジストリへプッシュ

  ```
  docker push yourregistry.azurecr.io/app:v1
  ```

  ※ youreregistry.azurecr.io を作成したコンテナー レジストリのログイン サーバーに変更

  <img src="images/docker-push-02.png" />

- Azure ポータルで作成したコンテナー レジストリの管理ブレードへアクセス

- 左側のメニューから「**リポジトリ**」を選択

- リポジトリ内のイメージを確認

  <img src="images/docker-push-02.png" />

<br />

## Exercise 3: Azure Container Apps の作成とイメージの展開

### Task 1: コンテナー アプリの作成

<br />

## Exercise 4: Azure Container Apps の設定

<br />