# KKBOX OPEN API Example - Smart Player

## Screenshot
![](https://i.imgur.com/4Tt8r9K.png =360x)

## Prerequirements
1. [android studio](https://developer.android.com/studio/index.html) 2+ (3+ will be best)
2. devices with android os
3. [KKBOX OPEN API client id and secret](https://developer.kkbox.com/#/signin)

> Note:
> You need to go to **[KKBOX OPEN API developer site](https://developer.kkbox.com/#/)** for sign up an account then new an app and get the client id and secret.

## Setup
1. cd app/src/res/raw
2. vim config_template and fill your client id and secret

```javascript
clientID = [YOUR_CLIENT_ID]
clientSecret = [YOUR_CLIENT_SECRET]
// Note:
// you should not add "" around id and secret
// this will be error
clientID = "1234556"
```

3. rename this file by "mv config_template config.properties"
4. run this project on your android devices

## Operations
1. step 1: **make sure device is connect to wifi or network**
2. step 2: press "Start Recording" button and you will hear a beep then start to speak.
3. step 3: after speaking, press "Stop Recording" button.
4. step 4: It will auto-play the song just searching.

> - press "Pause" button can pause this song and resume by pressing it again.
> - press "Replay" button can replay the song.

## Speaking Usage
1. operations
    - speak "下一首", it can play the next song
    - speak "上一首", it can play the front song
    - speak "暫停播放", it can pause this song
    - speak "繼續播放", it can resume this song
    - speak "重新播放", it can replay this song
2. general searching
    - speak general sentence or key word
    - ex. "播放林俊傑的音樂", "我想聽稻香" or "知足"
3. mood station searching
    - speak sentence with key word "電台"
    - ex. "適合運動的音樂電台"
4. genre station searching
    - speak sentence with key words in genre rule map
    - ex. "播放華語新歌", "日本動漫音樂"

## Mood Station Key Word Rule Map
- "Work Out": 
    "慢跑", "運動", "激勵", "鼓舞", "振奮", "節奏", "健身", "鍛鍊", "律動"
- "Party Animal": 
    "狂歡", "派對", "動感", "電音", "電子", "瘋狂", "跳舞", "搖滾", "嘻哈", "快歌"
- "Relaxing": 
    "輕鬆", "愉悅", "輕音樂", "純旋律", "大自然", "爵士", "舒緩", "自在", "溫和", "寧靜", "冥想", "睡前歌曲", "舒眠", "夢鄉", "鋼琴", "淨化"
- "Working Time": 
    "專注", "激昂", "振奮", "凝聚", "沈靜心緒", "保持清醒", "集中精神", "靈感"
- "Romantic": 
    "告白", "求婚", "熱戀", "曖昧", "單戀", "愛", "感情", "初戀", "回憶", "暖心", "情歌", "浪漫", "祝福"
- "Vacation": 
    "快樂", "出遊", "旅行", "度假", "陪伴", "解壓", "冒險"
- "Chill Out": 
    "下午茶", "輕音樂", "放鬆", "溫暖", "愉悅", "談天", "古典", "寧靜午後"
- "Tipsy Night": 
    "輕柔", "放鬆", "解壓", "愉悅", "藍調", "爵士"
- "Acoustic Pop": 
    "輕快", "鄉村", "新鮮", "活潑", "自然", "吉他", "慢歌", "合音"
- "Hardcore": 
    "重金屬", "搖滾", "電子電音", "電吉他", "喧鬧", "熱血", "吵-雜"
    
## Genre Station Key Word Rule Map
- "Mandarin": "華語", "中文", "國語"
- "Western": "西洋", "英文", "英語", "英國", "美國"
- "Japanese": "日語", "日文", "日系", "日本"
- "Korean": "韓語", "韓文", "韓系", "韓國"
- "Hokkien": "台語"
- "Cantonese": "粵語", "香港"
- "Hip Hop/R&B": "嘻哈", "藍調", "R and B"
- "Rock": "搖滾"
- "Electronic": "電子"
- "Jazz": "爵士"
- "Classical": "古典"
- "Background Music": "背景", "情境"
- "World Music": "世界音樂"
- "Religious Music": "宗教"
- "Kids Music": "兒童", "兒歌", "童謠"
- "Festival Music": "節慶", "節日"

> Note:
> Too many genre station classes and just list parts of them here. If you want to know all classes please look at app/src/main/java/com/kkbox/yunwenlin/smartPlayer/stations/Category.kt

## License
- Apache 2.0

## Reference
1. [KKBOX developer site](https://developer.kkbox.com/#/)
2. [KKBOX OPEN API doc](https://docs.kkbox.codes/docs)
3. [Kotlin](https://kotlinlang.org/)

