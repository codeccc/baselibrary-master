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
	compile 'com.github.codeccc:baselibrary-master:1.0.2'
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
    <version>1.0.2</version>
</dependency>
```

### Specify the size of the design

Specify the size of the design in the module's AndroidManifest.xml,example:

```
<meta-data
    android:name="design_width"
    android:value="720"/>
<meta-data
    android:name="design_height"
    android:value="1280"/>
```
### Internal Libraries

名称 | 说明
---|---
[Zeuslog](https://github.com/xsfelvis/ZeusLog)   |  支持移动端和控制台的日志工具 [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/ZeusLog.md) 
[CommonAdapter](https://github.com/hongyangAndroid/baseAdapter) | Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况。 <br> 我在此基础上在ViewHolder中增加了一些方法,在COmmonAdapter中增加了点击事件的回调。 [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/CommonAdapter.md) 
[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout) | Android屏幕适配方案，直接填写设计图上的像素尺寸即可完成适配，最大限度解决适配问题。 [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/AndroidAutoLayout.md) 
[MagicViewPager](https://github.com/hongyangAndroid/MagicViewPager) | 单页显示3个Item的ViewPager炫酷切换效果，适用于Banner等。 [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/MagicViewPager.md) 
[glide](https://github.com/bumptech/glide) | 一款主流图片加载框架 
[FlycoRoundView](https://github.com/H07000223/FlycoRoundView) |  一个扩展原生控件支持圆角矩形框背景的库,可以减少相关shape资源文件使用.  [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/FlycoRoundView.md) 
[SmoothCheckBox](https://github.com/andyxialm/SmoothCheckBox) | 一个带动画的自定义checkbox [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/SmoothCheckBox.md) 
[NineGridlayout](https://github.com/LukeMee/NineGridlayout) | 九宫格图片 <br>  我在此库基础上增加了宫格图片的单机和长按事件
[NumberPickerView](https://github.com/Carbs0126/NumberPickerView) | 更灵活的PickerView [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/NumberPickerView.md) 
[StatusLayoutManager](http://www.jianshu.com/p/9d53893b3eda) |无网络,无数据,获取数据失败,加载中等状态布局切换 [README](https://github.com/codeccc/baselibrary-master/blob/master/docs/StatusLayoutManager.md) 
[PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip) | 用于在ViewPager的不同页面之间导航的交互式指示器
 


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
