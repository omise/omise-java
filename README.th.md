# Omise Java Client

## ความต้องการขั้นต่ำ

* Java 1.6 หรือ สูงกว่า.
* [Apache Commons Codecs](http://commons.apache.org/proper/commons-codec/) 1.10 หรือ สูงกว่า.
* [GSON](https://code.google.com/p/google-gson/) version 2.2.4 หรือสูงกว่า.

Using older version of the requirements may work, but it is not officially supported.

## การติดตั้ง

กรณี ใช้ Maven, สามารถเพิ่ม Omise lib เข้าไปในไฟล์ pom.xml ตามด้านล่าง.

```
<dependency>
  <groupId>co.omise</groupId>
  <artifactId>omise-java</artifactId>
  <version>1.0.3</version>
</dependency>
```

กรณี ใช้ Gradle, สามารถเพิ่ม Omise lib เข้าไปในไฟล์ build.gradle ตามด้านล่าง.

```
compile 'co.omise:omise-java:1.0.3'
```

### ใช้ jar file

ถ้าท่านต้องการใช้งานในรูปแบบ .jar สามารถ build หรือ ดาวน์โหลด จาก maven center website (Search co.omise) :

```
git clone https://github.com/omise/omise-java
```

## วิธีการใช้งาน

ลำดับแรก, กรุณาเพิ่ม public key และ secret key:

```java
import co.omise.Omise;

Omise.setKeys("pkey_test_XXXXXXXXXXXXXXXXX", "skey_test_XXXXXXXXXXXXXXXXX");
```

ต้องการข้อมูลเพิ่มเติมการใช้งาน Omise API สามารดูได้ที่ [Omise Documentation](https://docs.omise.co/). 

ตัวอย่างการใช้งาน, การสร้างข้อมูล customer:

```java
import co.omise.model.Customer;

Customer customer = Customer.create(new HashMap<String, Object>() {
        {put("email", "john.doe@example.com");}
        {put("description", "John Doe (id: 30)");}
        {put("card", "tokn_test_4xs9408a642a1htto8z");}
    });
```

การ retrieve ข้อมูล customer, แก้ไข หรือ ลบข้อมูล:

```java
import co.omise.model.Customer;
import co.omise.model.DeleteCustomer;

Customer customer = Customer.retrieve("cust_test_4xtrb759599jsxlhkrb");
customer.update(new HashMap<String, Object>() {
        {put("email", "john.smith@example.com");}
        {put("description", "Another description");}
    });
DeleteCustomer deleteCustomer = customer.destroy();
deleteCustomer.isDestroyed(); // => true
```

## ทดสอบ

เพื่อการรัน automated test, ลำดับแรก กรุณาเพิ่มข้อมูล public key และ secret key ใน `test.java.co.omise.OmiseSetting.java`:

```java
Omise.setKeys("pkey_test_XXXXXXXXXXXXXXXXX", "skey_test_XXXXXXXXXXXXXXXXX");
```

หลังจากนั้น run JUnit with Maven:

```
mvn test
```

## License

See LICENSE.txt
