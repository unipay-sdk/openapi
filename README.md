# API接入以及代码示例结构说明 

## SDK环境

 * 开发语言：JAVA
 * 适用JDK版本：1.8
 
## 接入流程说明

   1.获取appid、appsecret <br>
        商户：
            商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
        运营商、渠道商（线下提供）：
            功能扩展 –> 开放应用 ->新增应用
   2.集成SDK根据文档说明快速接入实现快速开发； <br>
        ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API； <br>
     	②.确定接口对应的类； <br>
     	 例如接口通信地址：/haipay/micropay <br>
         在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response） <br>
         如上接口名对应的类为： <br>
         MicropayRequest（请求类） <br>
         MicropayResponse（响应类） <br>
        ③.通过DefaultClient.execute()发起请求，通过响应类接收返回参； <br>
        ④.示例： <br>
        //初始化请求参数 <br>
         MicropayRequest micropayRequest = new MicropayRequest(); <br>
         micropayRequest.setAppid("testAppid"); <br>
         micropayRequest.setTotal_amount(1); <br>
         micropayRequest.setOut_trade_no("201806141041249892319734292415015172020264"); <br>
         micropayRequest.setAuth_code("286211668181113823"); <br>
         micropayRequest.setNonce_str("27821252326365524524423679958790"); <br>
        //初始化初始化URL和appSecret <br>
         DefaultClient defaultClient = new DefaultClient("http://openapi.xxxxxxx.com ", "testAppsecret"); <br>
        //发起请求 <br>
         MicropayResponse micropayResponse = defaultClient.execute(micropayRequest); <br>
         
## 结构说明

####### 请求相关 com.open.api.DefaultClient.java

 * `public DefaultClient(String serverUrl, String appSecret)`   
> 功能：构造方法 <br>
  输入：<br>
       serverUrl 非空，请求服务器域名地址 示例：http://openapi.x.com <br>
       appSecret 非空，商户平台上的appSecret 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 -> （服务商）审核通过后即可使用 <br> 
  输出：<br>
       调用API实例对象 <br>
 
 * `private <T extends GenerateResponse> T doPost(GenerateRequest<T> generateRequest)`
> 功能：执行请求调用 <br>
  输入:  <br>
        generateRequest： 接口请求对象 <br>
        `<T>`： 请求返回对象 <br>
  输出：<br>
        T：请求返回对象<br>
        
####### 签名相关工具类 com.open.api.utils.SignUtils.java  
     
 * `public static <T extends GenerateResponse> Map<String, String> getRequestMap(GenerateRequest<?> generateRequest)`
> 功能：按照参数名ASCII码从小到大排序（字典序） <br>
  输入： <br>
       generateRequest：接口请求对象 <br>
       `<T>`： 请求返回对象 <br>
  输出：<br>
       Map<String, String>：排序后的map集合<br>
       
 * ` public static String sign(Map<String, String> params, String appSecret)`
> 功能：拼接排序并签名 <br>
  输入： <br>
       params：排序后的map集合 <br>
       appSecret： 商户平台上的appSecret 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 -> （服务商）审核通过后即可使用 <br>
  输出：<br>
       String：签名<br>

####### 请求示例

1. com.open.api.pay.AppletPayTest 
    >小程序支付接口示例
2. com.open.api.pay.MicropayTest
    >刷卡支付接口示例
3. com.open.api.pay.QrcodepayTest
    >预下单接口示例
4. com.open.api.pay.QueryTest
    >查询接口示例
5. com.open.api.pay.RefreshTest
    >同步订单接口示例
6. com.open.api.pay.RefundTest
    >退款接口示例
7. com.open.api.pay.BillOrderTest
    >订单对账接口示例
8. com.open.api.pay.BillRefundTest
    >退款详情接口示例

####### 必引的包

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpmime</artifactId>
            <version>4.5.1</version>
        </dependency>
 
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.27</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.10</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.5.3</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.2</version>
        </dependency>

##版本更新
##### V1.0.0 版本内容更新 2018年06月06日
1. 初始版SDK,支持小程序支付接口，刷卡支付接口,预下单接口,查询接口,同步订单接口,退款接口,订单对账接口,退款详情接口