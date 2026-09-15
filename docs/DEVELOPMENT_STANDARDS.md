# AI 试衣镜开发标准

版本：1.0  
生效日期：2026-09-15  
适用范围：用户端、商家端、Spring Boot API、共享类型、基础设施与 AI 服务接入。

## 1. 基本原则

1. 业务代码按领域组织，不按技术类型堆放全部文件。
2. API、数据库和异步消息均先定义契约，再实现调用方。
3. 用户照片、视频、身体尺寸和肤色信息按敏感数据处理。
4. 核心流程必须可观测、可测试、可回滚。
5. 未接入的 AI 能力必须通过明确的接口或适配器占位，不在业务层伪造实现。
6. 优先保持简单；没有明确复用场景时，不提前创建通用框架。

## 2. 技术基线

| 范围 | 标准 |
| --- | --- |
| 用户端 | Vue 3、TypeScript、Vite、Vue Router |
| 状态管理 | 跨页面共享状态引入 Pinia；页面内部状态使用 Composition API |
| 后端 | Java 11、Spring Boot 2.7、Maven |
| 数据库 | PostgreSQL |
| 缓存与任务状态 | Redis |
| 包管理 | pnpm workspace |
| 自动化检查 | GitHub Actions：全仓类型检查、前端构建、后端测试 |

依赖升级必须单独提交或在 PR 中明确列出，并说明兼容性与安全影响。不得在业务开发中无说明地批量升级依赖。

## 3. 仓库与目录

```text
apps/
  web/                   Vue 用户端
  admin/                 Vue 商家管理端
services/
  api/                   Spring Boot 业务 API
packages/
  contracts/             前端共享 TypeScript 类型
docs/                    架构、标准和路线文档
.github/                 CI 与协作模板
```

前端新增代码按以下边界放置：

```text
src/
  api/                   HTTP 请求与 DTO 映射
  assets/                本地静态资源
  components/            可复用展示组件
  composables/           可复用组合式逻辑
  layouts/               页面布局
  router/                路由定义与守卫
  stores/                Pinia 状态
  styles/                全局样式与设计变量
  types/                 前端内部类型
  views/                 路由页面
```

后端新增代码按业务领域组织：

```text
com.okeydokidoki.smartmirror/
  common/                通用响应、异常和基础类型
  config/                框架配置
  identity/              登录与会员
  profile/               人体与尺码档案
  catalog/               商品与库存
  tryon/                 虚拟试穿
  recommendation/        搭配推荐
  order/                 购物车与订单
  device/                门店设备
  analytics/             数据分析
```

每个后端领域可包含 `controller`、`application`、`domain`、`infrastructure` 子包。小模块不强制拆层；当控制器开始承担业务判断时，必须将逻辑下沉到应用服务。

## 4. 前端标准

### 4.1 Vue 与 TypeScript

- 使用 `<script setup lang="ts">` 和 Composition API。
- 禁止新增 `any`；第三方数据应先定义 `unknown`，校验后再使用。
- Props、Emits、接口响应和状态必须有明确类型。
- 组件名使用 PascalCase，组合函数使用 `useXxx`，普通变量使用 camelCase。
- 页面组件以 `View.vue` 结尾，可复用组件使用业务含义命名。
- 单个组件只承担一个主要职责；超过约 300 行时应评估拆分。
- 禁止直接修改 Props，禁止在模板中放置复杂业务表达式。

### 4.2 状态与请求

- 页面临时状态保留在组件内。
- 多页面共享、需要缓存或需要持久化的状态放入 Pinia。
- 所有 HTTP 调用集中在 `src/api`，页面不得直接调用 `fetch`。
- API DTO 与页面展示模型分离，转换逻辑放在 API 模块或映射函数中。
- 请求必须处理加载、空数据、失败和重试状态。
- 取消离开页面后不再需要的图片上传、视频处理和轮询请求。

### 4.3 样式与体验

- 全局颜色、间距、字号和层级通过 CSS 变量管理。
- 默认使用 scoped 样式；跨页面基础样式放在 `src/styles`。
- 控件必须支持键盘操作，并提供可识别的焦点状态。
- 图标按钮必须设置可访问名称或 `title`。
- 文本和控件在 320px 到大屏试衣镜尺寸下不得重叠或溢出。
- 图片应声明尺寸或宽高比，避免加载时布局跳动。
- 摄像头、相册和麦克风权限必须在用户触发对应功能后申请。

## 5. 后端标准

### 5.1 Java 与 Spring

- 包名全小写，类名 PascalCase，方法和变量 camelCase，常量 UPPER_SNAKE_CASE。
- 使用构造器注入，不使用字段注入。
- Controller 只负责协议转换、参数校验与响应状态。
- 业务规则放在应用服务或领域对象中，数据访问放在 repository。
- 请求对象、响应对象和持久化实体不得共用同一个类。
- 所有外部输入使用 Bean Validation 校验。
- 时间统一使用 UTC 存储，接口使用 ISO 8601 格式。
- 金额使用 `BigDecimal`，不得使用 `double`。
- 禁止捕获异常后静默忽略，禁止向客户端返回堆栈信息。

### 5.2 包结构示例

```text
tryon/
  controller/
    TryOnController.java
  application/
    TryOnService.java
  domain/
    TryOnSession.java
    TryOnStatus.java
  infrastructure/
    TryOnRepository.java
  dto/
    CreateTryOnRequest.java
    TryOnSessionResponse.java
```

### 5.3 配置

- 默认配置放在 `application.yml`。
- 环境差异通过环境变量或 profile 注入。
- 密钥、Token、数据库密码和对象存储凭据不得提交到仓库。
- 新增环境变量时必须同步更新 `.env.example` 和 README。
- 生产环境不得使用通配 CORS、默认密码或调试日志级别。

