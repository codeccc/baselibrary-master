![](https://img.shields.io/hexpm/l/plug.svg) 
![](https://img.shields.io/badge/JitPack-1.0.2-green.svg)
![](https://img.shields.io/badge/Maven-1.0.2-orange.svg)

 # BaseLibrary 
### [中文版](https://github.com/FancyOnePoint/BaseLibrary)
The library contains BaseActivity、BaseFragmentActivity、BaseFragment、some commonly used custom widgets,ListView/GridView/RecyclerView generic CommonAdapter and some tools,log library and so on.

## How To Use

### Gradle

1.Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2.Add the dependency
```
dependencies {
	compile 'com.github.codeccc:baselibrary-master:1.0.1'
}
```

### Maven

1.Add the JitPack repository to your build file

```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

2.Add the dependency

```
<dependency>
    <groupId>com.github.codeccc</groupId>
    <artifactId>baselibrary-master</artifactId>
    <version>1.0.1</version>
</dependency>
```

### [Have any questions ?](https://github.com/codeccc/baselibrary-master/blob/master/docs/NOTICE.md "常见问题")

## [Release Notes](https://github.com/codeccc/baselibrary-master/blob/master/docs/update.md "更新日志")

### v1.0.2,2017/5/6
- update library's minSdkVersion to 14

### v1.0.0,2017/5/5

- support BaseActivity,BaseFragment,BaseFragmentActivity;
- some commonly used widgets;
- ListView/Gridview/RecyclerView'sCommonAdapter;
- some commonly used utils

## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
