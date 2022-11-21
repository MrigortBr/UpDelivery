-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: updelivery
-- ------------------------------------------------------
-- Server version	5.7.36

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
-- Table structure for table `endereco_fornecedor`
--

DROP TABLE IF EXISTS `endereco_fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco_fornecedor` (
  `nome` varchar(60) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `rua` varchar(60) NOT NULL,
  `numero` varchar(5) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` tinytext NOT NULL,
  `tipo` varchar(30) NOT NULL,
  KEY `id_fornecedor` (`id_fornecedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_fornecedor`
--

LOCK TABLES `endereco_fornecedor` WRITE;
/*!40000 ALTER TABLE `endereco_fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco_fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco_pessoa`
--

DROP TABLE IF EXISTS `endereco_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco_pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `rua` varchar(60) NOT NULL,
  `bairro` varchar(30) DEFAULT NULL,
  `cidade` varchar(30) DEFAULT NULL,
  `numero` varchar(5) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` tinytext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_pessoa`
--

LOCK TABLES `endereco_pessoa` WRITE;
/*!40000 ALTER TABLE `endereco_pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco_pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `tipoEntrega` enum('Entrega Fixa','Entrega Por Km') NOT NULL,
  `preco_entrega` float NOT NULL,
  `login` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `tipo` varchar(60) NOT NULL,
  `avaliacao` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_fornecedor`
--

DROP TABLE IF EXISTS `login_fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_fornecedor` (
  `id_fornecedor` int(11) NOT NULL,
  `token` varchar(20) NOT NULL,
  `token_secundario` varchar(20) NOT NULL,
  `data_criacao` date NOT NULL,
  `data_validade` date NOT NULL,
  `working` tinyint(1) NOT NULL DEFAULT '0',
  KEY `id_fornecedor` (`id_fornecedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_fornecedor`
--

LOCK TABLES `login_fornecedor` WRITE;
/*!40000 ALTER TABLE `login_fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_usuario`
--

DROP TABLE IF EXISTS `login_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_usuario` (
  `id_usuario` int(11) NOT NULL,
  `token` varchar(20) NOT NULL,
  `token_secundario` varchar(20) NOT NULL,
  `data_criacao` date NOT NULL,
  `data_validade` date NOT NULL,
  `working` tinyint(1) DEFAULT NULL,
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_usuario`
--

LOCK TABLES `login_usuario` WRITE;
/*!40000 ALTER TABLE `login_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_fornecedor`
--

DROP TABLE IF EXISTS `pagamento_fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento_fornecedor` (
  `id_fornecedor` int(11) NOT NULL,
  `tipo` enum('debito','credito','Alimentacao','Refeicao','DebitoeCredito') DEFAULT NULL,
  `bandeira` enum('Mastercard','Visa','Elo','American Express','Dinners Club','Hipercard') NOT NULL,
  `cartao` enum('Alelo','Santander','Bradesco','Banco do Brasil','NuBank','Caixa','Inter') NOT NULL,
  KEY `id_fornecedor` (`id_fornecedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_fornecedor`
--

LOCK TABLES `pagamento_fornecedor` WRITE;
/*!40000 ALTER TABLE `pagamento_fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento_fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_usuario`
--

DROP TABLE IF EXISTS `pagamento_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento_usuario` (
  `id_usuario` int(11) NOT NULL,
  `apelido` varchar(30) NOT NULL,
  `cartao` varchar(30) NOT NULL,
  `cvv` varchar(4) NOT NULL,
  `validade` varchar(5) NOT NULL,
  `tipo` enum('debito','credito','Alimentacao','Refeicao','DebitoeCredito') DEFAULT NULL,
  `bandeira` varchar(40) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_usuario`
--

LOCK TABLES `pagamento_usuario` WRITE;
/*!40000 ALTER TABLE `pagamento_usuario` DISABLE KEYS */;
INSERT INTO `pagamento_usuario` VALUES (1,'Novo Cartão','5375298298098803','524','01/23','debito','Mastercard',1);
/*!40000 ALTER TABLE `pagamento_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `cpf` varchar(13) NOT NULL,
  `telefone` varchar(13) NOT NULL,
  `data_nascimento` date NOT NULL,
  `senha` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_fornecedor` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `descricao` tinytext,
  `preco` float NOT NULL,
  `desconto` float NOT NULL DEFAULT '0',
  `tipo` varchar(30) NOT NULL,
  `estoque` int(11) NOT NULL DEFAULT '-1',
  `curtidas` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_fornecedor` (`id_fornecedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `data_compra` date NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `preco` float NOT NULL,
  `desconto` float NOT NULL,
  `preco_final` float NOT NULL,
  `quantidade` int(11) NOT NULL,
  `tipo_pagamento` enum('Debito','Credito','Alimentacao','Refeicao','Desconhecido') DEFAULT NULL,
  `cartao` varchar(30) NOT NULL,
  `estado` enum('Pedido Entregue','Pedido em transporte','Pedido Recebido') DEFAULT NULL,
  KEY `id_usuario` (`id_usuario`),
  KEY `id_produto` (`id_produto`),
  KEY `id_fornecedor` (`id_fornecedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 18:52:04
