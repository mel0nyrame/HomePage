# Changelog

> 本文档由 CI 自动生成，记录所有已合并到 main 分支的变更。请勿手动编辑。


---

## 2026-05-21 — 🎉 init

**`59b73e6`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| *无文件变更* | | |

### 改动位置

*初始提交，全部新增。*

---

## 2026-05-21 — 📃 docs:

**`4176800`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `项目文档.md` | 11 | 8 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-21 — 🌈 style(.idea .gitignore): idea配置

**`b988dd5`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.gitignore` | 13 | 0 |
| `.idea/WebSite.iml` | 9 | 0 |
| `.idea/compiler.xml` | 21 | 0 |
| `.idea/encodings.xml` | 9 | 0 |
| `.idea/inspectionProfiles/Project_Default.xml` | 8 | 0 |
| `.idea/jarRepositories.xml` | 20 | 0 |
| `.idea/misc.xml` | 14 | 0 |
| `.idea/vcs.xml` | 7 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-21 — ✨ feat(homepage-common): 通用类

**`1049c77`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-common/pom.xml` | 57 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/BusinessException.java` | 30 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 34 | 0 |
| `homepage-common/src/main/java/com/homepage/common/web/Response.java` | 63 | 0 |
| `homepage-common/src/main/java/com/homepage/common/web/ResponseCode.java` | 146 | 0 |
| `pom.xml` | 91 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — 📃 docs(项目文档): 修改结构，添加登陆逻辑

**`9bf3184`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `项目文档.md` | 30 | 2 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — 🌈 style(.idea): idea配置好

**`21d0ddd`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 2 | 0 |
| `.idea/encodings.xml` | 2 | 0 |
| `.idea/intellij-javadocs-4.0.1.xml` | 204 | 0 |
| `.idea/misc.xml` | 1 | 1 |
| `.idea/modules.xml` | 8 | 0 |
| `homepage-user.iml` | 8 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — 🧪 test(homepage-common homepage-user): 技术验证

**`5c7ae21`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-common/src/test/java/com/homepage/JwtTest.java` | 40 | 0 |
| `homepage-user/src/test/java/com/homepage/SpringSecurityTest.java` | 31 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — ✨ feat(homepage-common homepage-user): 实现token接口

**`f01ca95`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-common/pom.xml` | 7 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/JwtConstants.java` | 12 | 0 |
| `homepage-common/src/main/java/com/homepage/common/util/JwtUtil.java` | 53 | 0 |
| `homepage-user/pom.xml` | 84 | 0 |
| `homepage-user/src/main/java/com/homepage/user/HomepageUserApplication.java` | 20 | 0 |
| `homepage-user/src/main/java/com/homepage/user/config/SecurityConfig.java` | 46 | 0 |
| `homepage-user/src/main/java/com/homepage/user/controller/UserController.java` | 28 | 0 |
| `homepage-user/src/main/java/com/homepage/user/filter/JwtAuthenticationFilter.java` | 81 | 0 |
| `homepage-user/src/main/java/com/homepage/user/model/dto/UserDTO.java` | 16 | 0 |
| `homepage-user/src/main/java/com/homepage/user/service/UserService.java` | 14 | 0 |
| `homepage-user/src/main/java/com/homepage/user/service/impl/UserServiceImpl.java` | 27 | 0 |
| `homepage-user/src/main/resources/application.yml` | 13 | 0 |
| `pom.xml` | 16 | 3 |
| `sql/init.sql` | 0 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — 🦄 refactor(homepage-auth): 更改模块名，以后尽量避免毁灭性更改

**`75c65b3`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 2 | 1 |
| `.idea/dataSources/data_sources_history.xml` | 55 | 0 |
| `.idea/encodings.xml` | 2 | 2 |
| `.idea/modules.xml` | 1 | 1 |
| `homepage-auth.iml` | 8 | 0 |
| `homepage-auth/pom.xml` | 84 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/HomepageAuthApplication.java` | 20 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 28 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/filter/JwtAuthenticationFilter.java` | 81 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/UserDTO.java` | 16 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 14 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 27 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 13 | 0 |
| `homepage-auth/src/test/java/com/homepage/SpringSecurityTest.java` | 31 | 0 |
| `homepage-user.iml` | 0 | 8 |
| `homepage-user/pom.xml` | 0 | 84 |
| `homepage-user/src/main/java/com/homepage/user/HomepageUserApplication.java` | 0 | 20 |
| `homepage-user/src/main/java/com/homepage/user/config/SecurityConfig.java` | 0 | 46 |
| `homepage-user/src/main/java/com/homepage/user/controller/UserController.java` | 0 | 28 |
| `homepage-user/src/main/java/com/homepage/user/filter/JwtAuthenticationFilter.java` | 0 | 81 |
| `homepage-user/src/main/java/com/homepage/user/model/dto/UserDTO.java` | 0 | 16 |
| `homepage-user/src/main/java/com/homepage/user/service/UserService.java` | 0 | 14 |
| `homepage-user/src/main/java/com/homepage/user/service/impl/UserServiceImpl.java` | 0 | 27 |
| `homepage-user/src/main/resources/application.yml` | 0 | 13 |
| `homepage-user/src/test/java/com/homepage/SpringSecurityTest.java` | 0 | 31 |
| `pom.xml` | 1 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-23 — 📃 docs(common,auth): 补充注释

**`236f894`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 5 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/JwtConstants.java` | 7 | 0 |

