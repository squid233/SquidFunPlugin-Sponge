# SquidFunPlugin-Sponge
This is a Sponge Plugin.

## Features 特性
This plugin will spawn 0~5 squid when you right click the sponge.  
当你右键海绵时此插件将生成0~5只鱿鱼。

This plugin will give you a named "<span style="color:gold;">金坷垃</span>" bone meal when you use the bone meal. But! Probability = `new Random().nextInt(10) == new Random().nextInt(10)`.  
当你使用骨粉时此插件将给予你一个名为“<span style="color:gold;">金坷垃</span>”的骨粉。但是！概率等于`new Random().nextInt(10) == new Random().nextInt(10)`。(10%)

### Translations 翻译

本插件自带汉化，如果遇到所谓的“汉化版”，请不要相信！


### Commands 命令

1. /squidfunplugin  
   alias 别名: sfp  
   description 描述: SquidFunPlugin misc  
   result 结果: SquidFunPlugin version
    1. config  
       alias: cfg  
       desc: Set config  
       no-result
        1. lang  
           no-alias  
           desc: Set lang  
           arg: A string

    2. forcegc  
       alias: gc  
       desc: Force execute `System.gc()`  
       result: Current free memory: `Runtime.getRuntime().freeMemory() / 1024 / 1024 / 1024` GB

## Feedback 反馈
If you have any problem, you new [issues](https://github.com/squid233/SquidFunPlugin-Sponge/issues)  
如果遇到问题可以发到[issues](https://github.com/squid233/SquidFunPlugin-Sponge/issues)