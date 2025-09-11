![R](Rinte.png#pic_center)



[![Generic badge](https://img.shields.io/badge/Java-17-<COLOR>.svg)](https://shields.io/)[![Generic badge](https://img.shields.io/badge/BurpSuite-Extensions-<COLOR>.svg)](https://shields.io/)![Generic badge](https://img.shields.io/badge/Framework-Detection-<COLOR>.svg)[![Generic badge](https://img.shields.io/badge/SupportPOC-1000+-<COLOR>.svg)](https://shields.io/)![GitHub release](https://img.shields.io/github/release/Naereen/StrapDown.js.svg)[![GitHub stars](https://img.shields.io/github/stars/Naereen/StrapDown.js.svg?style=social&label=Star&maxAge=000000)](https://GitHub.com/Naereen/StrapDown.js/stargazers/)

## 📖 简介

**Rinte** 是一款专为渗透测试人员设计的 Burp Suite 插件，提供强大的自动化安全扫描功能。该插件集成了框架检测、漏洞扫描和敏感路径扫描等多种功能，帮助安全研究人员快速识别目标系统的安全漏洞。支持1000+框架POC、支持动态加载POC、指定框架扫描。

## 🔄 核心功能

- 智能框架识别：基于HTTP响应特征自动识别Web应用程序框架，支持1000+POC检测
- 指定POC加载：支持快速加载任意POC进行漏洞测试
- 指定框架扫描：支持选择特定框架进行针对性漏洞扫描
- 多线程并发扫描：支持高效的并发扫描，提升检测速度和覆盖范围
- 灵活的配置管理：支持自定义扫描规则、黑白名单配置、线程数调节等个性化设置
- 实时检测日志：提供详细的扫描结果记录和漏洞管理面板
- DNSLog支持：集成CEYE平台，支持反连检测和盲注验证
- 智能流量过滤：基于MIME类型黑名单机制，精准过滤扫描目标
- ······

## 📋 支持的CMS

|  |  |  |
|:-:|:-:|:-:|
| 泛微 E-Office | 海康威视 IVMS | 宝塔面板 |
| 泛微 E-Cology | 海康威视综合安防管理平台 | 禅道项目管理系统 |
| 泛微云桥 e-Bridge | 海康威视安全接入网关 | 帆软报表 |
| 用友 U8 Cloud | 深信服应用交付管理系统 | 润乾报表系统 |
| 用友 NC Cloud | 深信服终端检测响应平台 | 通达 OA |
| 若依管理系统 | 致远 OA | 广联达 OA |
| 红帆 iOffice | 易宝 OA | 宏景 HCM |
| 昂捷 ERP/CRM | 飞企互联办公系统 | 智慧校园管理系统 |
| 正方数字化校园平台 | 时空智友企业流程化管控系统 | 大华智慧园区综合管理平台 |
| 大华 ICC智能物联综合管理平台 | 奥威亚视频云平台 | Apache Struts2 |
| ...... | ...... | ...... |

## 🚀 使用

配置 `Rinte_config.json` 文件，放在插件同一目录下即可

## 后续功能正在优化中
