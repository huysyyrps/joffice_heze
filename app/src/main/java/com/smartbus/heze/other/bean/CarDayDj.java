package com.smartbus.heze.other.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/2/15 0015.
 */

public class CarDayDj implements Serializable {

    /**
     * success : true
     * totalCounts : 155
     * result : [{"depName":"广告设施维护科","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6707","driverName":"杨红","examiner":"杨红","projectName":"","note":""},{"depName":"广告设施维护科","kaoheDate":"2020-02-15","lineCode":"13","carNo":"鲁rn1337","driverName":"闫福义","examiner":"闫福义","projectName":"","note":""},{"depName":"广告设施维护科","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁R09367D","driverName":"张同福","examiner":"张同福","projectName":"","note":""},{"depName":"广告设施维护科","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁RG7167","driverName":"张修平","examiner":"张修平","projectName":"","note":""},{"depName":"广告设施维护科","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09232D","driverName":"杨文军","examiner":"杨文军","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"15","carNo":"鲁R08503D","driverName":"刘宗河","examiner":"刘宗河","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"15","carNo":"鲁R09395D","driverName":"林凯","examiner":"林凯","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"15","carNo":"鲁R08195D","driverName":"刘增","examiner":"刘增","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁R09261D","driverName":"房晋东","examiner":"房晋东","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁R08329D","driverName":"王德帅","examiner":"王德帅","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁R09291D","driverName":"张修平","examiner":"张修平","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁RG1511","driverName":"张修平","examiner":"张修平","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"2","carNo":"鲁RG3157","driverName":"张修平","examiner":"张修平","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"2","carNo":"鲁R09273D","driverName":"张贵密","examiner":"2876","projectName":"","note":"正常"},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"2","carNo":"鲁R09273D","driverName":"张贵密","examiner":"2876","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"2","carNo":"鲁R08361D","driverName":"王东","examiner":"王东","projectName":"","note":"正常\n"},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"2","carNo":"鲁R09329D","driverName":"朱爱青","examiner":"朱爱青","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"22","carNo":"鲁RG0311","driverName":"何兆祥","examiner":"何兆祥","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"22","carNo":"鲁RG0313","driverName":"刘景亮","examiner":"刘景亮","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"22","carNo":"鲁RG0323","driverName":"田崇华","examiner":"田崇华","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"22","carNo":"鲁RG0312","driverName":"曾庆勇","examiner":"曾庆勇","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"28","carNo":"鲁RG6790","driverName":"王冬峰","examiner":"王冬峰","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"28","carNo":"鲁RG6732","driverName":"张辉","examiner":"张辉","projectName":"","note":"右后轮气压偏低\n"},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"28","carNo":"鲁RG6720","driverName":"赵勇02","examiner":"赵勇02","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"28","carNo":"鲁RG6790","driverName":"赵勇02","examiner":"赵勇02","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"28路","carNo":"鲁RG6507","driverName":"刘卫国","examiner":"刘卫国","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"48","carNo":"鲁R08651D","driverName":"王雪连","examiner":"王雪连","projectName":"","note":"正常"},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"50","carNo":"鲁RG0200","driverName":"陈泽义","examiner":"陈泽义","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"50","carNo":"鲁R08131D","driverName":"栗洪朋","examiner":"栗洪朋","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"50","carNo":"鲁RG0207","driverName":"张永生01","examiner":"张永生01","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"56","carNo":"鲁RG7525","driverName":"吴灿建","examiner":"吴灿建","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"56","carNo":"鲁RG7507","driverName":"李清蕊","examiner":"李清蕊","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"56","carNo":"鲁RG7537","driverName":"穆俊丽","examiner":"穆俊丽","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"56","carNo":"鲁RG7525","driverName":"吴灿建","examiner":"吴灿建","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"56路","carNo":"鲁RG7511","driverName":"李清蕊","examiner":"李清蕊","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"66","carNo":"鲁RG0597","driverName":"李文海","examiner":"李文海","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7625","driverName":"李建玉","examiner":"李建玉","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7583","driverName":"邱全粉","examiner":"邱全粉","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7591","driverName":"王燕梅","examiner":"王燕梅","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7572","driverName":"朱芬芬","examiner":"朱芬芬","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"7","carNo":"鲁RG7651","driverName":"董素彦","examiner":"董素彦","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"7","carNo":"鲁RG7625","driverName":"李建玉","examiner":"李建玉","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"7","carNo":"鲁RG7583","driverName":"吴春丽","examiner":"吴春丽","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0731","driverName":"黄国庆","examiner":"黄国庆","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RN0730","driverName":"王建国02","examiner":"王建国02","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"10","carNo":"鲁RG7287","driverName":"韩允强","examiner":"韩允强","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"10","carNo":"鲁RG7103","driverName":"王军","examiner":"王军","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"10","carNo":"鲁RN2116","driverName":"王体青","examiner":"王体青","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"10","carNo":"鲁RG7061","driverName":"肖建民","examiner":"肖建民","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"102","carNo":"鲁RG7125","driverName":"陈启腾","examiner":"陈启腾","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"102","carNo":"鲁RG7102","driverName":"邓胜","examiner":"邓胜","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"102","carNo":"鲁RG7081","driverName":"陈启腾","examiner":"陈启腾","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"102","carNo":"鲁RG7125","driverName":"陈启腾","examiner":"陈启腾","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"102","carNo":"鲁RG7012","driverName":"邓胜","examiner":"邓胜","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"103","carNo":"鲁RN0783","driverName":"庞亚宁","examiner":"庞亚宁","projectName":"","note":"\n"},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"103","carNo":"鲁RG7031","driverName":"谷瑞斋","examiner":"谷瑞斋","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"105","carNo":"鲁RG7053","driverName":"2311","examiner":"王燕01","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"105","carNo":"鲁RG7200","driverName":"韩红霞","examiner":"韩红霞","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"105","carNo":"鲁RG7065","driverName":"张静","examiner":"张静","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"13","carNo":"鲁RN2170","driverName":"程相文","examiner":"程相文","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"13","carNo":"鲁RN8712","driverName":"乔增鹏","examiner":"乔增鹏","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"14","carNo":"鲁R03115D","driverName":"李成良","examiner":"李成良","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"14","carNo":"鲁R03155D","driverName":"张呈虎","examiner":"张呈虎","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"14","carNo":"鲁R08510D","driverName":"周建","examiner":"周建","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"20","carNo":"鲁RG7082","driverName":"韩科成","examiner":"韩科成","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"20","carNo":"鲁RG7190","driverName":"赵庆杰","examiner":"赵庆杰","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09270D","driverName":"贺坤鹏","examiner":"贺坤鹏","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09205D","driverName":"胡青青","examiner":"胡青青","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09207D","driverName":"李尚华","examiner":"李尚华","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09237D","driverName":"徐良喜","examiner":"徐良喜","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09217D","driverName":"杨国强","examiner":"杨国强","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09212D","driverName":"杨家","examiner":"杨家","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-14","lineCode":"37","carNo":"鲁R09212D","driverName":"杨家","examiner":"杨家","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"37","carNo":"鲁R09263D","driverName":"李太华","examiner":"李太华","projectName":"","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"37","carNo":"鲁R08551D","driverName":"任松强","examiner":"任松强","projectName":"","note":"当日里程：46134"},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"37","carNo":"鲁R09205D","driverName":"任松强","examiner":"任松强","projectName":"","note":"当日里程：41249"},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"37","carNo":"鲁R09212D","driverName":"杨家","examiner":"杨家","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0737","driverName":"李昌伟","examiner":"李昌伟","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0715","driverName":"李海雷","examiner":"李海雷","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0761","driverName":"李作要","examiner":"李作要","projectName":"","note":"A"},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0705","driverName":"刘胜利","examiner":"刘胜利","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0702","driverName":"马继锋","examiner":"马继锋","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0732","driverName":"张庆杰","examiner":"张庆杰","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁RG0730","driverName":"张宪书","examiner":"张宪书","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"1","carNo":"鲁RG0715","driverName":"李海雷","examiner":"李海雷","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"1","carNo":"鲁RG0730","driverName":"吴振","examiner":"吴振","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"101","carNo":"鲁RN2161","driverName":"樊庆林","examiner":"樊庆林","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"101","carNo":"鲁RN1397","driverName":"李银明","examiner":"李银明","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"101","carNo":"鲁RN1385","driverName":"袁涛","examiner":"袁涛","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"101","carNo":"鲁RN8733","driverName":"孙翠娜","examiner":"孙翠娜","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"101","carNo":"鲁RN1385","driverName":"袁涛","examiner":"袁涛","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6317","driverName":"2760","examiner":"2760","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6305","driverName":"陈红杰","examiner":"陈红杰","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6723","driverName":"李美玲","examiner":"李美玲","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6735","driverName":"李作焕","examiner":"李作焕","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6330","driverName":"乔俊敏","examiner":"乔俊敏","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6317","driverName":"2760","examiner":"赵杰","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG6317","driverName":"赵杰","examiner":"赵杰","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"11","carNo":"鲁RG6305","driverName":"陈红杰","examiner":"陈红杰","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11路","carNo":"鲁RG6710","driverName":"2810","examiner":"2810","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11路","carNo":"鲁RG6710","driverName":"2810 段瑞青","examiner":"2810 段瑞青","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"11路 段瑞青","carNo":"鲁RG6710","driverName":"2810 段瑞青 ","examiner":"2810 段瑞青","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"17","carNo":"鲁RG0184","driverName":"高胜浩","examiner":"高胜浩","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"17","carNo":"鲁RG7730","driverName":"夏清祥","examiner":"夏清祥","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"18","carNo":"鲁RG6387","driverName":"2007","examiner":"2007","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"18","carNo":"鲁R09351D","driverName":"孟德胜","examiner":"孟德胜","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"18","carNo":"鲁R09373D","driverName":"王新宪","examiner":"王新宪","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"18","carNo":"鲁R09375D","driverName":"吴海顺","examiner":"吴海顺","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"30","carNo":"鲁RG6501","driverName":"伏思祥","examiner":"伏思祥","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"30","carNo":"鲁RG6307","driverName":"侯强","examiner":"侯强","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"30","carNo":"鲁RG6725","driverName":"李广","examiner":"李广","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"30","carNo":"鲁RG6795","driverName":"杨素云","examiner":"杨素云","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"30","carNo":"鲁RG6307","driverName":"侯强","examiner":"侯强","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"32","carNo":"鲁RG0672","driverName":"谷传银","examiner":"谷传银","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"32","carNo":"鲁RG0675","driverName":"谷传银","examiner":"谷传银","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"32","carNo":"鲁RG0675","driverName":"孙灵锋","examiner":"孙灵锋","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"33","carNo":"鲁RG6572","driverName":"侯焕立","examiner":"侯焕立","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"33","carNo":"鲁RG6793","driverName":"祝会玲","examiner":"祝会玲","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"4","carNo":"鲁RG7631","driverName":"赵保林","examiner":"赵保林","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"4","carNo":"鲁RN2115","driverName":"刘现生","examiner":"刘现生","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-15","lineCode":"4","carNo":"鲁RN1375","driverName":"王福利","examiner":"王福利","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"4路","carNo":"鲁RN1983","driverName":"范鲁方","examiner":"范鲁方","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"4路","carNo":"鲁RG7631","driverName":"赵保林","examiner":"赵保林","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"6","carNo":"鲁RG0221","driverName":"侯保建","examiner":"侯保建","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"9","carNo":"鲁RG7752","driverName":"胡燕龙","examiner":"胡燕龙","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"9","carNo":"鲁RG7671","driverName":"桑圣军","examiner":"桑圣军","projectName":"","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"四路","carNo":"鲁RG7562","driverName":"史红运","examiner":"史红运","projectName":"","note":"史红运已确认"},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"16","carNo":"鲁RG7930","driverName":"李建国","examiner":"李建国","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"16","carNo":"鲁RG7903","driverName":"李友良","examiner":"李友良","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"2058","carNo":"鲁RG0262","driverName":"靳瑞峰","examiner":"靳瑞峰","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"21","carNo":"鲁RN8173","driverName":"苑庆现","examiner":"苑庆现","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"21","carNo":"鲁RN8701","driverName":"赵崇银","examiner":"赵崇银","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"27","carNo":"鲁RG0575","driverName":"邓金虎","examiner":"付博邓金虎","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"27","carNo":"鲁RG0532","driverName":"罗广涛","examiner":"罗广涛","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"27","carNo":"鲁RG0537","driverName":"罗广涛","examiner":"罗广涛","projectName":"","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"58","carNo":"鲁RG7227","driverName":"魏陈扬","examiner":"魏陈扬","projectName":"","note":"正常\n"},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"8","carNo":"鲁R08593D","driverName":"任立春","examiner":"任立春","projectName":"","note":""},{"depName":"城乡公交一车队","kaoheDate":"2020-02-14","lineCode":"1","carNo":"鲁R06817D","driverName":"李昌伟","examiner":"李昌伟","projectName":"","note":""},{"depName":"城乡公交一车队","kaoheDate":"2020-02-15","lineCode":"10","carNo":"鲁RN0769","driverName":"杨洪领","examiner":"杨洪领","projectName":"","note":""},{"depName":"城乡公交一车队","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁R06865D","driverName":"杨红","examiner":"杨红","projectName":"","note":"鲁RG6707"},{"depName":"城乡公交一车队","kaoheDate":"2020-02-14","lineCode":"11","carNo":"鲁RG670770","driverName":"杨红","examiner":"杨红","projectName":"","note":""},{"depName":"城乡公交一车队","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7572","driverName":"朱芬芬","examiner":"朱芬芬","projectName":"","note":""},{"depName":"一公司","kaoheDate":"2020-02-15","lineCode":"15","carNo":"鲁RG7926","driverName":"桑贤建","examiner":"桑贤建","projectName":"雨刷、倒车镜、玻璃是否完好","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"22","carNo":"鲁RG0287","driverName":"王琦","examiner":"王琦","projectName":"助力油管路、水箱是否漏油（气）、漏水","note":"助力泵，打气泵渗油"},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7590","driverName":"朱燕娜","examiner":"朱燕娜","projectName":"车辆外观（广告画面）","note":""},{"depName":"一公司","kaoheDate":"2020-02-14","lineCode":"7","carNo":"鲁RG7590","driverName":"朱燕娜","examiner":"朱燕娜","projectName":"车辆外观（广告画面）","note":""},{"depName":"二公司","kaoheDate":"2020-02-15","lineCode":"13","carNo":"鲁RN2171","driverName":"乔增鹏","examiner":"乔增鹏","projectName":"门铃、监控设备是否正常工作","note":"视频有丢失"},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"17","carNo":"鲁RG7580","driverName":"刘同民","examiner":"刘同民","projectName":"行驶中有无异响","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"17","carNo":"鲁RG7580","driverName":"刘同民","examiner":"刘同民","projectName":"仪表是否有故障灯亮起、充电是否正常","note":""},{"depName":"三公司","kaoheDate":"2020-02-14","lineCode":"36","carNo":"鲁RG6730","driverName":"张英豪","examiner":"张英豪","projectName":"雨刷、倒车镜、玻璃是否完好","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-14","lineCode":"27","carNo":"鲁RG0535","driverName":"张波","examiner":"张波","projectName":"助力油管路、水箱是否漏油（气）、漏水","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"58","carNo":"鲁RG6523","driverName":"刘洪英","examiner":"刘洪英","projectName":"报站器、刷卡机是否工作正常","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"58","carNo":"鲁RG7211","driverName":"魏陈扬","examiner":"魏陈扬","projectName":"投币箱、线路标牌、车厢灯是否工作正常","note":"投币箱不翻板"},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"8","carNo":"鲁R08586D","driverName":"张浩","examiner":"张浩","projectName":"行驶中有无异响","note":""},{"depName":"定制公交公司","kaoheDate":"2020-02-15","lineCode":"8","carNo":"鲁R08586D","driverName":"张浩","examiner":"张亚强","projectName":"行驶中有无异响","note":""}]
     */

    private boolean success;
    private int totalCounts;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * depName : 广告设施维护科
         * kaoheDate : 2020-02-14
         * lineCode : 11
         * carNo : 鲁RG6707
         * driverName : 杨红
         * examiner : 杨红
         * projectName :
         * note :
         */

        private String depName;
        private String kaoheDate;
        private String lineCode;
        private String carNo;
        private String driverName;
        private String examiner;
        private String projectName;
        private String note;

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getKaoheDate() {
            return kaoheDate;
        }

        public void setKaoheDate(String kaoheDate) {
            this.kaoheDate = kaoheDate;
        }

        public String getLineCode() {
            return lineCode;
        }

        public void setLineCode(String lineCode) {
            this.lineCode = lineCode;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getExaminer() {
            return examiner;
        }

        public void setExaminer(String examiner) {
            this.examiner = examiner;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
