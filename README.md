![](https://img.shields.io/hexpm/l/plug.svg) 
![](https://img.shields.io/badge/JitPack-1.0.2-green.svg)
![](https://img.shields.io/badge/Maven-1.0.2-orange.svg)

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
	compile 'com.github.codeccc:baselibrary-master:1.0.2'
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
    <version>1.0.2</version>
</dependency>
```

### 注明设计稿尺寸

在项目module下的AndroidManifest.xml中注明设计稿尺寸,如:

```
		<meta-data
            android:name="design_width"
            android:value="720"/>
        <meta-data
            android:name="design_height"
            android:value="1280"/>
```

### 引用的第三方库

名称 | 说明
---|---
[Zeuslog](https://github.com/xsfelvis/ZeusLog) | 支持移动端和控制台的日志工具
[CommonAdapter](https://github.com/hongyangAndroid/baseAdapter) | Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况。 <br> 我在此基础上在ViewHolder中增加了一些方法,在COmmonAdapter中增加了点击事件的回调。
[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout) | Android屏幕适配方案，直接填写设计图上的像素尺寸即可完成适配，最大限度解决适配问题。
[MagicViewPager](https://github.com/hongyangAndroid/MagicViewPager) | 单页显示3个Item的ViewPager炫酷切换效果，适用于Banner等。
[glide](https://github.com/bumptech/glide) | 一款主流图片加载框架
[FlycoRoundView](https://github.com/H07000223/FlycoRoundView) |  一个扩展原生控件支持圆角矩形框背景的库,可以减少相关shape资源文件使用. 
[SmoothCheckBox](https://github.com/andyxialm/SmoothCheckBox) | 一个带动画的自定义checkbox
[NineGridlayout](https://github.com/LukeMee/NineGridlayout) | 九宫格图片 <br>  我在此库基础上增加了宫格图片的单机和长按事件
[NumberPickerView](http://note.youdao.com/) | 更灵活的PickerView
[StatusLayoutManager](http://www.jianshu.com/p/9d53893b3eda) |无网络,无数据,获取数据失败,加载中等状态布局切换
[PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip) | 用于在ViewPager的不同页面之间导航的交互式指示器
 



### [查看常见问题](https://github.com/codeccc/baselibrary-master/blob/master/docs/NOTICE.md "常见问题")

## [更新日志](https://github.com/codeccc/baselibrary-master/blob/master/docs/update.md "更新日志")

### v1.0.2,2017/5/6
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
