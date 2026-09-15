# AI Smart Mirror

AI 试衣镜项目脚手架，面向线下智能试衣镜、线上虚拟试穿和商家管理后台。

## 项目结构

```text
apps/
  web/       用户端试衣体验（Vue 3 + Vite）
  admin/     商家管理端（预留）
services/
  api/       业务 API（Spring Boot）
packages/
  contracts/ 前后端共享类型
docs/        架构与产品文档
```

## 本地启动

### 用户端

```bash
pnpm install
pnpm dev:web
```

访问 `http://localhost:5173`。

### API

```bash
mvn -f services/api/pom.xml spring-boot:run
```

API 默认运行在 `http://localhost:8080`，健康检查地址为
`http://localhost:8080/health`。

## 环境要求

- Node.js 20+
- pnpm 10+
- Java 11+
- Maven 3.9+

## 当前范围

本版本只搭建基础脚手架，包含：

- 用户端可运行首页与核心功能入口
- Spring Boot 健康检查及试穿、推荐、商品接口占位
- 前后端共享领域类型
- Docker Compose 与基础 CI
- 产品模块和后续开发阶段说明

人体测量、服装分割、姿态估计、虚拟试穿生成和尺码推荐目前均为接口占位。

## 开发文档

- [开发标准](docs/DEVELOPMENT_STANDARDS.md)
- [参与开发](CONTRIBUTING.md)
- [架构说明](docs/ARCHITECTURE.md)
- [开发路线](docs/ROADMAP.md)
