# mybatis

## 1.编写自定义的配置文件和映射文件。
## 2.使用Classloader加载全局配置文件，返回InputStream对象 
## 3.配置文件加载
### 全局配置文件加载,将XML信息存储到Configuration对象
#### 使用saxReader去读取InputStream对象,创建Document对象
#### 使用dom4j+xpath语法去解析Document对象
#### 解析environments标签
##### 封装DataSource对象,放入Configuration对象中
#### 解析mappers标签
### 映射文件加载,将XML信息封装到MappedStatement对象并放入Map集合中，key为statement的id， value为MappedStatement对象
#### 使用Classloader加载全局配置文件，返回InputStream对象 
#### 使用sax Reader去读取InputStream对象，创建Document对象 
#### 使用dom4j+xpath语法去解析Document对象
#### 解析根标签中的namespace属性和select标签
##### 解析id属性
##### 解析parameterType属性,并处理成Class类型
##### 解析resultType属性,并处理成Class类型
##### 解析statementType属性(便于执行时,是选择Statement还是preparedStatement)
##### 解析SQL语句(#{}和${})
###### 将#{}替换为占位符?
####### 存储预编译SQL(已经将#{}替换为?的SQL语句)
####### 创建List<ParameterMapping>集合，存储SQL参数信息(属性名称、参数类型) 
###### 将${}替换成真正的参数
####### 使用OGNL表达式获取参数值 
####### 替换${}的内容
## 4.sqlsession执行
### 制定sqlsession接口和api方法 
### 创建SqlSession
#### 创建Sqlsession需要先创建SqlSessionFactory，通过SqlSessionFactoryBuilder使用构建者模式来创建，此时需要Configuration对象去创建SqlSessionFactory 
### 执行sqlsession:参数有两个(statementId和参数对象)
#### 根据statementId，去Configuration中的MappedStatement集合中查找对应的MappedStatement 对象。
#### 取出MappedStatement中的SQL信息 
#### 取出MappedStatement中的statementType，用来创建Statement对象
##### 取出MappedStatement中的Configuration对象，通过Configuration对象，获取 DataSource对象，通过DataSource对象，创建Connection，通过Connection创建 Statement对象。
##### 设置参数 
###### 执行preparedStatement.setInt(1,value);
####### 遍历List<ParameterMapping>集合(参数名称、属性类型、顺序) 
######## 判断是否是集合类型、8种基本类型、String类型、引用类型
######### 基本类型的话，传过来的参数就是SQL的参数值
######### 引用类型的话，根据参数名称，获取引用类型对应的属性值 
######## 调用setXXX方法赋值
##### 执行Statement
###### executeQuery方法，获取ResultSet结果集 
##### 处理结果集
###### 遍历结果集ResultSet
####### 取出ResultSet中的所有列的名称和值和类型，存储到一个集合中 
####### 取出MappedStatement中的resultTypeClass，反射进行实例化。 
####### 遍历上面的集合，根据集合中的列的名称给属性赋值