# Hello MQTT

<img src="doc/hello-mqtt.drawio.png" alt="hello-mqtt" style="width:400px" />

> [paho](https://eclipse.dev/paho/)
>
- `spring-integration-mqtt` ->  <https://github.com/eclipse/paho.mqtt.java>
- [paho-mqtt](<https://crates.io/crates/paho-mqtt>) <https://github.com/eclipse/paho.mqtt.rust>
- <https://github.com/eclipse/paho.mqtt.golang>

## 1 Start Broker

### [Eclipse Mosquitto](https://mosquitto.org/)

```sh
brew install mosquitto
```

```sh
sh start_mosquito.sh
```

### [EMQX](https://github.com/emqx/emqx)

```sh
brew install emqx
```

```sh
emqx start
```

```sh
$ emqx ctl status

Node 'emqx@127.0.0.1' 5.3.2 is started
```

```sh
emqx stop
```

[MQTTX Web](https://mqttx.app/web)

<http://localhost:18083/> The default user name and password are `admin` & `public`

## 2 Build Client

### hello-mqtt-java build

`build.sh`

```sh
mvn clean install -DskipTests
```

### hello-mqtt-rust build

```sh
cargo build
```

## 3 Run Client

### hello-mqtt-java run

#### service

`start_service.sh`

```sh
cd mqtt-service 
mvn spring-boot:run 
```

`look_up.sh`

```sh
curl http://localhost:8080/temps/stats
```

#### sensor

`start_sensor.sh`

```sh
cd mqtt-sensor 
mvn spring-boot:run 
```

### hello-mqtt-rust run

#### sub

```sh
cargo run --bin sub
```

#### pub

```sh
cargo run --bin pub
```

## Reference

- <https://github.com/gregwhitaker/springboot-mqtt-example>
- <https://dzone.com/refcardz/getting-started-with-mqtt>
- <https://www.emqx.com/en/blog/how-to-use-mqtt-in-rust>
