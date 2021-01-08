# GodGame

###### 其实是写错名了 应该是 GoodGame 懒得改了

## Settings
```yaml
#插件提示的标题头
Title: "§8[§f GoodGame §8] §7"
#这里是正则匹配输入内容
#详情: https://www.runoob.com/java/java-regular-expressions.html
CapturedWorld:
  - '(?i)gg'
  - '(?i)good game'

#执行后超时时间
#超时后输入gg无法获取人品值
CapturedTime: 200
#执行组
#优先级是从上向下运行
#vip级别越高写的越上面
CapturedGiveing:
  #组
  vip:
    #权限 写 none就是不判断 然后执行
    perms: 'vip'
    #给予的数值
    giving: 10
    #语言 发送提示 然后发送
    #判断的是变量 Language 默认为Chinese
    #提示中可以走papi变量
    message:
      Chinese: '&d+10 Karma for %player_name%'
      English: '&d+10 Karma'
  default:
    perms: 'none'
    giving: 5
    message:
      Chinese: '&d+5 Karma'
      English: '&d+5 Karma'
```

## Commands
````
/karma look [玩家]  -- 查询变量
/karma set [玩家] [变量] [数值]  -- 设置变量
/karma take/add [玩家] [变量] [数值] -- 操作玩家变量
/karma enable [玩家] -- 进入状态

变量: 
Character & Language
Character: 人品值
Language: 语言 默认为 Chinese 不可以用 take/add
````

## Papi
````
%godgame_character% 返回玩家的人品值
%godgame_language% 返回玩家的语言
````