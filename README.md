### 主要功能：通过自定义属性或者代码实现shape效果和裁剪效果  


[//]: >![github](https://github.com/zhongruiAndroid/MyFastshape/blob/master/app/src/main/res/drawable/demo.png=320x640)  
<img src="https://github.com/zhongruiAndroid/MyFastshape/blob/newViewHelper/app/src/main/res/drawable/demo.png" alt="image"  width="auto" height="640">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/zhongruiAndroid/MyFastshape/blob/newViewHelper/app/src/main/res/drawable/demoimage.jpg" alt="image"  width="auto" height="640">  
  
    
    
|   [ ![Download](https://api.bintray.com/packages/zhongrui/mylibrary/fastshape/images/download.svg) ](https://bintray.com/zhongrui/mylibrary/fastshape/_latestVersion) |  最新版本号 |
|--------|----|
    
    

```xml
compile 'com.github:fastshape:版本号看上面'
```
<br/>
## [Demo.apk下载](https://raw.githubusercontent.com/zhongruiAndroid/MyFastshape/newViewHelper/app/release/demo.apk "apk文件")
  
#### XML设置  
```xml
<com.github.fastshape.MyLinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:borderColor="#FF4081"
        app:borderDashGap="2dp"
        app:borderDashWidth="4dp"
        app:borderWidth="2px"
        app:radius="6dp"
        app:solidColor="#34e8a6"
        app:clipSwitch="true"
        app:clipBorderDashWidth="15dp"
        app:clipBorderWidth="2dp"
        app:clipBorderColor="#34e8a6"
        >
</com.github.fastshape.MyLinearLayout>

<com.github.fastshape.MyCheckBox
        android:layout_width="120dp"
        android:layout_height="50dp"
        android:text="Box"
        android:gravity="center"
        
        android:background="@null"
        android:button="@null"
        
        app:borderColor="#FF4081"
        app:borderDashGap="2dp"
        app:borderDashWidth="4dp"
        app:borderWidth="2px"
        app:gradientCenterColor="#18B4ED"
        app:gradientStartColor="@color/fenhong"
        app:gradientEndColor="@color/c_press"
        app:gradientType="sweep"
        app:radius="10dp"
        app:solidColor="@color/c_press"
        android:checked="true"
        app:checked_drawable_left="@drawable/select"
        app:normal_drawable_left="@drawable/normal"
        app:padding_left="20dp"
        />

<com.github.fastshape.MyEditText
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:orientation="vertical"
        android:text="Android"
        android:textSize="19sp"
        android:textColor="#333333"
        
        app:clearIconDrawable="@drawable/text_clear"
        app:hiddenClearIcon="false"
        app:clearIcon_height="30dp"
        />

```



# 注意 注意 注意
**不能有android:background属性,否则部分自定义属性不生效**  

**MyButton,MyCheckBox,MyRadioButton需要手动设置android:background="@null",否则部分自定义属性会被覆盖**    

#### 代码设置  

```java
//链式结构
MyLinearLayout linearLayout=new MyLinearLayout(this);
//或linearLayout=findViewById(R.id.ll);
linearLayout.getViewHelper()
//.clearShapeAttr()重置shape相关属性
.setGradientType(FirstHelper.gradientType_linear)
.setSolidColor(ContextCompat.getColor(this,R.color.white))
.setRadius(20)//px
.complete();//或者linearLayout.complete();
//completeClip()用于view裁剪


MyEditText editText=new MyEditText(this);
//或editText=findViewById(R.id.et);
//设置清除icon
editText.setClearIconDrawable(R.drawable.yourResId);
//只设置icon高度(此时宽度自适应)
editText.setClearIcon_height(30);//px
//是否隐藏icon,默认值:false
editText.setHiddenClearIcon(true);
//给icon设置新的点击事件
editText.setOnRightListener(new MyEditText.OnRightListener() {
    @Override
    public boolean clickRight() {
		//此处自行处理icon点击事件
		
        //返回值：false:事件继续传递清除text,true:消费事件,不清除text
        return false;
    }
});

```
<br/>  

#### 如果每个view有公共属性想统一控制，可以在Theme里面设置
##### 优先级 xml > style > theme
```xml
<style name="your project Theme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- 一定要放在你自己项目或者Activity使用的 theme 标签下面，否则无效-->

	<!-- 以下item name属性不能改成其他名字-->
    
    <!-- 对应MyLinearLayout-->
    <item name="MyLinearLayoutStyle">@style/llStyle</item>

    <!-- 对应MyRelativeLayout-->
    <item name="MyRelativeLayoutStyle">yourStyle</item>

    <!-- 对应MyFrameLayout-->
    <item name="MyFrameLayoutStyle">@style/flStyle</item>

    <!-- 对应MyTextView-->
    <item name="MyTextViewStyle">@style/tvStyle</item>-->

    <!-- 对应MyButton-->
    <item name="MyButtonStyle">@style/btStyle</item>

    <!-- 对应MyEditText-->
    <item name="MyEditTextStyle">@style/etStyle</item>

    <!-- 对应MyRadioButton-->
    <item name="MyRadioButtonStyle">@style/rbStyle</item>

    <!-- 对应MyCheckBox-->
    <item name="MyCheckBoxStyle">@style/cbStyle</item>-->

    <!-- 对应MyImageView-->
    <item name="MyImageViewStyle">@style/ivStyle</item>-->
</style>


<style name="llStyle">
    <item name="borderColor">@color/red_point1</item>
    <item name="borderDashGap">26sp</item>
    <item name="borderDashWidth">6sp</item>
    <item name="borderWidth">6sp</item>
    <item name="radius">56dp</item>
    <item name="solidColor">@color/c_press_bt</item>
    <!--此处省略其他自定义属性-->
    <!--只要布局文件能用的属性，这里都能写,只不过没有智能提示-->
</style>

```



<br/>  

## ClipHelper属性
**MyRelativeLayout，MyLinearLayout，MyFrameLayout，MyImageView公共属性**  

<br/>    

| 属性名                | 说明                     | 属性类型  |                          |
|-----------------------|--------------------------|-----------|--------------------------|
| clipSwitch            | 是否进行裁剪             | boolean   | 默认值false,需要手动开启 |
| clipBg                | 是否裁剪背景             | boolean   | 默认值true               |
| clipIsCircle          | 是否裁剪成圆形           | boolean   | 默认值false              |
| clipIsAreaClick       | 是否只有剩余部分可点击   | boolean   | 默认值true               |
| clipIgnorePadding     | 裁剪是否忽略padding      | boolean   | 默认值true               |
| clipRadius            | 设置裁剪圆角半径         | dimension | 覆盖下面4个属性          |
| clipTopLeftRadius     | 设置裁剪左上角半径       | dimension |                          |
| clipTopRightRadius    | 设置裁剪右上角半径       | dimension |                          |
| clipBottomLeftRadius  | 设置裁剪左下角半径       | dimension |                          |
| clipBottomRightRadius | 设置裁剪右下角半径       | dimension |                          |
| clipBorderWidth       | 设置裁剪边框宽度         | dimension |                          |
| clipBorderColor       | 设置裁剪边框颜色         | color     |                          |
| clipBorderDashWidth   | 设置裁剪边框虚线长度     | dimension |                          |
| clipBorderDashGap     | 设置裁剪边框虚线间隔     | dimension |                          |
| clipBorderDashBgColor | 设置裁剪边框虚线背景     | color     |                          |
| clipBorderPhase       | 改变这个值虚线绕边框走动 | integer   | 需要不停的改变才会有效果 |  

<br/>  

## FirstHelper属性
**MyRelativeLayout，MyLinearLayout，MyFrameLayout，MyTextView，MyEditText，MyButton公共属性**


|属性名                         | 说明                               |                     属性类型                     |                  |
|--------------------------------|------------------------------------|:------------------------------------------------:|------------------|
| drawable_normal<br/>drawable_press | 设置正常状态背景和press状态背景    |                     drawable                     | 覆盖以下所有属性 |
| pressColor                     | 设置press颜色,设置了点击事件才生效 |                       color                      |                  |
| left_line                      | 显示左边框                         |                      boolean                     |                  |
| top_line                       | 显示上边框                         |                      boolean                     |                  |
| right_line                     | 显示右边框                         |                      boolean                     |                  |
| bottom_line                    | 显示底边框                         |                      boolean                     |                  |
| all_line                       | 显示所有边框                       |                      boolean                     | 覆盖上面4个属性  |
| shapeType                      | 设置shape类型,默认rectangle        |      rectangle(默认):矩形<br/>oval:椭圆<br/>line:线      |                  |
| borderWidth                    | 边框宽度                           |                     dimension                    |                  |
| borderColor                    | 边框颜色                           |                       color                      |                  |
| borderDashWidth                | 边框虚线长度                       |                     dimension                    |                  |
| borderDashGap                  | 边框虚线间隔距离                   |                     dimension                    |                  |
| solidColor                     | view填充色(相当于背景色)           |                       color                      |                  |
| topLeftRadius                  | 左上方圆角                         |                     dimension                    |                  |
| topRightRadius                 | 右上方圆角                         |                     dimension                    |                  |
| bottomLeftRadius               | 左下方圆角                         |                     dimension                    |                  |
| bottomRightRadius              | 右下方圆角                         |                     dimension                    |                  |
| radius                         | view圆角                           |                     dimension                    | 覆盖上面4个属性  |
| gradientType                   | 渐变类型                           | linear:线性渐变<br/>radial:放射渐变<br/>sweep:扫描性渐变 |                  |
| gradientAngle                  | 渐变角度                           |        a0,a45,a90,a135<br/>a180,a225,a270,a315       |                  |
| gradientCenterX                | 渐变的X轴起始位置                  |                  范围0~1,默认0.5                 |                  |
| gradientCenterY                | 渐变的Y轴起始位置                  |                  范围0~1,默认0.5                 |                  |
| gradientStartColor             | 渐变起始颜色                       |                       color                      |                  |
| gradientCenterColor            | 渐变中间颜色                       |                       color                      |                  |
| gradientEndColor               | 渐变结束颜色                       |                       color                      |                  |
| gradientRadius                 | 渐变半径                           |         gradientType="radial"适用<br/>默认40         |                  |


  
## SecondHelper属性
**MyTextView，MyButton，MyCheckBox，MyRadioButton公共属性**  

|    属性名    | 说明                                                          | 属性类型  |
|----------------------------|---------------------------------------------------------------|---|
| left_width<br/>left_height     | 设置drawableLeft宽高,只设置其中一个属性自动适配另外一个属性   | dimension  |
| top_width<br/>top_height       | 设置drawableTop宽高,只设置其中一个属性自动适配另外一个属性    | dimension  |
| right_width<br/>right_height   | 设置drawableRight宽高,只设置其中一个属性自动适配另外一个属性  | dimension  |
| bottom_width<br/>bottom_height | 设置drawableBottom宽高,只设置其中一个属性自动适配另外一个属性 | dimension  |



## ThirdHelper属性
| MyCheckBox,MyRadioButton           | 说明                                                                          | 属性类型 |
|------------------------------------|-------------------------------------------------------------------------------|----------|
| normal_drawable checked_drawable   | 设置normal和check状态的drawable                                               | drawable |
| normal_textColor checked_textColor | 设置normal和check状态的文字颜色                                               | color    |
| drawable_direction                 | 设置button所在方向left,top,right,bottom<br/>设置此属性需要:android:button="@null" |          |


## MyEditText属性
| MyEditText                       | 说明                                                    | 属性类型  |
|----------------------------------|---------------------------------------------------------|-----------|
| clearIconDrawable                | 设置清除按钮drawable(点击清除内容)                                    | drawable  |
| hiddenClearIcon                  | 设置清除按钮是否隐藏,默认false                          | boolean   |
| clearIcon_width<br/>clearIcon_height | 设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性 | dimension |


<br/>  

|   [ ![Download](https://api.bintray.com/packages/zhongrui/mylibrary/fastshape/images/download.svg) ](https://bintray.com/zhongrui/mylibrary/fastshape/_latestVersion) |  最新版本号 |
|--------|----|
  

```xml
compile 'com.github:fastshape:版本号看上面'
```
