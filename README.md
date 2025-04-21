# AndroidBasis
(![img.png](img.png)持续更新中)企业级 Android 开发基础框架，克隆下来本代码后，即可快速开发 Android 项目。该框架提供了常见的功能模块，包括网络请求、数据存储、UI 组件等，帮助开发者快速搭建项目基础架构。

## 依赖项

项目中集成了以下三方库，以下是详细的依赖库列表及其用途说明：

- **网络请求**：
    - **Net**：集成 [Net](https://github.com/liangjingkanji/Net)，Android 基于协程/OkHttp 网络请求工具。
    - **OkHttp**：集成 [OkHttp](https://square.github.io/okhttp/)，高效的 HTTP 客户端库，支持同步和异步请求。

- **JSON 序列化**：
    - **kotlinx-serialization-json**：集成 [kotlinx-serialization-json](https://kotlinlang.org/docs/serialization.html)，Kotlin 的 JSON 序列化库，用于数据序列化和反序列化。

- **UI 组件**：
    - **ViewBinding-KTX**：集成 [ViewBinding-KTX](https://github.com/DylanCaiCoding/ViewBindingKTX)，简化 ViewBinding 的使用，减少模板代码。
    - **ImmersionBar**：集成 [ImmersionBar](https://github.com/gyf-dev/ImmersionBar)，沉浸式状态栏解决方案，支持多种状态栏样式。
    - **WheelPicker**：集成 [WheelPicker](https://github.com/AigeStudio/WheelPicker)，一个美观的滚轮选择器组件。

- **工具类**：
    - **UtilCodeX**：集成 [UtilCodeX](https://github.com/Blankj/AndroidUtilCode)，一个功能强大的 Android 工具类库，包含各种常用工具方法。
    - **ZXing-Lite**：集成 [ZXing-Lite](https://github.com/jenly1314/ZXingLite)，一个轻量级的二维码生成和扫描库。

- **权限请求**：
    - **XXPermissions**：集成 [XXPermissions](https://github.com/getActivity/XXPermissions)，一个简单易用的 Android 权限请求框架。

- **多语言支持**：
    - **MultiLanguages**：集成 [MultiLanguages](https://github.com/getActivity/MultiLanguages)，一个支持多语言切换的框架。

- **存储**：
    - **MMKV**：集成 [MMKV](https://github.com/Tencent/MMKV)，一个高性能的移动端键值存储框架。

- **内存泄漏检测**：
    - **LeakCanary**：集成 [LeakCanary](https://github.com/square/leakcanary)，一个内存泄漏检测工具，帮助开发者发现和修复内存泄漏问题。

```text
Copyright 2025 xiaolu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```