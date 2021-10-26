# iothub-echo

> `mqtt服务端` 实现，类似 `aliyun iothub` 本地化部署

项目 | 地址 | 描述
----|-----|-----
iot-echo | [github.com/zx5435/iot-echo](https://github.com/zx5435/iot-echo) | 设备端(go) 采集数据，发数据
iothub-echo | [github.com/zx5435/iothub-echo](https://github.com/zx5435/iothub-echo) | 服务端(java) 收数据，处理数据

## Intro

只依赖redis，部署出iot服务端。同时兼容aliyun iot设备，在私有化场景，替换iothub的作用。

- `mqtt:1883` server broker
- `web:8080` 设备管理，状态监控，默认账号密码 `admin:admin`
- `db` 默认sqlite，文件级存储。可选mysql
- `redis`
  - `cache` last 状态缓存
  - `mq` 轻量级 x-stream in redis
- `tsdb:influxdb` 默认disable


## Deploy

```shell
# 方式 1 docker
# todo
docker run --restart=unless-stopped --name iothub -d -p 1883:1883 zx5435/iothub-echo:0.1.0

# 方式 2 docker-compose
wget https://raw.githubusercontent.com/zx5435/iothub-echo/main/__cicd__/docker-compose.yaml
docker-compose up -d

# 方式 3 k8s
# todo
```


## Todo

- [ ] rrpc
- [ ] tls
- [ ] quarkus


## Flow Rules

Iot device -> Mqtt broker -> Rules by topic

- 默认 `/{pk}/{deviceName}/user/update`
  - mq topic
    - `x:topic:all`
  - tsdb tag
    - `SNO = {deviceName}`
- 自定义
  - `TODO`
