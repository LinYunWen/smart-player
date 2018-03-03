package com.kkbox.yunwenlin.smartPlayer

class Category {
    inner class MoodCategory {
        val moodMutableMap = mutableMapOf<String, String>()
        val mood = mapOf<String, Array<String>>(
                "Work Out" to arrayOf("慢跑", "運動", "激勵", "鼓舞", "振奮", "節奏", "健身", "鍛鍊", "律動"),
                "Party Animal" to arrayOf("狂歡", "派對", "動感", "電音", "電子", "瘋狂", "跳舞", "搖滾", "嘻哈", "快歌"),
                "Relaxing" to arrayOf("輕鬆", "愉悅", "輕音樂", "純旋律", "大自然", "爵士", "舒緩", "自在", "溫和", "寧靜", "冥想", "睡前歌曲", "舒眠", "夢鄉", "鋼琴", "淨化"),
                "Working Time" to arrayOf("專注", "激昂", "振奮", "凝聚", "沈靜心緒", "保持清醒", "集中精神", "靈感"),
                "Romantic" to arrayOf("告白", "求婚", "熱戀", "曖昧", "單戀", "愛", "感情", "初戀", "回憶", "暖心", "情歌", "浪漫", "祝福"),
                "Vacation" to arrayOf("快樂", "出遊", "旅行", "度假", "陪伴", "解壓", "冒險"),
                "Chill Out" to arrayOf("下午茶", "輕音樂", "放鬆", "溫暖", "愉悅", "談天", "古典", "寧靜午後"),
                "Tipsy Night" to arrayOf("輕柔", "放鬆", "解壓", "愉悅", "藍調", "爵士"),
                "Acoustic Pop" to arrayOf("輕快", "鄉村", "新鮮", "活潑", "自然", "吉他", "慢歌", "合音"),
                "Hardcore" to arrayOf("重金屬", "搖滾", "電子電音", "電吉他", "喧鬧", "熱血", "吵雜")
        )

        init {
            mood.forEach { (key, value) ->
                value.forEach { element -> moodMutableMap.put(element, key) }
            }
        }

        fun matchMood(input: String): String {
            val matchClass = moodMutableMap.filterKeys { key -> input.contains(key) }
            if (matchClass.isEmpty()) {
                return "error"
            }
            return MainActivity.moodStation.getMoodStationId(matchClass.values.first())
        }
    }

