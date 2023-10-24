## Android Permission Monitor

Android 隐私政策敏感权限监控。基于 [BCU](https://github.com/Ysj001/BytecodeUtil) 的 [modifier-aspect](https://github.com/Ysj001/bcu-modifier-aspect) 实现。

通过在编译时代理敏感调用来监控敏感权限是否被调用，并支持检测通过反射调用敏感权限的行为。

- 如果本项目对你有所帮助，欢迎 start。
- 如果有遗漏的敏感权限欢迎提 issues 给我。

### Compile

工程结构如下

- `app` 用于演示的 demo
- `buildSrc` 用于管理 maven 发布，和一些项目统一配置
- `lib_permission_monitor` 本项目核心实现

### Quick Start

1. 用到的依赖版本

   - BCU：[![](https://jitpack.io/v/Ysj001/BytecodeUtil.svg)](https://jitpack.io/#Ysj001/BytecodeUtil)
   - modifier-aspect：[![](https://jitpack.io/v/Ysj001/bcu-modifier-aspect.svg)](https://jitpack.io/#Ysj001/bcu-modifier-aspect)

2. 在项目的根 `build.gradle.kts` 中配置如下

   ```kotlin
   buildscript {
       repositories {
           maven { setUrl("https://jitpack.io") }
       }
       
       dependencies {
           // BCU 插件依赖
           classpath("com.github.Ysj001.BytecodeUtil:plugin:<lastest-version>")
           // modifier-aspect 依赖
           classpath("com.github.Ysj001.bcu-modifier-aspect:modifier-aspect:<lastest-version>")
       }
   }
   
   subprojects {
       repositories {
           maven { url 'https://jitpack.io' }
       }
   }
   ```

3. 在 `app` 模块的 `build.gradle.kts` 中的配置如下

   ```kotlin
   plugins {
       id("com.android.application")
       id("org.jetbrains.kotlin.android")
       // 添加 bcu 插件
       id("bcu-plugin")
   }
   
   // 配置 bcu 插件
   bytecodeUtil {
       loggerLevel = 1
       modifiers = arrayOf(
           // 使用 modifier-aspect 的 Modifier 实现
           Class.forName("com.ysj.lib.bcu.modifier.aspect.AspectModifier"),
       )
       notNeed = { entryName ->
           entryName.startsWith("kotlin")
               || entryName.startsWith("java")
               || entryName.startsWith("com/ysj/lib/bcu/modifier")
           // 请按需配置过滤，可大幅提升编译输速度
       }
   }
   
   dependencies {
       // 只在 debug 且运行时下依赖
       debugRuntimeOnly("com.github.Ysj001.PermissionMonitor:permission-monitor:<lastest-version>")
   }
   ```

4. 在 `logcat` 中过滤  `tag` 为 `PermissionMonitor`，即可观察到是否有敏感权限被调用了。

   - 普通调用监控效果如下：

     ![普通调用](readme_assets/1.png)

   - 反射调用监控效果如下：

     ![反射调用](readme_assets/2.png)



