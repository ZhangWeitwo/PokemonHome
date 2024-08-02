# PokemonHome
这是一个宝可梦管理项目，训练师可以管理自己的宝可梦
# 使用技术
## 前端
前端使用了三件套，使用了webGL，尤其是Three.js，同时也使用了bootstrap进行前端搭建。使用了RESTFul的风格进行路由的搭建。
## 后端
后端使用springboot以及SSM，同时还使用了mybatis进行数据库连接。保存用户数据使用的是session进行保存。聊天数据使用的是redis进行保存。数据库使用的是mysql。
# 如何使用这个项目
clone到本地，首先下载redisserver.exe开启redis，再使用所给文档建立数据库。更改springboot中.properties文件的配置(主要是数据库密码，改成自己的数据库)。记得使用sql文件创建对应数据库。最后启动数据库即可在网页访问。
