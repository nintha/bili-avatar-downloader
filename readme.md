# bili-avatar-downloader

中文简介在英文简介下方

A tool to download bilibili user's avatar images

You can edit properties file`/src/main/resource/avatar.properties` to change config

```properties
# input file path, using csv format without header-line, only one column
avatar.input.file-path=./faces.csv
# the download path, every 3000 images save into a zip file
avatar.storage.dir=./storage
# record download process, file save in path ./
avatar.reader-mark.name=readerMark
# image size, 16 means height=16px and width=16px, optional values：16、24、32、64、72
avatar.image.size=16
```

### dependencies

- java 1.8

### build

```bash
cd bili-avatar-downloader
./gradlew build -x test
```

The jar file will be created in `build/lib/`

### run

```bash
cd build/lib
java -jar bili-avatar-0.0.1.jar
```







# 中文简介

b站用户头像爬取

相关配置修改见`/src/main/resource/avatar.properties`配置文件

```properties
# 输入数据源，csv格式, 没有headerLine，\n作为换行符，仅一列数据
avatar.input.file-path=./faces.csv
# 下载图片的存储路径, 每3000个图片放入一个压缩包.
avatar.storage.dir=./storage
# 下载进度记录文件名，文件位置在 ./
avatar.reader-mark.name=readerMark
# 下载的图片大小，16代表高宽均为16px，可用值：16、24、32、64、72
avatar.image.size=16
```

### 依赖

- java 1.8 

### 构建

```bash
cd bili-avatar-downloader
./gradlew build -x test
```

生成的jar文件在`build/lib/`目录下

### 运行

```bash
cd build/lib
java -jar bili-avatar-0.0.1.jar
```



