# TrainingReport

## 1. 概要

Java Servlet/JSPとPostgreSQLを使用して構築された、研修報告書を管理するためのWebアプリケーションです。
報告書の登録、一覧表示、更新、削除といった基本的なCRUD機能を提供します。

### 1.1. 主な機能

*   **報告書の一覧表示**:
    登録されている研修報告書を一覧で表示します。
*   **報告書の登録**:
    新しい研修報告書を作成し、データベースに保存します。
*   **報告書の更新**:
    既存の報告書の内容を編集し、更新します。
*   **報告書の削除**:
    選択した報告書をデータベースから削除します。

## 2. 動作環境

*   Java
*   PostgreSQL
*   Apache Tomcat (または互換性のあるWebコンテナ)

## 3. セットアップ手順 (Windows)

### 3.1. PostgreSQLの準備

#### 3.1.1. PostgreSQLサーバーの起動・停止

*   **起動**
    ```bash
    pg_ctl -D "C:/tools/pgsql/data" -l "C:/tools/pgsql/logs/postgresql.log" start
    ```
*   **状態確認**
    ```bash
    pg_ctl -D "C:/tools/pgsql/data" status
    ```
*   **停止**
    ```bash
    pg_ctl -D "C:/tools/pgsql/data" stop
    ```

#### 3.1.2. データベースの作成

1.  `postgres`ユーザーでPostgreSQLに接続します。
    ```bash
    psql -U postgres -W
    ```

2.  データベース用のロール（ユーザー）を作成します。パスワードは任意に設定してください。
    ```sql
    CREATE ROLE custuser LOGIN PASSWORD '<your_password>';
    ```

3.  アプリケーション用のデータベースを作成します。
    ```sql
    CREATE DATABASE training_report ENCODING=UTF8 OWNER custuser;
    ```

#### 3.1.3. テーブルの作成

1.  プロジェクトのルートディレクトリに移動します。
    ```bash
    cd C:\Users\ishihama\workspace_java\workspace_myproject\TrainingReport
    ```

2.  環境変数 `PGPASSWORD` に、`custuser`用に設定したパスワードをセットします。
    ```bash
    set PGPASSWORD=<your_password>
    ```

3.  DDLファイルを実行してテーブルを作成します。
    ```bash
    psql -U custuser training_report < training_report.ddl
    psql -U custuser training_report < log_customer.ddl
    ```

### 3.2. アプリケーションのデプロイと確認

1.  `TrainingReport.war` ファイルをTomcat等のWebコンテナにデプロイします。
2.  ブラウザで以下のURLにアクセスして動作を確認します。
    *   `http://localhost:8080/TrainingReport/index.html`

## 4. データベース関連コマンド

### 4.1. データバックアップ

*   **スキーマのみバックアップ**
    ```bash
    pg_dump -U custuser -s training_report > training_report.ddl
    ```
*   **データのみバックアップ (INSERT文形式)**
    ```bash
    pg_dump -U custuser -a --column-inserts training_report > training_report.dump
    ```