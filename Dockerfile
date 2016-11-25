FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/rocks.pho.btc.huobi-transaction-proxy.jar /rocks.pho.btc.huobi-transaction-proxy/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/rocks.pho.btc.huobi-transaction-proxy/app.jar"]
