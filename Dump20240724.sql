-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: pokedex
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commodity` (
  `propid` int DEFAULT NULL,
  `price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES (1,5),(2,1),(3,2),(4,3),(5,5),(6,1),(7,2),(8,3),(9,4),(10,5);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `userID` int NOT NULL,
  `friendsID` int NOT NULL,
  `messageID` int DEFAULT NULL,
  PRIMARY KEY (`userID`,`friendsID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (0,1,3),(0,2,2),(0,5,4),(1,0,3),(1,2,5),(1,5,6),(2,0,2),(2,1,5),(2,5,7),(3,5,9),(4,5,8),(5,0,4),(5,1,6),(5,2,7),(5,3,9),(5,4,8),(5,6,10),(6,5,10);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `propid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`propid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'神奇果实','能够立即恢复所有HP。','itempic/1.png',5.00),(2,'超级球','比普通精灵球更容易捕捉精灵。','itempic/2.png',1.00),(3,'解麻药','治愈被麻痹状态的精灵。','itempic/3.png',2.00),(4,'火焰石','携带此道具的火属性精灵特攻提升。','itempic/4.png',3.00),(5,'月亮石','进化某些精灵所需的神秘石头。','itempic/5.png',5.00),(6,'水之石','进化某些精灵所需的水属性石头。','itempic/6.png',1.00),(7,'草之石','进化某些精灵所需的草属性石头。','itempic/7.png',2.00),(8,'幸运蛋','携带此道具的精灵可以获得额外的经验值。','itempic/8.png',3.00),(9,'速度之翼','携带此道具的精灵速度提升。','itempic/9.png',4.00),(10,'金属外套','携带此道具的钢属性精灵攻击力提升。','itempic/10.png',5.00);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `messageID` int NOT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `senderName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (3,'你好','bob','2024-07-20 14:50:17'),(3,'Hello','小智','2024-07-20 14:17:46'),(3,'a','bob','2024-07-22 10:47:15'),(3,'hello','bob','2024-07-22 10:47:51'),(4,'你好','bob','2024-07-22 15:24:24'),(4,'1111111','小茂','2024-07-23 08:33:30'),(3,'111111111','xiaozhi','2024-07-23 08:34:49'),(3,'22222','xiaozhi','2024-07-23 08:34:55'),(3,'adnfkulahfklhaiulhefiluahflkjdshblkhjfvabjh','xiaozhi','2024-07-23 08:35:09'),(3,'?','xiaozhi','2024-07-23 08:35:16'),(5,'nihao','xiaozhi','2024-07-23 08:38:26'),(3,'123','bob','2024-07-23 08:53:36'),(3,'123456','bob','2024-07-23 08:53:39'),(4,'你好你好','小茂','2024-07-23 08:54:24'),(4,'114514','小茂','2024-07-23 08:54:49'),(4,'刚才断连了','bob','2024-07-23 08:55:39'),(4,'oo','小茂','2024-07-23 08:55:57'),(4,'搜得死噶','小茂','2024-07-23 08:56:05'),(4,'你好','小茂','2024-07-24 08:49:39'),(4,'你好你好','bob','2024-07-24 08:49:45');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `own`
--

DROP TABLE IF EXISTS `own`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `own` (
  `propid` int NOT NULL,
  `ownerId` int NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`propid`,`ownerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `own`
--

LOCK TABLES `own` WRITE;
/*!40000 ALTER TABLE `own` DISABLE KEYS */;
INSERT INTO `own` VALUES (1,0,21),(2,0,1),(2,5,1);
/*!40000 ALTER TABLE `own` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon` (
  `Pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Pid` int NOT NULL,
  `type1` varchar(50) DEFAULT NULL,
  `type2` varchar(50) DEFAULT NULL,
  `introduction` varchar(10000) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Pname`) USING BTREE,
  UNIQUE KEY `Pid` (`Pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES ('三合一磁怪','发电厂',82,'电','钢',NULL,'pokemonpic/082.gif'),('三地鼠','洞穴',51,'地面','-',NULL,'pokemonpic/051.gif'),('九尾','火山',38,'火','-',NULL,'pokemonpic/038.gif'),('伊布','城市',133,'一般','-',NULL,'pokemonpic/133.gif'),('催眠貘','城市',96,'超能','-',NULL,'pokemonpic/096.gif'),('六尾','火山',37,'火','-',NULL,'pokemonpic/037.gif'),('凯罗斯','森林',127,'虫','-',NULL,'pokemonpic/127.gif'),('凯西','城市',63,'超能','-',NULL,'pokemonpic/063.gif'),('刺甲贝','海洋',91,'水','冰',NULL,'pokemonpic/091.gif'),('勇基拉','城市',64,'超能','-',NULL,'pokemonpic/064.gif'),('化石盔','海洋',140,'岩石','水',NULL,'pokemonpic/140.gif'),('化石翼龙','洞穴',142,'岩石','飞行',NULL,'pokemonpic/142.gif'),('卡咪龟','湖泊',8,'水','-','卡咪龟的身体呈较淡的靛蓝色，椭圆的头上长着一对类似翅膀的毛茸茸耳朵。它的眼睛呈褐色，在嘴部可看见它微小的獠牙，脸颊上有小块的深蓝色椭圆状斑纹。它的前肢有三个锋利的爪子，尾巴是毛茸茸的。卡咪龟作为宠物来说，活得比人类还久。据说它有着1万年的寿命，被视为长寿的象征。如果卡咪龟的壳上长着苔藓，代表它已经活了非常久了，甲壳上的伤痕是强者的证明。它长长的蓬松的尾巴是长寿的象征，颜色会随着年龄的增长而越变越深，在老人当中特别受欢迎。如果拍打它的头部，它会把头缩进壳里，但尾巴还是会露出来一点点。它会躲藏在水中猎取食物，当它游得很快的时候会灵巧地摆动自己毛茸茸的耳朵和尾巴，借此在水中保持平衡。','pokemonpic/008.gif'),('卡拉卡拉','山地',104,'地面','-',NULL,'pokemonpic/104.gif'),('卡比兽','城市',143,'一般','-',NULL,'pokemonpic/143.gif'),('卡蒂狗','城市',58,'火','-',NULL,'pokemonpic/058.gif'),('双弹瓦斯','发电厂',110,'毒','-',NULL,'pokemonpic/110.gif'),('口呆花','森林',70,'草','毒',NULL,'pokemonpic/070.gif'),('可达鸭','湖泊',54,'水','-',NULL,'pokemonpic/054.gif'),('吉利蛋','草原',113,'一般','-',NULL,'pokemonpic/113.gif'),('呆呆兽','湖泊',79,'水','超能',NULL,'pokemonpic/079.gif'),('呆壳兽','湖泊',80,'水','超能',NULL,'pokemonpic/080.gif'),('哈克龙','湖泊',148,'龙','-',NULL,'pokemonpic/148.gif'),('哥达鸭','湖泊',55,'水','-',NULL,'pokemonpic/055.gif'),('喇叭芽','森林',69,'草','毒',NULL,'pokemonpic/069.gif'),('喵喵','城市',52,'一般','-',NULL,'pokemonpic/052.gif'),('喷火龙','火山',6,'火','飞行','喷火龙是双足行走的具有西方龙特点的宝可梦。皮肤主要为橙色，颜色比火恐龙浅一些，从腹部至尾巴尖端以及脚底是奶油色的。它长着短而粗壮的下肢和相对细小的上肢，上肢有着三根锐利的爪子，同时脚上也长着三颗尖爪。喷火龙背后长有可以用来飞翔的巨大翅膀。翅膀的内侧呈蓝绿色，第三个关节处长着角状的突起。它有着长长的脖子，蓝色的小眼睛，后脑勺上长着两个角状突起，长而粗的尾巴末端燃烧着火焰。','pokemonpic/006.gif'),('嘎啦嘎啦','山地',105,'地面','-',NULL,'pokemonpic/105.gif'),('嘟嘟','草原',84,'一般','飞行',NULL,'pokemonpic/084.gif'),('嘟嘟利','草原',85,'一般','飞行',NULL,'pokemonpic/085.gif'),('地鼠','洞穴',50,'地面','-',NULL,'pokemonpic/050.gif'),('墨海马','海洋',116,'水','-',NULL,'pokemonpic/116.gif'),('多刺菊石兽','海洋',139,'岩石','水',NULL,'pokemonpic/139.gif'),('多边兽','城市',137,'一般','-',NULL,'pokemonpic/137.gif'),('大嘴蝠','山洞',42,'毒','飞行',NULL,'pokemonpic/042.gif'),('大嘴雀','草原',22,'一般','飞行',NULL,'pokemonpic/022.gif'),('大岩蛇','洞穴',95,'岩石','地面',NULL,'pokemonpic/095.gif'),('大比鸟','森林',18,'一般','飞行',NULL,'pokemonpic/018.gif'),('大舌头','草原',108,'一般','-',NULL,'pokemonpic/108.gif'),('大舌贝','海洋',90,'水','-',NULL,'pokemonpic/090.gif'),('大葱鸭','草原',83,'一般','飞行',NULL,'pokemonpic/083.gif'),('大针蜂','森林',15,'虫','毒',NULL,'pokemonpic/015.gif'),('大钳蟹','海洋',98,'水','-',NULL,'pokemonpic/098.gif'),('大食花','森林',71,'草','毒',NULL,'pokemonpic/071.gif'),('妙蛙种子','森林',1,'草','毒','妙蛙种子是一种四足的宝可梦，外表类似蟾蜍。它有着鲜红色的眼睛，白色的瞳孔和巩膜，头顶上长着一对凸起的耳朵。它的鼻子短而钝，且有一张大嘴。当它的嘴巴张开时，可以看到上颚的一对小而尖的牙齿。它的皮肤呈蓝绿色，并附有深绿色的斑纹。它每条粗腿的脚掌上都分出三趾，且末端都长有白色的小爪子。据说在它出生的时候，背上就种有一个会和它身体一同成长的神奇种子。妙蛙种子背上的种子里储存着营养，所以即使好几天不吃东西也可以活得好好的。在它出生后的一段时间内，它会吸收背上种子里储存着的营养成长。有时会看到它在太阳底下睡午觉的样子。在沐浴了充足的阳光后，它背上的种子就会茁壮成长。','pokemonpic/001.gif'),('妙蛙花','森林',3,'草','毒','妙蛙花是四足的爬行动物类宝可梦，体型比进化前要大的多。其皮肤呈蓝绿色，并且没有了进化前的深色斑块纹，取而代之的是类似蟾蜍背部疣粒的疙瘩。妙蛙花的头顶有一对耳朵，耳朵内部为红色，眼睛仍然呈橘红色，口腔里除进化前就有的上排两颗尖牙外，下排两侧又各长出两颗小尖牙。它背上的花蕾现在盛开成一朵巨大的花朵，由一根类似棕榈树的浅褐色树干状物支撑。花朵由六片红色梯形花瓣和黄色王冠形花蕊组成，花瓣前端有两个三角缺刻。每瓣花瓣上布有三个不规则形状的白色斑点，花蕊有11个尖角。与进化前类似，“树干”基部长出四片类似芭蕉叶的大叶子，呈十字分布，分别向头顶、身后和身体两侧伸展。爪分三趾，长有白色趾甲，腿也变得比妙蛙草更粗。雌性的花朵上有一颗种子。','pokemonpic/003.gif'),('妙蛙草','森林',2,'草','毒','妙蛙草是四足行走的宝可梦，外表类似蟾蜍。妙蛙草有着蓝绿色带有深绿色斑块的皮肤，它有红色的眼睛和黑色的瞳孔，头顶的耳朵有了黑色的内耳，上颚的一对尖牙稍长。妙蛙草背上的种子成长为了粉色的花苞，从花苞基部长出了四片形似芭蕉叶的绿叶，呈十字形张开，分别向头顶，身后和身体两侧伸展。妙蛙草沐浴在阳光下越久，身体内会涌出越多力量，背上的花苞也会渐渐成长。花蕾在它的背上成长，不断地吸取养分。据说长在背上的花苞在吸收足够的养分开始鼓起时就会飘出一股甜美的香气，这是大花即将盛开的前兆。它的腰腿会为了支撑背上的花苞而变强。如果待在太阳底下一动不动的时间变长了，就表示大花即将盛开。当背上的花苞长大之后，它似乎就无法再用两只脚站立起来了。','pokemonpic/002.gif'),('宝石海星','海洋',121,'水','超能',NULL,'pokemonpic/121.gif'),('小拉达','城市',19,'一般','-',NULL,'pokemonpic/019.gif'),('小拳石','山地',74,'岩石','地面',NULL,'pokemonpic/074.gif'),('小海狮','海洋',86,'水','-',NULL,'pokemonpic/086.gif'),('小火马','草原',77,'火','-',NULL,'pokemonpic/077.gif'),('小火龙','火山',4,'火','-','小火龙的身体呈橙色，肚子和尾巴下方是奶油色的，尾巴上有燃烧的火焰。它的眼睛呈蓝色，四颗小小的牙齿分立在上下颚。它四肢短小，有四根极短的手指和三根脚爪。小火龙生下来的时候，尾巴上就有火焰在燃烧。尾巴上的火焰是小火龙生命力的象征，如果精神不错，火焰就会熊熊燃烧。如果干劲不足，火焰就会变得微弱。火焰熄灭时，它的生命也会结束。尾巴上的火焰也代表着它的心情。当它开心时，火焰会摇曳晃动，而当它生气时，火焰就会剧烈燃烧。要是把它带到安静的地方，就能听到它的尾巴燃烧时发出的微小的声音。它天生喜欢热热的东西。据说当它被雨淋湿的时候，尾巴的末端会冒出烟来。如果它很健康，即使在全身湿透的情况下尾巴上的火焰也会十分旺盛地燃烧。','pokemonpic/004.gif'),('小磁怪','发电厂',81,'电','钢',NULL,'pokemonpic/081.gif'),('尼多兰','草原',29,'毒','-',NULL,'pokemonpic/029.gif'),('尼多力诺','草原',33,'毒','-',NULL,'pokemonpic/033.gif'),('尼多后','草原',31,'毒','地面',NULL,'pokemonpic/031.gif'),('尼多娜','草原',30,'毒','-',NULL,'pokemonpic/030.gif'),('尼多朗','草原',32,'毒','-',NULL,'pokemonpic/032.gif'),('尼多王','草原',34,'毒','地面',NULL,'pokemonpic/034.gif'),('巨钳蟹','海洋',99,'水','-',NULL,'pokemonpic/099.gif'),('巴大蝶','森林',12,'虫','飞行',NULL,'pokemonpic/012.gif'),('引梦貘人','城市',97,'超能','-',NULL,'pokemonpic/097.gif'),('快拳郎','道馆',107,'格斗','-',NULL,'pokemonpic/107.gif'),('快龙','湖泊',149,'龙','飞行',NULL,'pokemonpic/149.gif'),('急冻鸟','冰原',144,'冰','飞行',NULL,'pokemonpic/144.gif'),('怪力','山地',68,'格斗','-',NULL,'pokemonpic/068.gif'),('拉普拉斯','海洋',131,'水','冰',NULL,'pokemonpic/131.gif'),('拉达','城市',20,'一般','-',NULL,'pokemonpic/020.gif'),('摩鲁蛾','森林',49,'虫','毒',NULL,'pokemonpic/049.gif'),('暴鲤龙','湖泊',130,'水','飞行',NULL,'pokemonpic/130.gif'),('杰尼龟','湖泊',7,'水','-','杰尼龟有一双紫红色的大眼睛，四肢均为三趾，尾巴呈小型波浪状。杰尼龟的身体呈浅蓝色，被坚硬的龟壳包裹，龟壳背部为褐色，腹部为浅黄色，两者之间有着白色的波浪型边缘。杰尼龟出生后需要一段时间才会变硬的龟甲相当柔软，十分富有弹性，仿佛吹弹可破似的。即使用指头按压，放开后也能马上恢复原状。当它遇到危险的时候，会将四肢收回龟壳里保护自己。当它把长长的脖子缩回壳里时，会顺势发射出力道强劲的水枪，从嘴巴能喷出强力的泡沫。甲壳的作用不仅仅是用来保护自己，它圆润的外形和表面的沟槽会减小水的阻力，使它能快速地游动。它通过从水面喷水来捕食，会抓住对手的破绽，用喷水来反击。','pokemonpic/007.gif'),('梦幻','未知',151,'超能','-','梦幻外表像一只粉红色小猫，尾长约为身高的两倍，尾尖为橄榄形。蓝色的大眼睛呈三角形。梦幻是生活在南美的本应已经灭绝的宝可梦，智商非常高，什么都能记住。虽然被人们视为已经灭绝，但直到现在仍被称为幻之宝可梦。因为可以随心所欲地隐形，所以就算接近人类也完全不会被察觉。目击到这只幻之宝可梦的传闻接连不断，但见过其的身影的人就算在全国也没几个。似乎只有抱有纯洁的心灵和想与之见面的强烈感情，才能让梦幻现身。梦幻有着很高的智力，遗传基因中含有所有宝可梦的信息，能够使用一切的招式，所以有很多学者认为它是宝可梦的祖先。用显微镜可以看到它身上极短极细且密集的体毛。超梦正是通过重组了梦幻的遗传基因制造出来的宝可梦。','pokemonpic/151.gif'),('椰蛋树','森林',103,'草','超能',NULL,'pokemonpic/103.gif'),('毒刺水母','海洋',73,'水','毒',NULL,'pokemonpic/073.gif'),('比比鸟','森林',17,'一般','飞行',NULL,'pokemonpic/017.gif'),('毛球','森林',48,'虫','毒',NULL,'pokemonpic/048.gif'),('水伊布','湖泊',134,'水','-',NULL,'pokemonpic/134.gif'),('水箭龟','湖泊',9,'水','-','水箭龟是一种以两足行走为主，体型巨大的龟形宝可梦，它有时也会用四足行走。水箭龟身体呈深蓝色。它的前后肢短而粗壮，因关节而分成一节一节的，并且都长有三趾。它的尾巴不再像海浪，而变成了粗短的蓝色尾巴。它的下颚是淡黄色的，耳朵是三角形的。它的躯干覆盖着巨大而厚重的龟壳，龟壳背部为褐色，腹部淡黄色，之间有白色的边缘。在龟壳的前端伸出了两门水炮，这两门水炮可以缩进龟壳里。','pokemonpic/009.gif'),('波波','森林',16,'一般','飞行',NULL,'pokemonpic/016.gif'),('派拉斯','森林',46,'虫','草',NULL,'pokemonpic/046.gif'),('派拉斯特','森林',47,'虫','草',NULL,'pokemonpic/047.gif'),('海刺龙','海洋',117,'水','-',NULL,'pokemonpic/117.gif'),('海星星','海洋',120,'水','-',NULL,'pokemonpic/120.gif'),('火伊布','火山',136,'火','-',NULL,'pokemonpic/136.gif'),('火恐龙','火山',5,'火','-','火恐龙的头顶长出了一个角状凸起，嘴巴变尖，有点类似鸟喙。它的四肢和尾巴较长，尾巴末端依然燃烧着火焰。火恐龙性格十分粗暴，火爆的性格致使它一直在寻找战斗的对手，总是静不下来。它会先挥舞尾巴扫倒对手，然后再用利爪将其撕成碎片。在与强敌战斗的过程中，如果情绪变得兴奋起来，尾巴上会燃起蓝白色的火焰。如果挥舞燃烧着的尾巴，就能令周围温度不断上升，从而让对手陷入痛苦。它有时也会喷出青白色的烈火，把周围的东西烧得一干二净。如果在夜晚抬头仰望栖息着火恐龙的岩山，尾巴上的火焰看起来就像夜空的星星。','pokemonpic/005.gif'),('火暴猴','山地',57,'格斗','-',NULL,'pokemonpic/057.gif'),('火焰鸟','火山',146,'火','飞行',NULL,'pokemonpic/146.gif'),('烈焰马','草原',78,'火','-',NULL,'pokemonpic/078.gif'),('烈雀','草原',21,'一般','飞行',NULL,'pokemonpic/021.gif'),('独角犀牛','山地',111,'地面','岩石',NULL,'pokemonpic/111.gif'),('独角虫','森林',13,'虫','毒',NULL,'pokemonpic/013.gif'),('猫老大','城市',53,'一般','-',NULL,'pokemonpic/053.gif'),('猴怪','山地',56,'格斗','-',NULL,'pokemonpic/056.gif'),('玛瑙水母','海洋',72,'水','毒',NULL,'pokemonpic/072.gif'),('瓦斯弹','发电厂',109,'毒','-',NULL,'pokemonpic/109.gif'),('电击兽','发电厂',125,'电','-',NULL,'pokemonpic/125.gif'),('白海狮','海洋',87,'水','冰',NULL,'pokemonpic/087.gif'),('百变怪','城市',132,'一般','-',NULL,'pokemonpic/132.gif'),('皮卡丘','森林',25,'电','-','皮卡丘是一只电气鼠，全身的皮毛都是黄色的，背上有两条褐色的条纹，尾巴是像锯齿状的闪电，与身体相接的部分也有一片褐色的皮毛。它有小小的嘴巴，以及黑色的眼睛，脸颊上有着红色的电力袋，长长的耳朵尖端是黑色的。它的前爪短而粗，有五趾，后爪则只有三趾。越是能制造出强大电流的皮卡丘，脸颊上的囊就越柔软，同时也越有伸展性。由于它脸颊上的袋子中储存了电能，触摸了之后会觉得麻麻的。它跑动的时候是用四条腿着地快速地奔跑，但是更多时候它是站立着的并用两只后脚走路。雌性皮卡丘的尾巴末端为心形，而雄性的则没有缺口。不同地区的皮卡丘在皮毛光泽上有细微差异。关都地区的皮卡丘在阳光下有星星点点的细小光斑，而阿罗拉地区的皮卡丘则皮毛润泽。','pokemonpic/025.gif'),('皮可西','山洞',36,'一般','-',NULL,'pokemonpic/036.gif'),('皮皮','山洞',35,'一般','-',NULL,'pokemonpic/035.gif'),('穿山王','沙漠',28,'地面','-',NULL,'pokemonpic/028.gif'),('穿山鼠','沙漠',27,'地面','-',NULL,'pokemonpic/027.gif'),('绿毛虫','森林',10,'虫','-','绿毛虫是毛毛虫外表的宝可梦,背面是绿色，腹部是白色。它的头部有Y字形的红色触角，尾巴是黄色纺锤状的。它有两对腹足，上面有吸盘，身体两侧有黄色环形斑纹。绿毛虫外表覆著绿色的皮肤，为了伪装自己而潜入到与自己身体颜色相同的植物中去。别看它的脚很短，因为是吸盘，所以无论是斜坡还是墙壁都能轻松前进。它藉著蜕皮的过程不断成长，最后用丝缠住自己，变化成蛹。或许是想要尽快长大，它的食欲非常旺盛，会拼命地爬上树去吃叶子，每天能吃掉１００片叶子。它的身体软绵绵的，也没什么力气。在自然界，它的命运就是作为猎物而被不断捕食。被鸟宝可梦袭击时会从头部的触角中释放出臭气抵抗，以此赶走敌人，但也经常会成为鸟食。如果你碰到了它头上的触角，它就会分泌出难闻的气味来保护自己。它易于捕捉，成长也快。是推荐给新手训练家作为同伴的宝可梦之一。','pokemonpic/010.gif'),('耿鬼','塔楼',94,'幽灵','毒',NULL,'pokemonpic/094.gif'),('肯泰罗','草原',128,'一般','-',NULL,'pokemonpic/128.gif'),('胖丁','草原',39,'一般','妖精',NULL,'pokemonpic/039.gif'),('胖可丁','草原',40,'一般','妖精',NULL,'pokemonpic/040.gif'),('胡地','城市',65,'超能','-',NULL,'pokemonpic/065.gif'),('腕力','山地',66,'格斗','-',NULL,'pokemonpic/066.gif'),('臭泥','沼泽',88,'毒','-',NULL,'pokemonpic/088.gif'),('臭臭泥','沼泽',89,'毒','-',NULL,'pokemonpic/089.gif'),('臭臭花','森林',44,'草','毒',NULL,'pokemonpic/044.gif'),('菊石兽','海洋',138,'岩石','水',NULL,'pokemonpic/138.gif'),('蔓藤怪','森林',114,'草','-',NULL,'pokemonpic/114.gif'),('蚊香君','湖泊',61,'水','-',NULL,'pokemonpic/061.gif'),('蚊香泳士','湖泊',62,'水','格斗',NULL,'pokemonpic/062.gif'),('蚊香蝌蚪','湖泊',60,'水','-',NULL,'pokemonpic/060.gif'),('蛋蛋','森林',102,'草','超能',NULL,'pokemonpic/102.gif'),('袋兽','草原',115,'一般','-',NULL,'pokemonpic/115.gif'),('角金鱼','湖泊',118,'水','-',NULL,'pokemonpic/118.gif'),('豪力','山地',67,'格斗','-',NULL,'pokemonpic/067.gif'),('走路草','森林',43,'草','毒',NULL,'pokemonpic/043.gif'),('超梦','洞穴',150,'超能','-',NULL,'pokemonpic/150.gif'),('超音蝠','山洞',41,'毒','飞行',NULL,'pokemonpic/041.gif'),('迷你龙','湖泊',147,'龙','-',NULL,'pokemonpic/147.gif'),('迷唇姐','冰原',124,'冰','超能',NULL,'pokemonpic/124.gif'),('金鱼王','湖泊',119,'水','-',NULL,'pokemonpic/119.gif'),('钻角犀兽','山地',112,'地面','岩石',NULL,'pokemonpic/112.gif'),('铁壳蛹','森林',14,'虫','毒',NULL,'pokemonpic/014.gif'),('铁甲蛹','森林',11,'虫','-',NULL,'pokemonpic/011.gif'),('镰刀盔','海洋',141,'岩石','水',NULL,'pokemonpic/141.gif'),('闪电鸟','发电厂',145,'电','飞行',NULL,'pokemonpic/145.gif'),('阿柏怪','沼泽',24,'毒','-',NULL,'pokemonpic/024.gif'),('阿柏蛇','沼泽',23,'毒','-',NULL,'pokemonpic/023.gif'),('隆隆岩','山地',76,'岩石','地面',NULL,'pokemonpic/076.gif'),('隆隆石','山地',75,'岩石','地面',NULL,'pokemonpic/075.gif'),('雷丘','森林',26,'电','-',NULL,'pokemonpic/026.gif'),('雷伊布','森林',135,'电','-',NULL,'pokemonpic/135.gif'),('霸王花','森林',45,'草','毒',NULL,'pokemonpic/045.gif'),('霹雳电球','发电厂',100,'电','-',NULL,'pokemonpic/100.gif'),('顽皮雷弹','发电厂',101,'电','-',NULL,'pokemonpic/101.gif'),('风速狗','城市',59,'火','-',NULL,'pokemonpic/059.gif'),('飞天螳螂','森林',123,'虫','飞行',NULL,'pokemonpic/123.gif'),('飞腿郎','道馆',106,'格斗','-',NULL,'pokemonpic/106.gif'),('鬼斯','塔楼',92,'幽灵','毒',NULL,'pokemonpic/092.gif'),('鬼斯通','塔楼',93,'幽灵','毒',NULL,'pokemonpic/093.gif'),('魔墙人偶','城市',122,'超能','妖精',NULL,'pokemonpic/122.gif'),('鲤鱼王','湖泊',129,'水','-',NULL,'pokemonpic/129.gif'),('鸭嘴火兽','火山',126,'火','-',NULL,'pokemonpic/126.gif');
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `userID` int NOT NULL,
  `friendsID` int NOT NULL,
  `status` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`userID`,`friendsID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (0,1,1,'2024-07-20 14:04:21'),(0,2,1,'2024-07-20 13:02:49'),(0,5,1,'2024-07-22 15:24:14'),(1,0,1,'2024-07-20 14:04:21'),(1,2,1,'2024-07-23 08:38:06'),(1,5,1,'2024-07-23 21:51:45'),(2,0,1,'2024-07-20 13:02:49'),(2,1,1,'2024-07-23 08:38:06'),(2,5,1,'2024-07-23 21:52:07'),(3,0,0,'2024-07-25 14:12:31'),(3,5,1,'2024-07-23 21:52:37'),(4,0,0,'2024-07-29 14:12:40'),(4,5,1,'2024-07-23 21:52:24'),(5,0,1,'2024-07-22 15:24:14'),(5,1,1,'2024-07-23 21:51:45'),(5,2,1,'2024-07-23 21:52:07'),(5,3,1,'2024-07-23 21:52:37'),(5,4,1,'2024-07-23 21:52:24'),(5,6,1,'2024-07-23 21:52:53'),(5,9,0,'2024-07-23 21:23:08'),(5,10,0,'2024-07-23 21:23:12'),(6,5,1,'2024-07-23 21:52:53');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp`
--

DROP TABLE IF EXISTS `tp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tp` (
  `flag` int NOT NULL,
  `Tid` int NOT NULL,
  `Pid` int NOT NULL,
  `statement` tinyint(1) NOT NULL,
  `lev` int NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`flag`) USING BTREE,
  KEY `Tid` (`Tid`) USING BTREE,
  KEY `Pid` (`Pid`) USING BTREE,
  CONSTRAINT `tp_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `trainer` (`Tid`),
  CONSTRAINT `tp_ibfk_2` FOREIGN KEY (`Pid`) REFERENCES `pokemon` (`Pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp`
--

LOCK TABLES `tp` WRITE;
/*!40000 ALTER TABLE `tp` DISABLE KEYS */;
INSERT INTO `tp` VALUES (2,1,2,1,13,'♀'),(4,5,1,0,15,'♂'),(5,1,2,2,15,'♂'),(6,0,2,1,15,'♂'),(7,0,2,0,15,'♀'),(10,4,2,1,40,'♀'),(12,5,12,0,15,'♂'),(13,1,1,2,5,'♂'),(14,5,55,0,55,'♂'),(16,1,99,0,55,'♂'),(17,1,2,2,5,'♂'),(18,5,15,0,5,'♀'),(19,5,22,2,15,'♀'),(20,5,5,0,55,'♀'),(21,1,25,1,55,'♀'),(22,1,11,1,15,'♀'),(25,1,9,2,15,'♀'),(26,1,11,2,5,'♂'),(27,5,150,2,5,'♂'),(30,9,147,2,5,'♂'),(31,1,149,2,55,'♀'),(32,9,114,1,99,'-'),(33,1,1,2,99,'-'),(34,5,5,0,5,'♂'),(35,5,111,0,25,'♀'),(36,5,120,0,25,'♂'),(37,5,124,0,25,'♂'),(38,5,125,1,25,'♂'),(39,5,126,0,25,'♂'),(40,5,128,0,25,'♂'),(41,20,114,1,50,'♂'),(42,5,150,0,100,'♂'),(43,5,5,0,85,'♂'),(44,5,150,0,85,'♂'),(45,5,149,0,85,'♂'),(46,5,148,0,84,'♂'),(47,0,5,2,25,'♂');
/*!40000 ALTER TABLE `tp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `propid` int NOT NULL,
  `ownerId` int NOT NULL,
  `price` double NOT NULL,
  `special` varchar(255) DEFAULT NULL,
  `num` int NOT NULL,
  `propName` varchar(255) DEFAULT NULL,
  `ownerName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (1,2,2,'false',1,'神奇果实','xiaozhi'),(1,1,1,'false',3,'神奇果实','bob');
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `Tname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int unsigned NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `TrainerPic` varchar(255) DEFAULT NULL,
  `Tid` int NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `points` int DEFAULT NULL,
  PRIMARY KEY (`Tid`),
  UNIQUE KEY `new_tid` (`Tid`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES ('bob',16,'男','123456','trainer/0.png',0,'Europe',0),('xiaozhi',18,'男','123456','trainer/1.png',1,'Australia',65),('alice',20,'女','123456','trainer/2.png',2,'Australia',117),('james',20,'男','123456','trainer/3.png',3,'South America',12),('jane',14,'女','123456','trainer/4.png',4,'Europe',3),('小茂',15,'男','123456','trainer/5.png',5,'Antarctica',3),('小霞',12,'女','123456','trainer/6.png',6,'South America',75),('Somnia1337',20,'Male','123456',NULL,7,'Antarctica',4),('1111',11,'1','111111',NULL,8,'South America',6),('陈冥雁',18,'武装直升机','password',NULL,9,'Antarctica',83),('孙笑川',24,'other','sunxiaochuan',NULL,10,'Europe',56),('孙笑川1',24,'无','114514',NULL,11,'Africa',65),('测试员',24,'male','111','D:\\学习\\学习内容\\jslearning\\pokemonhome\\PokemonDB\\trainer\\12.jpg',12,'North America',95),('测试员2',24,'female','123456','D:\\学习\\学习内容\\jslearning\\pokemonhome\\PokemonDB\\trainer\\13.jpg',13,'Antarctica',32),('测试员4',24,'male','123456','trainer\\14.png',14,'Africa',97),('测试员5',24,'male','123456','trainer\\15.png',15,'South America',61),('水王',18,'male','123456','trainer\\16.png',16,'Asia',91),('测试员6',25,'other','123456','trainer\\17.png',17,'Antarctica',66),('乔伊',25,'female','123456','trainer\\18.png',18,'Asia',51),('乔一',25,'female','123456','trainer\\19.png',19,'Africa',89),('我是LJL',114514,'other','123456','trainer\\20.png',20,'Antarctica',95),('试',15,'male','123456','/trainer/奇树.jpg',36,'Antarctica',0),('11',11,'male','123456','/trainer/37.png',37,'Asia',0),('12',22,'male','123456','/trainer/38.png',38,'Antarctica',0),('13',12,'male','123456','/trainer/39.png',39,'Asia',0),('成龙',45,'male','123456','/trainer/40.png',40,'Africa',0),('马克思',15,'male','123456','trainer\\41.png',41,'South America',0),('恩格斯',55,'male','123456','trainer/42.png',42,'South America',0),('mmm',16,'male','123456','trainer/43.png',43,'Africa',0),('毕升',22,'male','123456','trainer/44.png',44,'Africa',0),('毕阳得',22,'male','123456','trainer/45.png',45,'Asia',0),('猴子',666,'male','123456','trainer/46.png',46,'Africa',0),('大洲',15,'male','123456','trainer/47.png',47,'Europe',0),('小美',12,'female','123456','trainer/48.png',48,'Asia',0);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-24 14:11:34
