![](https://img.shields.io/hexpm/l/plug.svg) 
![](https://jitpack.io/v/FancyOnePoint/BaseLibrary.svg)
![](https://img.shields.io/badge/maven-1.0.0-orange.svg)

 # BaseLibrary 
### [English](https://github.com/FancyOnePoint/BaseLibrary/blob/master/README_EN.md "English")
包含BaseActivity、BaseFragmentActivity、BaseFragment、一些常用自定义控件,ListView/GridView/RecyclerView通用的CommonAdapter以及一些工具类,日志库等

## 如何使用

### Gradle

1.把下面代码添加到项目根目录 build.gradle下:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.在module的 `build.gradle` 中添加以下依赖:
```
	dependencies {
		compile 'com.github.codeccc:baselibrary-master:1.0.0'
	}
```

### Maven

1.添加 JitPack 依赖到你的 build 文件中:

```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

2.添加如下依赖:

```
	<dependency>
	    <groupId>com.github.codeccc</groupId>
	    <artifactId>baselibrary-master</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

## [更新日志](https://github.com/codeccc/baselibrary-master/blob/master/docs/update.md "更新日志")

### v1.0.1,2017/5/6
- 修改library的minSdkVersion为14

### v1.0.0,2017/5/5

- 提供BaseActivity,BaseFragment,BaseFragmentActivity支持;
- 加入一些常用自定义控件;
- ListView/Gridview/RecyclerView通用CommonAdapter;
- 一些常用的工具类

## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
