# Omise Java Client

## Requirements

* Java1.6以上で動作します。
* [Apache Commons Codec](http://commons.apache.org/proper/commons-codec/)が必要です。（1.10で動作確認）
* [GSON](https://code.google.com/p/google-gson/)も必要です。（2.2.4で動作確認）

上記の推奨バーション以下を利用した場合の問題についてはサポートしかねますので、ご了承ください。

## Installation


### Manually



## Usage

まず、秘密鍵と公開鍵を設定します。

```java
import main.java.co.omise.Omise;

Omise.setKeys("pkey_test_XXXXXXXXXXXXXXXXX", "skey_test_XXXXXXXXXXXXXXXXX");
```

customerのcreateのサンプル

```java
import main.java.co.omise.model.Customer;

Customer customer = Customer.create(new HashMap<String, Object>() {
		{put("email", "john.doe@example.com");}
		{put("description", "John Doe (id: 30)");}
		{put("card", "tokn_test_4xs9408a642a1htto8z");}
	});
```

customerのretrieve、update、destroyのサンプル

```php
import main.java.co.omise.model.Customer;
import main.java.co.omise.model.DeleteCustomer;

Customer customer = Customer.retrieve("cust_test_4xtrb759599jsxlhkrb");
customer.update(new HashMap<String, Object>() {
		{put("email", "john.smith@example.com");}
		{put("description", "Another description");}
	});
DeleteCustomer deleteCustomer = customer.destroy();
deleteCustomer.isDestroyed(); // => true
```

## Testing

ユニットテストを実行するためには、まず`test.java.co.omise.OmiseSettings.java`の秘密鍵と公開鍵を書き換えください。

```java
Omise.setKeys("pkey_test_XXXXXXXXXXXXXXXXX", "skey_test_XXXXXXXXXXXXXXXXX");
```

鍵を書き換えたあと、MavenでJUnitを実行させます。

```
mvn test
```

## License

LICENSE.txtを参照してください。