### 改动位置

- public class UserServiceImpl implements UserService {

---

## 2026-05-24 — 🌈 style(.idea): 项目配置

**`c99ecec`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.gitignore` | 3 | 0 |
| `.idea/WebSite.iml` | 0 | 9 |
| `.idea/compiler.xml` | 0 | 7 |
| `.idea/dataSources/data_sources_history.xml` | 0 | 55 |
| `.idea/intellij-javadocs-4.0.1.xml` | 0 | 204 |
| `.idea/misc.xml` | 1 | 3 |
| `.idea/modules.xml` | 0 | 8 |
| `.idea/vcs.xml` | 0 | 1 |
| `homepage-auth.iml` | 0 | 8 |
| `homepage-auth/pom.xml` | 6 | 0 |
| `homepage-common/pom.xml` | 15 | 3 |
| `pom.xml` | 5 | 5 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-24 — 🦄 refactor(common,auth): 更换jwt

**`b3e0f2e`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 7 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 7 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/filter/JwtAuthenticationFilter.java` | 1 | 6 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 7 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 4 | 4 |
| `homepage-common/src/main/java/com/homepage/common/config/JwtConfig.java` | 43 | 0 |
| `homepage-common/src/main/java/com/homepage/common/util/JwtUtil.java` | 30 | 26 |
| `homepage-common/src/test/java/com/homepage/JwtTest.java` | 3 | 23 |

### 改动位置

- public class JwtAuthenticationFilter extends OncePerRequestFilter {
- public class SecurityConfig {
- public class UserController {
- public class UserServiceImpl implements UserService {

---

## 2026-05-24 — 🌈 style(.idea): idea配置

**`89af1ae`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 1 | 0 |
| `pom.xml` | 10 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-24 — 🔧 build(auth,common): 项目依赖

**`81638c9`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/pom.xml` | 8 | 0 |
| `homepage-common/pom.xml` | 1 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-24 — ✨ feat(auth,common): 用户登陆注册功能

**`d35b7ce`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 26 | 16 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 12 | 4 |
| `homepage-auth/src/main/java/com/homepage/auth/filter/JwtAuthenticationFilter.java` | 0 | 76 |
| `homepage-auth/src/main/java/com/homepage/auth/mapper/UserMapper.java` | 47 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/UserDTO.java` | 13 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/UserEntity.java` | 111 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 15 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 78 | 7 |
| `homepage-auth/src/main/resources/application.yml` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/config/JwtConfig.java` | 6 | 8 |
| `homepage-common/src/main/java/com/homepage/common/util/JwtUtil.java` | 21 | 4 |
| `sql/init.sql` | 17 | 0 |

### 改动位置

- public class JwtConfig {
- public class JwtUtil {
- public class UserController {

---

## 2026-05-24 — 📃 docs: 项目文档

**`5a29541`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `项目文档.md` | 133 | 21 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-25 — 🌈 style(.idea): idea配置

**`a55d91a`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/intellij-javadocs-4.0.1.xml` | 204 | 0 |
| `homepage-auth/pom.xml` | 6 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-25 — ✨ feat(auth): 处理各种各样的错误配置

**`edf8a33`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 5 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 3 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/UserDTO.java` | 4 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 3 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 20 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAccessDeniedHandler.java` | 30 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAuthenticationEntryPoint.java` | 33 | 0 |

### 改动位置

- public class GlobalExceptionHandler {
- public class SecurityConfig {
- public class UserController {
- public class UserDTO {

---

## 2026-05-25 — 🐞 fix(auth): 在用户表中加入email字段

**`abc7029`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.gitignore` | 7 | 3 |
| `.idea/misc.xml` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 6 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/mapper/UserMapper.java` | 22 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/LoginDTO.java` | 26 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/RegisterDTO.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/UserDTO.java` | 0 | 32 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/UserEntity.java` | 8 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 3 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 25 | 16 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 16 | 0 |
| `sql/init.sql` | 1 | 0 |

### 改动位置

- CREATE TABLE homepage_user
- public class GlobalExceptionHandler {
- public class UserController {
- public class UserEntity implements UserDetails {
- public class UserServiceImpl implements UserService, UserDetailsService {
- public interface UserMapper{
- public interface UserService {

---

## 2026-05-25 — ✨  feat(auth): 实现管理员登录认证功能，支持角色权限控制

**`4ee6084`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 34 | 7 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/AdminController.java` | 43 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/initializer/DataInitializer.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/mapper/AdminMapper.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminLoginDTO.java` | 26 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminRegisterDTO.java` | 30 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/AdminEntity.java` | 108 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/UserEntity.java` | 2 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/service/AdminService.java` | 29 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 2 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/AdminServiceImpl.java` | 102 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 5 | 3 |
| `homepage-auth/src/main/resources/application.yml` | 6 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 1 | 1 |
| `sql/init.sql` | 14 | 2 |

### 改动位置

- CREATE TABLE homepage_user
- public class GlobalExceptionHandler {
- public class SecurityConfig {
- public class UserEntity implements UserDetails {
- public class UserServiceImpl implements UserService, UserDetailsService {

---

## 2026-05-26 — 🐞 fix(auth): 为userAuthenticationManager添加@Primary避免多AuthenticationManager冲突

**`865388d`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 5 | 4 |

### 改动位置

- public class SecurityConfig {

---

## 2026-05-26 — 🐞 fix(auth): 修复userAuthenticationManager自引用导致StackOverflow

**`af25750`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 6 | 2 |

### 改动位置

- public class SecurityConfig {

---

## 2026-05-26 — 🐞 fix(common): 修复访问无权限接口时候的返回值

**`d86aadb`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 0 | 1 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 8 | 0 |

### 改动位置

- public class GlobalExceptionHandler {

---

## 2026-05-26 — 📃 docs(项目文档): 修改项目文档

**`d640154`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `项目文档.md` | 247 | 55 |

### 改动位置

- BearerTokenAuthenticationFilter
- UserController.register()
- UserServiceImpl.login()
- UserServiceImpl.register()

---

## 2026-05-26 — 🦄 refactor(project): 将auth模块拆分为auth/admin/user三个独立模块

**`8f15a16`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 3 | 0 |
| `.idea/encodings.xml` | 4 | 0 |
| `homepage-admin/pom.xml` | 95 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/config/AdminAuthConfig.java` | 27 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/controller/AdminController.java` | 42 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/initializer/DataInitializer.java` | 45 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/mapper/AdminMapper.java` | 45 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/model/entity/AdminEntity.java` | 108 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/service/AdminService.java` | 29 | 0 |
| `homepage-admin/src/main/java/com/homepage/admin/service/impl/AdminServiceImpl.java` | 100 | 0 |
| `homepage-admin/src/main/resources/application.yml` | 5 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 1 | 24 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/AdminController.java` | 0 | 43 |
| `homepage-auth/src/main/java/com/homepage/auth/controller/UserController.java` | 0 | 40 |
| `homepage-auth/src/main/java/com/homepage/auth/initializer/DataInitializer.java` | 0 | 45 |
| `homepage-auth/src/main/java/com/homepage/auth/mapper/AdminMapper.java` | 0 | 45 |
| `homepage-auth/src/main/java/com/homepage/auth/mapper/UserMapper.java` | 0 | 66 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminLoginDTO.java` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminRegisterDTO.java` | 1 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/LoginDTO.java` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/AdminEntity.java` | 0 | 108 |
| `homepage-auth/src/main/java/com/homepage/auth/model/entity/UserEntity.java` | 0 | 119 |
| `homepage-auth/src/main/java/com/homepage/auth/service/AdminService.java` | 0 | 29 |
| `homepage-auth/src/main/java/com/homepage/auth/service/UserService.java` | 0 | 30 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/AdminServiceImpl.java` | 0 | 102 |
| `homepage-auth/src/main/java/com/homepage/auth/service/impl/UserServiceImpl.java` | 0 | 114 |
| `homepage-auth/src/main/resources/application.yml` | 0 | 6 |
| `homepage-user/pom.xml` | 95 | 0 |
| `homepage-user/src/main/java/com/homepage/user/config/UserAuthConfig.java` | 29 | 0 |
| `homepage-user/src/main/java/com/homepage/user/controller/UserController.java` | 40 | 0 |
| `homepage-user/src/main/java/com/homepage/user/mapper/UserMapper.java` | 66 | 0 |
| `homepage-user/src/main/java/com/homepage/user/model/entity/UserEntity.java` | 119 | 0 |
| `homepage-user/src/main/java/com/homepage/user/service/UserService.java` | 30 | 0 |
| `homepage-user/src/main/java/com/homepage/user/service/impl/UserServiceImpl.java` | 114 | 0 |
| `pom.xml` | 2 | 0 |
| `项目文档.md` | 24 | 9 |

### 改动位置

- public class SecurityConfig {

---

## 2026-05-26 — 🦄 refactor(project): 合并admin/user模块到auth，Entity移至common，简化模块依赖

**`e017dc7`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 13 | 3 |
| `homepage-admin/pom.xml` | 0 | 95 |
| `homepage-admin/src/main/java/com/homepage/admin/config/AdminAuthConfig.java` | 0 | 27 |
| `homepage-admin/src/main/java/com/homepage/admin/controller/AdminController.java` | 0 | 42 |
| `homepage-admin/src/main/java/com/homepage/admin/initializer/DataInitializer.java` | 0 | 45 |
| `homepage-admin/src/main/java/com/homepage/admin/mapper/AdminMapper.java` | 0 | 45 |
| `homepage-admin/src/main/java/com/homepage/admin/model/entity/AdminEntity.java` | 0 | 108 |
| `homepage-admin/src/main/java/com/homepage/admin/service/AdminService.java` | 0 | 29 |
| `homepage-admin/src/main/java/com/homepage/admin/service/impl/AdminServiceImpl.java` | 0 | 100 |
| `homepage-admin/src/main/resources/application.yml` | 0 | 5 |
| `homepage-auth/pom.xml` | 7 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/HomepageAuthApplication.java` | 4 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/controller/AdminController.java` | 42 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/initializer/DataInitializer.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/mapper/AdminMapper.java` | 45 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/AdminService.java` | 28 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 99 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 24 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminLoginDTO.java` | 0 | 26 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/AdminRegisterDTO.java` | 0 | 29 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/LoginDTO.java` | 0 | 26 |
| `homepage-auth/src/main/java/com/homepage/auth/model/dto/RegisterDTO.java` | 0 | 45 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 40 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/mapper/UserMapper.java` | 66 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 30 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 114 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 8 | 2 |
| `homepage-common/pom.xml` | 6 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminLoginDTO.java` | 26 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 29 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/LoginDTO.java` | 26 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 45 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/AdminEntity.java` | 108 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/UserEntity.java` | 119 | 0 |
| `homepage-user/pom.xml` | 0 | 95 |
| `homepage-user/src/main/java/com/homepage/user/config/UserAuthConfig.java` | 0 | 29 |
| `homepage-user/src/main/java/com/homepage/user/controller/UserController.java` | 0 | 40 |
| `homepage-user/src/main/java/com/homepage/user/mapper/UserMapper.java` | 0 | 66 |
| `homepage-user/src/main/java/com/homepage/user/model/entity/UserEntity.java` | 0 | 119 |
| `homepage-user/src/main/java/com/homepage/user/service/UserService.java` | 0 | 30 |
| `homepage-user/src/main/java/com/homepage/user/service/impl/UserServiceImpl.java` | 0 | 114 |
| `pom.xml` | 0 | 2 |

### 改动位置

- public class SecurityConfig {

---

## 2026-05-26 — ✨ feat(auth,common): 引入文档依赖，给每个接口添加文档信息

**`203506b`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 1 | 10 |
| `homepage-auth/pom.xml` | 6 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/controller/AdminController.java` | 8 | 7 |
| `homepage-auth/src/main/java/com/homepage/auth/config/OpenApiConfig.java` | 27 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 11 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 7 | 8 |
| `homepage-auth/src/main/resources/application.yml` | 8 | 0 |
| `homepage-common/pom.xml` | 6 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminLoginDTO.java` | 4 | 12 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 8 | 14 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/LoginDTO.java` | 4 | 12 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 10 | 21 |
| `homepage-common/src/main/java/com/homepage/common/web/Response.java` | 7 | 6 |
| `pom.xml` | 9 | 0 |

### 改动位置

- public class AdminController {
- public class Response<T> implements Serializable {
- public class SecurityConfig {
- public class UserController {

---

## 2026-05-27 — 🦄 refactor(auth,common): mybatis迁至mybatisplus

**`fbc3646`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/inspectionProfiles/Project_Default.xml` | 27 | 0 |
| `homepage-auth/pom.xml` | 4 | 4 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/initializer/DataInitializer.java` | 2 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/mapper/AdminMapper.java` | 2 | 32 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/AdminService.java` | 3 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 6 | 20 |
| `homepage-auth/src/main/java/com/homepage/auth/user/mapper/UserMapper.java` | 2 | 53 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 3 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 12 | 28 |
| `homepage-common/pom.xml` | 6 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/AdminEntity.java` | 5 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/UserEntity.java` | 6 | 0 |
| `pom.xml` | 10 | 10 |
| `sql/init.sql` | 2 | 2 |

### 改动位置

- CREATE TABLE homepage_admin
- CREATE TABLE homepage_user
- public class AdminServiceImpl implements AdminService {
- public class DataInitializer implements CommandLineRunner {
- public class UserServiceImpl implements UserService {

---

## 2026-05-27 — 🦄 refactor(auth,common): 实体与UserDetails解耦，引入时间自动填充，优化表结构并集成Actuator

**`7ff5437`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.gitignore` | 3 | 6 |
| `.idea/jarRepositories.xml` | 5 | 0 |
| `homepage-auth/pom.xml` | 6 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 2 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 2 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 2 | 1 |
| `homepage-common/pom.xml` | 7 | 0 |
| `homepage-common/src/main/java/com/homepage/common/config/MyBatisPlusConfig.java` | 28 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/AdminEntity.java` | 14 | 71 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/UserEntity.java` | 14 | 74 |
| `homepage-common/src/main/java/com/homepage/common/model/security/AdminUserDetails.java` | 86 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/security/HomepageUserDetails.java` | 86 | 0 |
| `sql/init.sql` | 30 | 14 |

### 改动位置

- USE homepage;
- public class AdminEntity implements UserDetails {
- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class SecurityConfig {
- public class UserEntity implements UserDetails {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme

---

## 2026-05-27 — 📃 docs(项目文档.md): 修改项目文档

**`8257425`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `项目文档.md` | 86 | 60 |

### 改动位置

- AdminController.login()
- AdminController.register()
- BearerTokenAuthenticationFilter
- UserController.login()
- UserController.register()
- homepage-main (父 POM)
- public class SecurityConfig {

---

## 2026-05-27 — fix(init):修改了初始化建表语句

**`1d10429`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/inspectionProfiles/Project_Default.xml` | 1 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/AdminEntity.java` | 1 | 1 |
| `sql/init.sql` | 5 | 2 |

### 改动位置

- CREATE TABLE homepage_admin
- CREATE TABLE homepage_user
- USE homepage;

---

## 2026-05-27 — fix():修复了管理员nickname字段

**`e80210b`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/admin/initializer/DataInitializer.java` | 4 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 1 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 1 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 5 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/entity/AdminEntity.java` | 6 | 1 |
| `sql/init.sql` | 2 | 2 |

### 改动位置

- CREATE TABLE homepage_admin
- CREATE TABLE homepage_user
- public class  AdminEntity {
- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class DataInitializer implements CommandLineRunner {

---

## 2026-05-27 — docs(script):新增了changelog.md的更新脚本

**`c476796`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/workflows/changelog.yml` | 33 | 0 |
| `CHANGELOG.md` | 809 | 0 |
| `scripts/gen-changelog.sh` | 97 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-27 — docs: 更新 CHANGELOG.md [skip ci]

**`756729c`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 19 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-27 — docs():新增了定时启动脚本的功能

**`2b87df2`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/scripts/com.homepage.sync-changelog.plist` | 26 | 0 |
| `.github/scripts/gen-changelog.sh` | 97 | 0 |
| `.github/scripts/install-sync.sh` | 66 | 0 |
| `.github/scripts/sync-changelog.bat` | 2 | 0 |
| `.github/scripts/sync-changelog.sh` | 18 | 0 |
| `.github/workflows/changelog.yml` | 1 | 1 |
| `scripts/gen-changelog.sh` | 0 | 97 |
| `项目文档.md` | 11 | 3 |

### 改动位置

- AdminServiceImpl.login()
- Service 层 `loadUserByUsername()` 从数据库查出实体后，包装为对应
- application.yml 中配置了初始管理员凭据：
- homepage-main (父 POM)

---

## 2026-05-27 — docs():新增了定时启动脚本的功能

**`d2e6ee9`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/scripts/com.homepage.sync-changelog.plist` | 26 | 0 |
| `.github/scripts/gen-changelog.sh` | 97 | 0 |
| `.github/scripts/install-sync.sh` | 66 | 0 |
| `.github/scripts/sync-changelog.bat` | 2 | 0 |
| `.github/scripts/sync-changelog.sh` | 18 | 0 |
| `.github/workflows/changelog.yml` | 1 | 1 |
| `scripts/gen-changelog.sh` | 0 | 97 |
| `项目文档.md` | 11 | 3 |

### 改动位置

- AdminServiceImpl.login()
- Service 层 `loadUserByUsername()` 从数据库查出实体后，包装为对应
- application.yml 中配置了初始管理员凭据：
- homepage-main (父 POM)

---

## 2026-05-27 — Merge branch 'dev'

**`b4856b9`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — ✨ feat(auth): 获取验证码图片

**`88d61a7`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/pom.xml` | 12 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/controller/CaptchaController.java` | 30 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/CaptchaService.java` | 20 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/impl/CaptchaServiceImpl.java` | 75 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 1 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 8 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/RedisConstants.java` | 11 | 0 |
| `pom.xml` | 18 | 0 |

### 改动位置

- public class SecurityConfig {

---

## 2026-05-28 — fix(.github):修复了自动脚本，实现静默执行

**`a42b00d`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.devcontainer/devcontainer.json` | 4 | 0 |
| `.github/scripts/com.homepage.sync-changelog.plist` | 1 | 1 |
| `.github/scripts/gen-changelog.sh` | 1 | 1 |
| `.github/scripts/install-sync.sh` | 42 | 8 |
| `.github/scripts/sync-changelog.bat` | 0 | 2 |
| `.github/scripts/sync-changelog.sh` | 1 | 1 |
| `.github/workflows/qodana_code_quality.yml` | 40 | 0 |
| `qodana.yaml` | 49 | 0 |

### 改动位置

- case "$(uname -s)" in

---

## 2026-05-28 — Merge branch 'dev'

**`ca28c72`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`ef1eed7`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 108 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — fix():文件位置变动

**`8f862b6`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/qodana.yaml` | 49 | 0 |
| `.github/workflows/qodana_code_quality.yml` | 1 | 0 |
| `qodana.yaml` | 0 | 49 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — Merge branch 'dev'

**`c31556a`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`24c7c96`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`382f2e8`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 51 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — ✨ feat(auth,common): 完善验证码功能

**`c865127`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/admin/controller/AdminController.java` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/AdminService.java` | 3 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 21 | 6 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/controller/CaptchaController.java` | 2 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/CaptchaService.java` | 9 | 4 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/impl/CaptchaServiceImpl.java` | 3 | 6 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 3 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 22 | 8 |
| `homepage-common/pom.xml` | 6 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/RedisConstants.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 1 | 2 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminLoginDTO.java` | 8 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 8 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/LoginDTO.java` | 8 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 8 | 0 |
| `homepage-common/src/main/java/com/homepage/common/util/RedisUtil.java` | 53 | 0 |
| `homepage-common/src/main/java/com/homepage/common/web/Response.java` | 4 | 4 |

### 改动位置

- public class AdminController {
- public class AdminLoginDTO {
- public class AdminRegisterDTO {
- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class CaptchaController {
- public class CaptchaServiceImpl implements CaptchaService {
- public class GlobalExceptionHandler {
- public class LoginDTO {
- public class RegisterDTO {
- public class Response<T> implements Serializable {
- public class UserController {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme
- public interface AdminService extends UserDetailsService, IService<AdminEntity>
- public interface UserService extends UserDetailsService, IService<UserEntity> {

---

## 2026-05-28 — 🔒 fix(auth,common): 修复验证码安全漏洞与代码质量问题

**`a236c72`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 2 | 8 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/impl/CaptchaServiceImpl.java` | 2 | 7 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 2 | 12 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 7 | 2 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminLoginDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/CaptchaAware.java` | 12 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/LoginDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/util/RedisUtil.java` | 27 | 16 |

### 改动位置

- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class CaptchaServiceImpl implements CaptchaService {
- public class RedisUtil {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme

---

## 2026-05-28 — Merge remote-tracking branch 'origin/main'

**`8077475`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`5496bbb`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 122 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — ⏺ 🔧 ci: 修复 Qodana workflow 参数错误与版本号

**`7926733`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/workflows/qodana_code_quality.yml` | 2 | 2 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`b021338`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 33 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — 🔧 ci: 修复 Qodana 扫描参数解析错误

**`24b7398`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/workflows/qodana_code_quality.yml` | 1 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`231ff2a`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 33 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — ⏺ 🔧 ci: 将 Qodana linter 版本降级为 2025.1 以兼容当前 CLI

**`ec1838a`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/qodana.yaml` | 1 | 1 |

### 改动位置

- projectJDK: "21" #(Applied in CI/CD pipeline)

---

## 2026-05-28 — Merge remote-tracking branch 'origin/main'

**`8af7751`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`0a3b1d9`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 41 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — fix(qodana):更新了qodana.yaml的位置

**`a5dadb1`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/qodana.yaml` | 0 | 49 |
| `.github/workflows/qodana_code_quality.yml` | 0 | 1 |
| `qodana.yaml` | 49 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`85c0af9`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 35 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — 🐞 fix(auth,common): 修复字段和获取不到验证码的bug

**`2d98a18`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminLoginDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/CaptchaAware.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/LoginDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/util/RedisUtil.java` | 3 | 3 |

### 改动位置

- public class AdminLoginDTO implements CaptchaAware {
- public class AdminRegisterDTO implements CaptchaAware {
- public class LoginDTO implements CaptchaAware {
- public class RedisUtil {
- public class RegisterDTO implements CaptchaAware {

---

## 2026-05-28 — Merge branch 'dev-mel0ny'

**`a304da9`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`5a27971`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 50 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — feat():添加了forum模块

**`bb5ccb4`** | klei

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/scripts/sync-changelog.sh` | 1 | 0 |
| `.idea/compiler.xml` | 10 | 0 |
| `.idea/encodings.xml` | 2 | 0 |
| `homepage-forum/pom.xml` | 120 | 0 |
| `homepage-forum/src/main/java/com/homepage/forum/HomepageForumApplication.java` | 14 | 0 |
| `homepage-forum/src/main/resources/application.yml` | 17 | 0 |
| `pom.xml` | 1 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — Merge branch 'dev'

**`d2bbb2d`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`165a8c9`** | klei

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`de5e9f4`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 55 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-28 — feat(auth,common): 用户注册增加邮箱验证码验证功能

**`66a87d0`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/admin/controller/AdminController.java` | 2 | 4 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 5 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/config/OpenApiConfig.java` | 0 | 27 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 9 | 4 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 10 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 42 | 1 |
| `homepage-auth/src/main/resources/application.yml` | 7 | 0 |
| `homepage-auth/src/main/resources/templates/EmailVerificationCode.html` | 166 | 0 |
| `homepage-auth/src/test/java/com/homepage/HutoolTest.java` | 27 | 0 |
| `homepage-common/pom.xml` | 18 | 0 |
| `homepage-common/src/main/java/com/homepage/common/config/OpenApiConfig.java` | 27 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/RedisConstants.java` | 13 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/EmailDTO.java` | 15 | 0 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 73 | 0 |

### 改动位置

- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class UserController {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme
- public interface UserService extends UserDetailsService, IService<UserEntity> {

---

## 2026-05-29 — ♻️ refactor(auth,common): 修复时序攻击、NPE风险，消除代码重复

**`e2a87f5`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/HomepageAuthApplication.java` | 2 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/controller/AdminController.java` | 4 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/AdminService.java` | 1 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 0 | 12 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminUserDetailsServiceImpl.java` | 34 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 5 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 6 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 1 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserDetailsServiceImpl.java` | 37 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 6 | 23 |
| `homepage-common/src/main/java/com/homepage/common/constant/JwtConstants.java` | 4 | 0 |
| `homepage-common/src/main/java/com/homepage/common/constant/RedisConstants.java` | 4 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/EmailDTO.java` | 12 | 0 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 41 | 17 |
| `homepage-common/src/main/java/com/homepage/common/util/RedisUtil.java` | 25 | 1 |

### 改动位置

- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class MailUtil {
- public class RedisUtil {
- public class SecurityConfig {
- public class UserController {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme

---

## 2026-05-29 — ✨  feat(auth): 新增重发验证码接口，修复邮件配置

**`91174ae`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/impl/CaptchaServiceImpl.java` | 2 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 2 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 7 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/UserService.java` | 6 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 8 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 3 | 0 |
| `homepage-auth/src/main/resources/templates/EmailVerificationCode.html` | 79 | 160 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 1 | 6 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 1 | 1 |

### 改动位置

- public class CaptchaServiceImpl implements CaptchaService {
- public class MailUtil {
- public class SecurityConfig {
- public class UserController {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme
- public interface UserService extends IService<UserEntity> {

---

## 2026-05-29 — Merge branch 'dev-mel0ny'

**`ec85bbd`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-28 — docs: 更新 CHANGELOG.md [skip ci]

**`a5d8f36`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 121 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-29 — 🐞 fix(yaml): 修改敏感信息

**`c9f45dd`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.gitignore` | 4 | 0 |
| `.idea/db-forest-config.xml` | 10 | 0 |
| `homepage-auth/src/main/resources/application-dev-template.yml` | 30 | 0 |
| `homepage-auth/src/main/resources/application.yml` | 1 | 31 |

### 改动位置

- target/

---

## 2026-05-29 — Merge branch 'dev-mel0ny'

**`b46f4f7`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`52fa866`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — docs: 更新 CHANGELOG.md [skip ci]

**`0b242d5`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 52 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-29 — 🐞 fix(yaml): 修复敏感信息

**`a5c2363`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 1 | 0 |
| `homepage-auth/src/main/resources/application-dev-template.yml` | 4 | 1 |
| `homepage-common/src/main/java/com/homepage/common/constant/JwtConstants.java` | 17 | 8 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-29 — Merge branch 'dev-mel0ny'

**`89600c2`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`099bac2`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — docs: 更新 CHANGELOG.md [skip ci]

**`dc45e8c`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 51 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-29 — 🔒 fix(auth,common): 修复代码审查发现的安全漏洞与质量问题

**`3b7717a`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 0 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 2 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/captcha/service/impl/CaptchaServiceImpl.java` | 3 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/config/SecurityConfig.java` | 25 | 3 |
| `homepage-auth/src/main/java/com/homepage/auth/user/controller/UserController.java` | 1 | 1 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 2 | 0 |
| `homepage-common/pom.xml` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/config/JwtConfig.java` | 8 | 4 |
| `homepage-common/src/main/java/com/homepage/common/constant/JwtConstants.java` | 3 | 18 |
| `homepage-common/src/main/java/com/homepage/common/constant/RedisConstants.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAccessDeniedHandler.java` | 2 | 0 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAuthenticationEntryPoint.java` | 2 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/AdminRegisterDTO.java` | 1 | 0 |
| `homepage-common/src/main/java/com/homepage/common/model/dto/RegisterDTO.java` | 3 | 2 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 2 | 2 |
| `homepage-common/src/main/java/com/homepage/common/util/RedisUtil.java` | 2 | 2 |
| `sql/init.sql` | 3 | 3 |

### 改动位置

- CREATE TABLE homepage_admin
- CREATE TABLE homepage_user
- public class AdminRegisterDTO implements CaptchaAware {
- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class CaptchaServiceImpl implements CaptchaService {
- public class JwtConfig {
- public class MailUtil {
- public class RedisUtil {
- public class RegisterDTO implements CaptchaAware {
- public class SecurityConfig {
- public class UserController {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme

---

## 2026-05-29 —  ✨  feat(ci): 集成 GitGuardian 密钥扫描到 GitHub Actions

**`bd27a30`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/workflows/ggshield.yml` | 23 | 0 |
| `.idea/compiler.xml` | 1 | 0 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-29 — Merge branch 'dev-mel0ny'

**`c0a1435`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`5e38939`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — docs: 更新 CHANGELOG.md [skip ci]

**`8809b35`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 93 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-30 — ♻️  refactor(auth): UserDetailsServiceImpl 改用 Mapper 打破循环依赖

**`bc4345e`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.idea/compiler.xml` | 1 | 0 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminServiceImpl.java` | 1 | 2 |
| `homepage-auth/src/main/java/com/homepage/auth/admin/service/impl/AdminUserDetailsServiceImpl.java` | 8 | 5 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserDetailsServiceImpl.java` | 12 | 8 |
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 13 | 4 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 1 | 1 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAccessDeniedHandler.java` | 2 | 1 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 0 | 2 |

### 改动位置

- public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> impl
- public class GlobalExceptionHandler {
- public class MailUtil {
- public class RestAccessDeniedHandler implements AccessDeniedHandler {
- public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> impleme

---

## 2026-05-30 — 🌈 style(auth,common): 修复警告

**`942f010`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `homepage-auth/src/main/java/com/homepage/auth/user/service/impl/UserServiceImpl.java` | 0 | 2 |
| `homepage-common/src/main/java/com/homepage/common/exception/GlobalExceptionHandler.java` | 0 | 1 |
| `homepage-common/src/main/java/com/homepage/common/exception/RestAccessDeniedHandler.java` | 0 | 1 |
| `homepage-common/src/main/java/com/homepage/common/util/MailUtil.java` | 0 | 3 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-30 — Merge branch 'dev-mel0ny'

**`d873994`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-30 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`c7a2a5e`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

## 2026-05-29 — docs: 更新 CHANGELOG.md [skip ci]

**`955cc21`** | github-actions[bot]

### 改动文件

| 文件 | + | - |
|------|---|---|
| `CHANGELOG.md` | 79 | 1 |

### 改动位置

*无具体位置信息。*

---

## 2026-05-30 — ♻️ refactor(docs): 移动文档文件到 doc 文件夹

**`c852fc8`** | Mel0ny

### 改动文件

| 文件 | + | - |
|------|---|---|
| `.github/scripts/gen-changelog.sh` | 1 | 1 |
| `.github/scripts/install-sync.sh` | 3 | 3 |
| `.github/scripts/sync-changelog.sh` | 5 | 5 |
| `.github/workflows/changelog.yml` | 1 | 1 |
| `CHANGELOG.md` | 0 | 1659 |
| `doc/CHANGELOG.md` | 1659 | 0 |
| `doc/项目文档.md` | 402 | 0 |
| `项目文档.md` | 0 | 402 |

### 改动位置

- VBS_EOF
- case "$(uname -s)" in
- cd "$REPO_ROOT"

---

## 2026-05-30 — Merge branch 'main' of https://github.com/mel0nyrame/HomePage

**`71db1b8`** | Mel0ny

*合并提交，变更细节见各子提交。*

---

*自动生成于 2026-05-29 18:44:10 UTC*

