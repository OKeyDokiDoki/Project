# AI Smart Mirror

AI 试衣镜项目脚手架，面向线下智能试衣镜、线上虚拟试穿和商家管理后台。

## 项目结构

```text
apps/
  web/       用户端试衣体验（React + Vite）
  admin/     商家管理端（预留）
services/
  api/       业务 API（FastAPI）
packages/
  contracts/ 前后端共享类型
docs/        架构与产品文档
```

## 本地启动

### 用户端

```bash
corepack enable
pnpm install
pnpm dev:web
```

访问 `http://localhost:5173`。

### API

```bash
cd services/api
python -m venv .venv
.venv/Scripts/activate
pip install -r requirements.txt
uvicorn app.main:app --reload
```

访问 `http://localhost:8000/docs` 查看接口文档。

## 当前范围

本版本只搭建基础脚手架，包含：

- 用户端可运行首页与核心功能入口
- FastAPI 健康检查及试穿、推荐、商品接口占位
- 前后端共享领域类型
- Docker Compose 与基础 CI
- 产品模块和后续开发阶段说明

人体测量、服装分割、姿态估计、虚拟试穿生成和尺码推荐目前均为接口占位。