    inner class GenreCategory {
        var genreClass = ""
        var genreSubClass = ""
        val genreMutableMap = mutableMapOf<String, String>()
        val genreSubMutableMap = mutableMapOf<String, String>()
        val genre = mapOf<String, Array<String>>(
                "Mandarin" to arrayOf("華語", "中文", "國語"),
                "Western" to arrayOf("西洋", "英文", "英語", "英國", "美國"),
                "Japanese" to arrayOf("日語", "日文", "日系", "日本"),
                "Korean" to arrayOf("韓語", "韓文", "韓系", "韓國"),
                "Hokkien" to arrayOf("台語"),
                "Cantonese" to arrayOf("粵語", "香港"),
                "Hip Hop/R&B" to arrayOf("嘻哈", "藍調", "R and B"),
                "Rock" to arrayOf("搖滾"),
                "Electronic" to arrayOf("電子"),
                "Jazz" to arrayOf("爵士"),
                "Classical" to arrayOf("古典"),
                "Background Music" to arrayOf("背景", "情境"),
                "World Music" to arrayOf("世界音樂"),
                "Religious Music" to arrayOf("宗教"),
                "Kids Music" to arrayOf("兒童", "兒歌", "童謠"),
                "Festival Music" to arrayOf("節慶", "節日")
        )

        val mandarin = mapOf<String, Array<String>>(
                "Mandarin New Release" to arrayOf("最新", "新"),
                "Mandopop Male" to arrayOf("男歌手", "男"),
                "Mandopop Female" to arrayOf("女歌手", "女"),
                "Mandopop Group" to arrayOf("樂團", "團體"),
                "Mandopop Duet" to arrayOf("男女對唱", "對唱"),
                "Mandopop Idol" to arrayOf("偶像", "活力"),
                "Love Song" to arrayOf("情歌", "精選情歌"),
                "Karaoke Hits" to arrayOf("KTV", "熱門", "火紅"),
                "Golden Melody Award Winner" to arrayOf("金曲獎"),
                "Mandarin Film & TV Songs" to arrayOf("電影", "偶像劇", "電視劇"),
                "Mandarin R&B" to arrayOf("R and B", "藍調", "節奏"),
                "Mandarin Hip-Hop" to arrayOf("嘻哈", "饒舌"),
                "Mandarin Rock" to arrayOf("搖滾", "熱血"),
                "Mandarin Folk" to arrayOf("民謠", "清新"),
                "Everlasting Mandarin Artist" to arrayOf("老歌", "歷久不衰"),
                "Mandarin 70’s Folk Tunes" to arrayOf("民歌", "校園"),
                "Rock Records 30 Anniversity" to arrayOf("滾石唱片"),
                "Mandarin Classic 80’s" to arrayOf("80年代", "復古"),
                "Mandarin Classic 90’s" to arrayOf("90年代", "經典"),
                "Hakka Hits" to arrayOf("客家", "客語")
        )

        // western
        val western = mapOf<String, Array<String>>(
                "Western New Release" to arrayOf("最新", "新"),
                "Forever Love Song" to arrayOf("情歌", "精選情歌"),
                "Soft Pop" to arrayOf("慢歌", "抒情"),
                "Billboard" to arrayOf("美國告示牌"),
                "UK Official Chart" to arrayOf("英國金榜"),
                "Idol Group" to arrayOf("團體"),
                "Best Movie Songs" to arrayOf("電影", "原聲帶"),
                "Grammy Award Winner" to arrayOf("葛萊美獎"),
                "Classic Country" to arrayOf("鄉村"),
                "Everlasting Oldies" to arrayOf("老歌", "懷舊", "復古"),
                "The 60’s" to arrayOf("60年代"),
                "The 70’s" to arrayOf("70年代"),
                "The 80’s" to arrayOf("70年代"),
                "The 90’s" to arrayOf("90年代"),
                "Modern Hits" to arrayOf("熱門", "火紅", "熱銷")
        )

        // Japanese
        val japanese = mapOf<String, Array<String>>(
                "ANIME SONG" to arrayOf("動畫", "漫畫", "動漫"),
                "Japanese R&B" to arrayOf("R and B", "藍調", "節奏"),
                "J-POP New Release" to arrayOf("最新", "新"),
                "J-POP Hits" to arrayOf("熱門", "火紅", "熱銷"),
                "Japanese Voice acting" to arrayOf("-sMWYY1mkJ_uqZtB8N"),
                "Japanese Film & TV songs" to arrayOf("日劇", "電影", "電視"),
                "Non-Stop Love Songs" to arrayOf("情歌", "精選情歌"),
                "Hip-Hop" to arrayOf("嘻哈", "饒舌"),
                "Easy-listening" to arrayOf("清新", "療癒", "自然"),
                "J-Rock" to arrayOf("搖滾", "熱血"),
                "Electronica / Dance" to arrayOf("電子", "電音", "舞曲"),
                "Vocaloid" to arrayOf("4sxrzNVesKrHPBk0wZ"),
                "Enka" to arrayOf("5aeySnQ-YWXvrEt38A"),
                "J-POP Girl Band" to arrayOf("女團體", "女"),
                "70's 80's J-Pop Hits" to arrayOf("70年代", "70年代"),
                "90s J-Pop Hits" to arrayOf("90年代"),
                "Visual Rock" to arrayOf("Pa2slhdsfCs-hB-D2q")
        )

        // korean
        val korean = mapOf<String, Array<String>>(
                "K-POP New Release" to arrayOf("最新", "新"),
                "K-POP Hits" to arrayOf("熱門", "火紅", "熱銷"),
                "Korean Film & TV songs" to arrayOf("韓劇", "電影", "電視"),
                "K-POP Boy Band" to arrayOf("男團體", "男"),
                "K-POP Girl Group" to arrayOf("女團體", "女"),
                "Dance Music" to arrayOf("舞曲"),
                "INDIE POP" to arrayOf("清新", "自然", "乾淨"),
                "POP LOVESONGS" to arrayOf("情歌", "精選情歌")
        )

        // hokkien
        val hokkien = mapOf<String, Array<String>>(
                "Essential Hokkien Music" to arrayOf("熱門", "火紅", "熱銷"),
                "Best of Male Hokkien Singers" to arrayOf("男歌手", "男"),
                "Best of Female Hokkien Singers" to arrayOf("女歌手", "女"),
                "Classic Hokkien Duet" to arrayOf("男女對唱", "對唱"),
                "Hokkien Oldies" to arrayOf("老歌", "懷舊", "復古")
        )

        // cantonese
        val cantonese = mapOf<String, Array<String>>(
                "Cantopop Male" to arrayOf("男歌手", "男"),
                "Cantopop Female" to arrayOf("女歌手", "女"),
                "Cantopop Group" to arrayOf("樂團", "團體"),
                "Indie Collection" to arrayOf("GpzUSKI6NAET_QMZym"),
                "Love Song" to arrayOf("情歌", "精選情歌"),
                "HK TV Drama Theme Tunes" to arrayOf("港劇", "港片")
        )


        // hip-hop/R&B
        val hipHop = mapOf<String, Array<String>>(
                "Hip-Hop Hits" to arrayOf("熱門", "火紅", "熱銷"),
                "US Rap" to arrayOf("美式饒舌", "rap"),
                "Urban Soul/Neo-Soul" to arrayOf("都會靈魂"),
                "Reggae/DanceHall/Ska" to arrayOf("雷鬼"),
                "Non-English Hip-Hop" to arrayOf("異國嘻哈"),
                "Disco/Funk" to arrayOf("迪斯可", "放克"),
                "Classic Soul" to arrayOf("靈魂樂"),
                "R&B" to arrayOf("R and B", "藍調", "節奏")
        )

        // rock
        val rock = mapOf<String, Array<String>>(
                "Modern Rock" to arrayOf("熱門", "火紅", "熱銷"),
                "Nu-Metal" to arrayOf("無金屬"),
                "Heavy Metal/Hard Rock" to arrayOf("重金屬", "爆發性", "硬式搖滾"),
                "Alternative Rock" to arrayOf("另類搖滾"),
                "British Rock" to arrayOf("英式搖滾"),
                "Punk Rock" to arrayOf("龐克搖滾"),
                "Soft Indie Pop" to arrayOf("獨立", "硬地"),
                "Great Rock Guitar" to arrayOf("吉他手"),
                "60's & 70's Rock" to arrayOf("老搖滾", "60年代", "70年代"),
                "Rock Ballad" to arrayOf("情歌", "精選情歌")
        )

        // electronic
        val electronic = mapOf<String, Array<String>>(
                "Electro Mix" to arrayOf("電音", "混音"),
                "Dance Hits" to arrayOf("熱門", "火紅", "熱銷"),
                "Drum & Bass" to arrayOf("鼓", "貝斯"),
                "Trip-Hop" to arrayOf("神遊舞曲", "Trip Hop"),
                "Big Beat/Breakbeat" to arrayOf("碎拍" ,"Big Beat", "Breakbeat"),
                "Lounge Bar" to arrayOf("Lounge bar"),
                "Disco" to arrayOf("迪斯可", "Disco"),
                "Techno/Trance" to arrayOf("Trance"),
                "House" to arrayOf("舒緩電子樂", "放鬆電子樂")
        )

        // jazz
        val jazz = mapOf<String, Array<String>>(
                "Jazz Collection" to arrayOf("熱門", "火紅", "熱銷", "精選"),
                "Smooth Jazz" to arrayOf("輕鬆"),
                "Vocal Jazz" to arrayOf("演唱輯", "演唱"),
                "Instrumental Jazz" to arrayOf("演奏輯", "演奏"),
                "Bossa Nova" to arrayOf("巴薩諾瓦", "bossa nova"),
                "Blues" to arrayOf("藍調"),
                "Big Band/Swing" to arrayOf("大樂隊", "搖擺樂", "樂團"),
                "Bop/Hard Bop" to arrayOf("咆勃", "硬咆勃"),
                "Cool Jazz/ West Coast Jazz" to arrayOf("酷派", "西岸爵士"),
                "Fusion/Cross-over" to arrayOf("融合爵士", "跨界爵士"),
                "Free Jazz" to arrayOf("自由爵士"),
                "New Orleans Jazz" to arrayOf("紐奧良爵士"),
                "Soul Jazz/Funk" to arrayOf("靈魂爵士", "放克"),
                "Latin Jazz" to arrayOf("拉丁爵士"),
                "Jazz Standard" to arrayOf("爵士標準曲"),
                "The Penguin Guide to Jazz" to arrayOf("企鵝"),
                "Miles Davis" to arrayOf("Miles Davis"),
                "John Coltrane" to arrayOf("John Coltrance"),
                "Great Jazz Piano" to arrayOf("鋼琴"),
                "Great Jazz Trumpet" to arrayOf("小號"),
                "Great Jazz Guitar" to arrayOf("吉他"),
                "Great Jazz Saxophone" to arrayOf("薩克斯風")
        )

        // classical
        val classical = mapOf<String, Array<String>>(
                "Famous Classical Music" to arrayOf("熱門", "名曲", "精選"),
                "Berlin Philharmonic Orchestra" to arrayOf("柏林愛樂", "樂團"),
                "Best Mozart" to arrayOf("莫札特"),
                "Best Beethoven" to arrayOf("貝多芬"),
                "Best Schubert" to arrayOf("舒伯特"),
                "Best Chopin" to arrayOf("蕭邦"),
                "Best Bach" to arrayOf("巴哈"),
                "Best Tchaikovsky" to arrayOf("柴可夫斯基"),
                "Classical Vocal" to arrayOf("古典美聲"),
                "Cross-Over Vocal" to arrayOf("跨界美聲"),
                "Best Opera" to arrayOf("歌劇"),
                "Baroque" to arrayOf("巴洛克"),
                "Classical Period" to arrayOf("古典樂派"),
                "Romantic Era" to arrayOf("浪漫樂派"),
                "Modern & Contemporary" to arrayOf("現代樂派"),
                "Nationalistic" to arrayOf("國民樂派"),
                "Impressionism" to arrayOf("印象樂派"),
                "Great Classical Piano" to arrayOf("鋼琴"),
                "Great Classical Violin" to arrayOf("小提琴"),
                "Great Classical Cello" to arrayOf("大提琴"),
                "Great Conductors" to arrayOf("指揮家")
        )

        // background
        val background = mapOf<String, Array<String>>(
                "Chill Out" to arrayOf("舒壓", "輕鬆", "放鬆"),
                "Tropical Holidays" to arrayOf("熱帶", "南國", "南國渡假"),
                "Rise And Shine" to arrayOf("早晨", "清晨", "早上"),
                "Whistle While You Work" to arrayOf("工作"),
                "Tea Time" to arrayOf("午茶", "約會"),
                "Romantic Dinner" to arrayOf("晚餐", "浪漫"),
                "Late Night" to arrayOf("半夜", "寧靜", "安靜"),
                "After Work Cafe" to arrayOf("下班", "酒館", "解壓"),
                "Rev It Up" to arrayOf("瘋狂"),
                "Disco Saturday" to arrayOf("狂熱", "週末夜", "派對"),
                "Yoga Spirit" to arrayOf("瑜珈", "冥想", "沈思"),
                "Work Out Tempo" to arrayOf("運動", "健身", "鍛鍊"),
                "Sunshine, Beach, Waves" to arrayOf("陽光", "沙灘", "海"),
                "Surfing" to arrayOf("衝浪"),
                "Sweet Love" to arrayOf("情人", "情歌", "甜言蜜語"),
                "Instrumental Chinese Pop Music" to arrayOf("華語流行"),
                "Instrumental Japanese/Korean Pop Music" to arrayOf("日韓流行"),
                "Movie Original Soundtrack" to arrayOf("電影配樂", "電影插曲", "電影"),
                "New Age" to arrayOf("新世紀"),
                "Baroque & Rococo" to arrayOf("歐式宮廷")
        )

        // world music
        val worldMusic = mapOf<String, Array<String>>(
                "Traditional China" to arrayOf("中國", "傳統"),
                "Chanson" to arrayOf("法式", "法國"),
                "Latin" to arrayOf("拉丁"),
                "Flamenco" to arrayOf("佛朗明哥"),
                "Italy" to arrayOf("義大利"),
                "Caribbean" to arrayOf("加勒比海", "熱帶風情"),
                "Hawaii" to arrayOf("夏威夷"),
                "Mexican" to arrayOf("墨西哥"),
                "Argentina" to arrayOf("阿根廷"),
                "Bali" to arrayOf("印尼", "巴里島"),
                "Thailand" to arrayOf("泰國"),
                "India" to arrayOf("印度"),
                "Middle East" to arrayOf("中東"),
                "Africa" to arrayOf("非洲")
        )

        // religious music
        val religious = mapOf<String, Array<String>>(
                "Gospel Hits" to arrayOf("福音"),
                "Taoist Music" to arrayOf("民間信仰"),
                "Tibetan Buddhism" to arrayOf("佛教"),
                "The Great Compassion Sutra" to arrayOf("大悲咒"),
                "The Heart Sutra" to arrayOf("心經"),
                "Sutra Reciting" to arrayOf("佛經"),
                "Hokkien Sutra Reciting" to arrayOf("台語佛經")
        )

        // kids music
        val kids = mapOf<String, Array<String>>(
                "Disney Cartoons" to arrayOf("迪士尼"),
                "Kid's Songs" to arrayOf("西洋"),
                "Children's Classics" to arrayOf("經典"),
                "Lullaby" to arrayOf("搖籃曲", "安眠曲"),
                "Pop Kids Songs" to arrayOf("流行")
        )

        // festival music
        val festival = mapOf<String, Array<String>>(
                "Lunar New Year" to arrayOf("新年", "農曆新年")
        )

        val genreSub = mapOf<String, Map<String, Array<String>>>(
                "Mandarin" to mandarin,
                "Western" to western,
                "Japanese" to japanese,
                "Korean" to korean,
                "Hokkien" to hokkien,
                "Cantonese" to cantonese,
                "Background Music" to background,
                "Electronic" to electronic,
                "Hip Hop/R&B" to hipHop,
                "Rock" to rock,
                "Jazz" to jazz,
                "Religious Music" to religious,
                "Classical" to classical,
                "World Music" to worldMusic,
                "Kids Music" to kids,
                "Festival Music" to festival
        )

        fun checkGenreType(input: String): String {
            val matchClass = genreMutableMap.filterKeys { key -> input.contains(key) }
            if (matchClass.isEmpty()) {
                return "error"
            }
            genreClass = matchClass.values.first()
            return genreClass
        }

        fun matchGenre(input: String): String {
            genreSub[genreClass]!!.forEach { (key, value) ->
                value.forEach { element -> genreSubMutableMap.put(element, key) }
            }

            val matchSubClass = genreSubMutableMap.filterKeys { key -> input.contains(key) }
            if (matchSubClass.isEmpty()) {
                return "error"
            }
            genreSubClass = matchSubClass.values.first()
            return MainActivity.genreStation.getGenreStationIds(genreClass, genreSubClass)
        }

        init {
            genre.forEach { (key, value) ->
                value.forEach { element -> genreMutableMap.put(element, key) }
            }
        }
    }

    var moodCategory = MoodCategory()
    var genreCategory = GenreCategory()
}