## 6. API 标准

### 6.1 路径与方法

- 业务 API 前缀统一为 `/api/v1`。
- 路径使用小写复数名词和 kebab-case。
- 使用 HTTP 方法表达行为：
  - `GET /products`
  - `GET /products/{productId}`
  - `POST /try-on/sessions`
  - `PATCH /try-on/sessions/{sessionId}`
- 非业务健康检查可使用 `/health`，运维指标使用 `/actuator`。

### 6.2 响应

成功响应统一使用：

```json
{
  "success": true,
  "data": {},
  "message": null
}
```

错误响应统一使用：

```json
{
  "success": false,
  "data": null,
  "message": "请求参数不合法",
  "code": "VALIDATION_ERROR",
  "traceId": "..."
}
```

HTTP 状态码不得一律返回 `200`：

- `200`：查询或同步操作成功
- `201`：资源创建完成
- `202`：AI 试穿等异步任务已接受
- `204`：删除成功且无响应体
- `400`：参数格式错误
- `401`：未登录或凭证无效
- `403`：权限不足
- `404`：资源不存在
- `409`：资源状态冲突
- `422`：业务校验失败
- `429`：请求过于频繁
- `500`：未处理的服务端错误

列表接口统一支持 `page`、`size` 和明确的排序字段。默认 `size` 不超过 20，最大不超过 100。

### 6.3 异步 AI 任务

试穿、人体分析和视频生成使用任务资源表达：

1. 客户端创建任务，服务端返回 `202` 和任务 ID。
2. 状态仅允许 `pending`、`processing`、`ready`、`failed`、`cancelled`。
3. 客户端通过查询、SSE 或 WebSocket 获取进度。
4. 失败响应必须包含稳定错误码，不暴露模型内部信息。
5. 创建接口应支持幂等键，防止重复上传或重复扣费。

## 7. 数据标准

- 数据库表名和字段名使用 snake_case。
- 每张核心业务表包含 `id`、`created_at`、`updated_at`。
- 需要软删除时使用 `deleted_at`，不得用含义模糊的数字状态代替。
- 数据库结构变更必须使用版本化迁移工具，正式开发建议采用 Flyway。
- 外键、唯一约束和必要索引必须在数据库层声明。
- 日志、分析事件与业务主数据使用稳定 ID 关联，不记录原始人体图片内容。
- PostgreSQL 保存业务元数据，对象存储保存图片、视频、模型和生成结果。

## 8. 隐私与安全

- 人体照片、视频、尺寸、肤色、手机号和会员信息均为敏感数据。
- 采集前必须展示用途、保存期限和删除方式，并取得明确授权。
- 默认最小化采集；能够在端侧处理的数据不上传原始文件。
- 传输使用 HTTPS，存储使用加密，对象存储使用短期签名 URL。
- 日志不得记录完整手机号、访问令牌、原始图片 URL、身体尺寸全集或人脸特征。
- 用户必须能够查看并删除自己的试穿记录和人体档案。
- 后台接口必须进行身份认证、角色授权和门店数据隔离。
- 文件上传必须校验类型、大小、扩展名与实际内容，并进行恶意文件检测。
- 第三方 AI 服务接入前必须确认数据是否用于训练、保存地区和删除机制。

## 9. 测试标准

### 9.1 前端

- 工具函数、映射函数和复杂组合函数编写单元测试。
- 核心组件覆盖主要交互、加载、空状态和错误状态。
- 登录、上传、试穿、尺码推荐和下单链路最终应有端到端测试。
- 每次提交至少通过类型检查和生产构建。

### 9.2 后端

- 领域规则与应用服务使用单元测试。
- Controller 使用 MockMvc 测试状态码、校验和响应结构。
- Repository 使用集成测试验证查询与约束。
- 外部 AI、短信、微信和对象存储通过测试替身隔离。
- 新缺陷必须先补充可复现测试，再提交修复。

最低合并检查：

```bash
pnpm install --frozen-lockfile
pnpm typecheck
pnpm build:web
mvn -f services/api/pom.xml test
```

## 10. 日志与可观测性

- 每个请求生成或透传 `traceId`。
- 采用结构化日志，至少包含时间、级别、服务、环境、traceId 和事件名称。
- 记录任务状态变化，不记录原始敏感数据。
- 外部服务调用记录耗时、结果与稳定错误码。
- 生产环境必须监控请求错误率、延迟、AI 任务积压、任务失败率和存储用量。
- 健康检查区分存活状态与就绪状态。

## 11. Git 与评审

- `main` 始终保持可构建、可部署。
- 功能开发通过短生命周期分支和 Pull Request 完成。
- Commit 使用 Conventional Commits 格式。
- 一个 PR 聚焦一个目标；混合重构与业务功能时必须拆分。
- PR 必须说明变更内容、验证方式、风险和截图或接口示例。
- 涉及 API、数据库、环境变量、隐私策略或部署方式的变更必须同步更新文档。
- 不得提交生成目录、IDE 配置、密钥、用户数据或大型模型文件。

## 12. 完成定义

一项需求只有同时满足以下条件才算完成：

- 验收条件全部实现。
- 前后端契约一致，错误状态已处理。
- 必要测试已添加且本地与 CI 通过。
- 日志和监控点满足排障需要。
- 无密钥、敏感数据或调试代码进入仓库。
- 相关文档、环境变量和迁移脚本已更新。
- 移动端、桌面端或镜面大屏的目标尺寸已验证。
- 代码评审意见已解决并获得批准。

## 13. 例外管理

确需偏离本标准时，PR 中必须记录：

1. 偏离的具体条款。
2. 无法遵循的原因。
3. 风险与补偿措施。
4. 恢复标准的负责人和计划日期。

未记录的例外视为不符合开发标准。
