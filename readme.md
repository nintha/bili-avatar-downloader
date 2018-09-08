# bili-avatar-downloader

b站用户头像爬取



数据源为项目根目录下的faces.csv文件，格式为每行一个头像url后缀类似`f7aba29c6c88fa00ff035adce7edcce5cf7c493a.jpg`，并用`\n`作为换行符

数据结果会存入storage文件夹，每3000个图片放入一个压缩包.

### 断点续传

运行时会在项目目录下创建readerMark文件，用于记录下载进度

