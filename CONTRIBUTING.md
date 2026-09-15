# 参与开发

所有代码变更必须遵循 [开发标准](docs/DEVELOPMENT_STANDARDS.md)。

## 开发流程

1. 从最新 `main` 创建功能分支。
2. 完成代码、测试和必要文档。
3. 在本地执行前后端检查。
4. 提交粒度清晰的 Git commit。
5. 发起 Pull Request，填写变更、验证和风险信息。
6. CI 通过并完成代码评审后合并。

## 本地检查

```bash
pnpm install --frozen-lockfile
pnpm typecheck
pnpm build:web
mvn -f services/api/pom.xml test
```

## 分支命名

```text
feature/<issue>-<short-name>
fix/<issue>-<short-name>
refactor/<short-name>
docs/<short-name>
chore/<short-name>
```

示例：`feature/42-try-on-session`。

## Commit 格式

```text
<type>(<scope>): <summary>
```

允许的 `type`：

- `feat`：新增功能
- `fix`：缺陷修复
- `refactor`：不改变行为的重构
- `test`：测试调整
- `docs`：文档调整
- `chore`：工具、构建或依赖调整
- `ci`：持续集成调整

示例：`feat(try-on): create virtual try-on session`。

## 合并要求

- 禁止直接向 `main` 提交业务功能。
- 禁止合并未通过 CI 的 Pull Request。
- 至少一名非作者开发者完成评审。
- 合并前必须解决所有阻塞性评审意见。
- 默认使用 Squash Merge，确保主分支历史清晰。
