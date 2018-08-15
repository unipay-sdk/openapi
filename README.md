# API接入以及代码示例结构说明 

## SDK环境

 * 开发语言：JAVA
 * 适用JDK版本：1.8
 
## 接入流程说明

# 集成接入
  
### 接入SDK步骤
  
#### 1. 根据文档说明快速接入实现快速开发。
  
```
  第一步：分别下载 SDK ；
  第二步：安装和部署 SDK ，使用 SDK 请求对象；
  注：SDK已经对加签验签逻辑进行封装，使用SDK可直接调用API。
```
  
#### 2.确定接口对应的类。
  
```
​    例如接口通信地址：/haipay/micropay
​    在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
​    如上接口名对应的类为：
​    MicropayRequest（请求类）
​    MicropayResponse（响应类）
```
  
#### 3.发起请求
  
```
  通过DefaultClient.execute()发起请求，通过响应类接收返回参。
```
  
#### 4.示例：
  
```
​        //初始化请求参数
     MicropayRequest micropayRequest = new MicropayRequest();
​        micropayRequest.setAppid("testAppid");
​        micropayRequest.setTotal_amount(1);
​        micropayRequest.setOut_trade_no("201806141041249892319734292415015172020264");
​        micropayRequest.setAuth_code("286211668181113823");
​        micropayRequest.setNonce_str("27821252326365524524423679958790");
​        //初始化初始化URL和appSecret
​        DefaultClient defaultClient = new DefaultClient("http://openapi.chuangjiangx.com "， "testAppsecret");
​        //发起请求
​        MicropayResponse micropayResponse = defaultClient.execute(micropayRequest);
```
     
### 根据接口文档文档接入。
  
```
 1.根据当前文档初始化发起请求的相关参数。
 2.根据发起请求的相关参数进行签名（详见签名）。
 3.发起请求。
 4.根据api对应返回值进行签名验证并进行相关逻辑处理。
```
     
#SDK示例

* > 小程序支付示例路径
```
​    ai.unipay.openapi.pay.AppletPayTest
```
* >  刷卡支付示例路径
```
​    ai.unipay.openapi.pay.MicropayTest
```
* > 预下单示例路径
```
​    ai.unipay.openapi.pay.QrcodepayTest
```
* > 查询示例路径
```
​    ai.unipay.openapi.pay.QueryTest
```
* > 同步订单示例路径
```
​    ai.unipay.openapi.pay.RefreshTest
```
* > 退款示例路径
```
​    ai.unipay.openapi.pay.RefundTest
```
* > 订单对账示例路径
```
​    ai.unipay.openapi.pay.BillOrderTest
```
* > 退款详情示例路径 
```
​    ai.unipay.openapi.pay.BillRefundTest
```

#SDK依赖包：
```
    <dependency>

​        <groupId>org.apache.httpcomponents</groupId>

​        <artifactId>httpmime</artifactId>

​        <version>4.5.1</version>

​    </dependency>

 

​    <dependency>

​        <groupId>com.alibaba</groupId>

​        <artifactId>fastjson</artifactId>

​        <version>1.2.27</version>

​    </dependency>

 

​    <dependency>

​        <groupId>org.projectlombok</groupId>

​        <artifactId>lombok</artifactId>

​        <version>1.16.10</version>

​        <scope>provided</scope>

​    </dependency>

 

​    <dependency>

​        <groupId>junit</groupId>

​        <artifactId>junit</artifactId>

​        <version>4.12</version>

​        <scope>test</scope>

​    </dependency>

 

​    <dependency>

​        <groupId>com.fasterxml.jackson.core</groupId>

​        <artifactId>jackson-annotations</artifactId>

​        <version>2.5.3</version>

​    </dependency>

 

​    <dependency>

​        <groupId>org.slf4j</groupId>

​        <artifactId>slf4j-log4j12</artifactId>

​        <version>1.7.2</version>

​    </dependency>
```