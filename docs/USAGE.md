## 开始使用

### Base基类的使用

BaseActivity,BaseFragment,BaseFragmentActivity都有全局变量mContext,在子类中需要用到Context对象或者this的地方可以直接使用mContext代替 (实现的点击事件的this除外)。

Base基类除了setLogTag()方法在OnCreate中有调用,widgetClick()方法无需调用外其余方法都需在子类中手动调用


### 1. `Activity` 继承 `BaseActivity`


> **在不使用Fragment,ViewPager的情况下Activity继承自BaseActivity。**

Activity继承自BaseActivity可以规范代码风格,提高代码可读性以及提高开发效率。

`Activity` 继承 `BaseActivity`会重写如下方法:


	@Override
    protected void setRequestedOrientation() {

    }

    @Override
    public void onCreate(Bundle bundle, Object o) {

    }

    @NonNull
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @NonNull
    @Override
    public String setLogTag() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void showResult(Object o) {

    }

    @Override
    public void widgetClick(View view) {

    }




各个方法含义解释如下:

**setRequestedOrientation()**：设置当前Activity屏幕方向,如,`setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);`

 **getLayoutId()** ：返回布局文件资源id,如返回 `return R.layout.activity_main;` ,此方法需要在 `onCreate()` 方法中调用
`setContentView(getLayoutId());`,之所以没有直接在基类中直接调用`setContentView()`是考虑到开发者会有使用DataBinding的需求以及会在`setContentView()`之前做一些事情。

**setLogTag()** ：设置当前log日志打印的tag,在有日志打印需求时可以直接打印而不用设置tag。

**initView()**： 在这里初始化控件,做一些比如findviewbyid,setonclicklistener等与控件操作直接相关的事情。

**initData()** ：初始化全局变量方法。

**onLoadData()** ： 获取网络数据的方法。

**showResult()** ： 将获取到的数据更新到UI上的方法。建议在onLoadData()方法中获取到数据后调用方法将结果传入到方法中。该方法在UI线程中执行。

**widgetClick()** ： 处理控件点击事件的方法。

**以下方法可在activity中直接调用**

- 快速设置控件点击事件

在Activity的方法中调用 `setClick(view1,view2,view3,...);` 即可为多个控件设置点击事件监听。该方法的实现为:

    public void setClick(@NonNull View... v) {
    	for (int i = 0; i < v.length; i++) {
    		v[i].setOnClickListener(this);
    	}
    }


- 格式化打印json数据

	

    json(jsonStr); 
    


- 打印日志
```
i(object);

e(object);

d(object);

......
```

- 加载网络图片

```

    setCircleImage(imageview,url);//加载圆形图片********* 常用  *********

    setImageUrl(imageview,url);//加载图片********* 常用  *********

    setImageRadius(imageview,url,radius);//加载指定圆角大小图片********* 常用  *********

    setCircleImageDrawable(imageview,drawable);//加载Drawable圆形图片

	setCircleImageFile(imageview,imageFile);//加载圆形File类型图片

    setImageDrawableRadius(imageview,drawable,radius);//加载Drawable指定圆角大小图片
    
    setImageFileRadius(imageview,imageFile,radius);//加载File类型指定圆角大小图片
    
    setImageDrawable(imageview,drawable);//加载drawable图片

    setImageFile(imageview,imageFile);//加载File图片
    
    setImageUri(imageview,uri);//根据图片Uri加载图片
    
    setCircleImage(imageview, url,errorResId,  placeholderResId);//指定加载失败图片以及加载中占位图加载圆形图片
    
    setImageRadius(imageview, url,radius,errorResId,  placeholderResId);//指定加载失败图片以及加载中占位图加载指定圆角大小图片
    
    setImageUrl(imageview, url,errorResId,  placeholderResId);//指定加载失败图片以及加载中占位图加载网络图片
    
    setImageUri(imageview, url,errorResId,  placeholderResId);//指定加载失败图片以及加载中占位图加载图片


```

- 弹出PopupWindow后更改背景透明度


``` 
	
	backgroundAlpha(alpha);//alpha float 透明度,范围在0-1之间

```


- 修改背景透明度


```

    setAlpha(view,alpha);//alpha 透明度,范围在0-255之间

```

- 设置控件宽度
    
```
    setWidth(view,width);//设置控件宽度
```

-  设置控件高度

```
    setHeight(view,height);//设置控件高度
```

- 设置控件宽高

```
    setWidthHeight(view, width, height);//设置控件宽高
    
```

- 获取控件文本

```

text(eitText);
text(textview);
text(button);

```

- 格式化时间字符串
    
```

String formartTimeWithDate( time,  format);//format like "yyyy-MM-dd HH:mm:ss"

```

- 得到Drawable资源


```
getDrawableRes(resId);
```

- 得到color int值


```
getColorInt(colorResId);
```

- toast提示

    Toast提示使用了自定义Toast,解决Toast连续弹出的延迟问题

    传入参数类型为常用类型,省去转换麻烦

```
//toast提示object内容
shortToast(object);
longToast(object);

```

- 双击退出Activity

    子类Activity重写`exitBy2Click()`方法便可实现双击退出功能

```

exitBy2Click();//双击退出函数.

```

- activity设置全屏

    设置是否全屏,默认非全屏
```

setAllowFullScreen(true);//true or false,在SetContentView之前调用

```

- 跳转Activity

    传入目标Activity即可跳转activity;
    
    有三个方法,可选择不带切换动画,带切换动画以及制定切换动画三种方法可以调用;
    
    Intent传值情况暂未封装方法。

```
goToActivity(clazz);/传入Class即可跳转
goToActivityWithAnim(clazz);//跳转到指定Activity ,从右往左进入
goToActivityWithAnim(clazz,enterAnim,exitAnim);//跳转到指定Activity ,指定进入退出动画.
```


### 2. `Activity` 继承 `BaseFragmentActivity`

> **在使用Fragment,ViewPager的情况下Activity继承自BaseFragmentActivity。**

重写的方法,可调用的方法与 [BaseActivity](#BaseActivity) 

### 3. `Fragment` 继承 `BaseFragment`

> **大部分方法与BaseActivity保持一致,下面是特有方法**

BaseFragment有公开变量mView,子类无需在声明可直接使用;


```

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup group,     @Nullable Bundle bundle, Object o) {
     //mView = inflater.inflate(getLayoutId(), null);
     //return mView;
    return null;
}

@Override
public void onActivityCreated(@Nullable Bundle bundle, Object o) {

}
```
